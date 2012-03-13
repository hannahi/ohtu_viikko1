/* 
 * File:   main.c
 * Author: hendri & hanna
 *
 * Created on September 30, 2011, 7:25 PM
 * 
 * Tasapainotetun AVL-puun toteutus.
 */

#include "avl.h"

/*
 * Määritellään AVL-puun solmulle struct.
 */
typedef struct Solmu {
    char *sana;
    struct Solmu* vasen;
    struct Solmu* oikea;
    int korkeus;
    int lkm;
} solmu;

/*
 * Funktio vapauttaa muistista tietorakenteen.
 */
void vapauta(struct Solmu* T) {
    if (T != NULL) {
        vapauta(T->vasen);
        vapauta(T->oikea);
        free(T);
    }
}

/*
 * Funktio ottaa parametreikseen char pointterin eli etsittävän sanan sekä puun.
 * Kun etsittävä sana löytyy funktio palauttaa arvonaan solmun, josta sama sana
 * löytyi tai NULL jos etsittävää ei löytynyt.
 */
struct Solmu* etsi(char *x, struct Solmu* T) {
    if (T == NULL) {
        return NULL;
    }
    if (strcmp(x, T->sana) < 0) {
        return etsi(x, T->vasen);
    } else {
        if (strcmp(x, T->sana) > 0) {
            return etsi(x, T->oikea);
        } else {
            return T;
        }
    }
}

/*
 * Funktio palauttaa annetun solmun korkeuden.
 */
int korkeus(struct Solmu* T) {
    if (T == NULL) {
        return -1;
    } else {
        return T->korkeus;
    }
}

/*
 * Funktio palauttaa annetuista parametreistä suurimman eli maksimikorkeuden.
 */
int maksimikorkeus(int vasen, int oikea) {
    return vasen > oikea ? vasen : oikea;
}

/*
 * Funktio toteuttaa yksinkertaisen kierron vasemmalle. Solmujen korkeudet
 * päivitetään ja lopuksi palautetaan uusi juurisolmu.
 */
struct Solmu* yks_rotaatio_vasen(struct Solmu* s2) {
    struct Solmu* s1;

    s1 = s2->vasen;
    s2->vasen = s1->oikea;
    s1->oikea = s2;

    s2->korkeus = maksimikorkeus(korkeus(s2->vasen), korkeus(s2->oikea)) + 1;
    s1->korkeus = maksimikorkeus(korkeus(s1->vasen), s2->korkeus) + 1;

    return s1; /* New root */
}

/*
 * Funktio toteuttaa yksinkertaisen kierron oikealle. Solmujen korkeudet
 * päivitetään ja lopuksi palautetaan uusi juurisolmu.
 */
struct Solmu* yks_rotaatio_oikea(struct Solmu* s1) {
    struct Solmu* s2;

    s2 = s1->oikea;
    s1->oikea = s2->vasen;
    s2->vasen = s1;

    s1->korkeus = maksimikorkeus(korkeus(s1->vasen), korkeus(s1->oikea)) + 1;
    s2->korkeus = maksimikorkeus(korkeus(s2->oikea), s1->korkeus) + 1;

    return s2;
}

/*
 * Funktio toteuttaa kaksinkertaisen kierron vasemmalle. Solmujen korkeudet
 * päivitetään ja lopuksi palautetaan uusi juurisolmu.
 */
struct Solmu* tupla_rotaatio_vasen(struct Solmu* s) {
    s->vasen = yks_rotaatio_oikea(s->vasen);

    return yks_rotaatio_vasen(s);
}

/*
 * Funktio toteuttaa kaksinkertaisen kierron oikealle. Solmujen korkeudet
 * päivitetään ja lopuksi palautetaan uusi juurisolmu.
 */
struct Solmu* tupla_rotaatio_oikea(struct Solmu* s) {
    s->oikea = yks_rotaatio_vasen(s->oikea);

    return yks_rotaatio_oikea(s);
}

/*
 * Funktio lisää solmun AVL-puuhun. Jos solmu löytyi puusta, kasvatetaan
 * vain jo olemassa olevan solmun lkm-kenttää. Muuten sulmulle etsitään oikea 
 * paikka puusta ja lopuksi päivitetään puun korkeus.
 */
struct Solmu* lisaa(char *x, struct Solmu* T) {
    solmu *etsitty;
    if (T == NULL) {
        T = malloc(sizeof (solmu));
        if (T == NULL) {
            printf("Muistinvaraus ei onnistunut!");
        } else {
            T->sana = x;
            T->korkeus = 0;
            T->vasen = T->oikea = NULL;
            T->lkm = 1;
        }
    } else {
        if ((etsitty = etsi(x, T)) != NULL) {
            ++etsitty->lkm;
        } else {
            if (strcmp(x, T->sana) < 0) {
                T->vasen = lisaa(x, T->vasen);
                if (korkeus(T->vasen) - korkeus(T->oikea) == 2) {
                    if (strcmp(x, T->vasen->sana) < 0) {
                        T = yks_rotaatio_vasen(T);
                    } else {
                        T = tupla_rotaatio_vasen(T);
                    }
                }
            } else {
                if (strcmp(x, T->sana) > 0) {
                    T->oikea = lisaa(x, T->oikea);
                    if (korkeus(T->oikea) - korkeus(T->vasen) == 2) {
                        if (strcmp(x, T->vasen->sana) > 0) {
                            T = yks_rotaatio_oikea(T);
                        } else {
                            T = tupla_rotaatio_oikea(T);
                        }
                    }
                }
            }
        }
    }
    T->korkeus = maksimikorkeus(korkeus(T->vasen), korkeus(T->oikea)) + 1;
    return T;
}

/*
 * Funktio tulostaa AVL-puun kaikki solmut aakkosjärjestyksessä.
 */

void kirjoita_tiedostoon(FILE* fptr, struct Solmu* T) {
        if (T == NULL) {
            return;
        }
        kirjoita_tiedostoon(fptr, T->vasen);
              fprintf(fptr, "%s", T->sana);
              fprintf(fptr, "\n");
              fprintf(fptr, "%d", T->lkm);
              fprintf(fptr, "\n");
        kirjoita_tiedostoon(fptr, T->oikea);
}

