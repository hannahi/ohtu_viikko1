/* 
 * File:   avl.h
 * Author: hanna
 *
 * Created on 27. lokakuuta 2011, 22:02
 */

#ifndef AVL_H
#define	AVL_H

#ifdef	__cplusplus
extern "C" {
#endif


#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct Solmu;

void vapauta(struct Solmu *solmu);
struct Solmu* etsi(char *X, struct Solmu *solmu);
struct Solmu* lisaa(char *X, struct Solmu *solmu);
int korkeus(struct Solmu *solmu);
int maksimikorkeus(int Lhs, int Rhs);
struct Solmu* yks_rotaatio_vasen(struct Solmu *solmu);
struct Solmu* yks_rotaatio_oikea(struct Solmu *solmu);
struct Solmu* tupla_rotaatio_vasen(struct Solmu *solmu);
struct Solmu* tupla_rotaatio_oikea(struct Solmu *solmu);
void kirjoita_tiedostoon(FILE* fptr, struct Solmu* T);


#ifdef	__cplusplus
}
#endif

#endif	/* AVL_H */

