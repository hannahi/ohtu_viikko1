/**
 *
 * @author hanna
 * Keko on täydellinen binääripuu. 
 * Solmut sijoittuvat taulukkoon tasoittain: 
 * juuri indeksissä 0, juuren lapset 1 ja 2 jne.
 * Vasen lapsi: left(i) = 2 * i + 1
 * Oikea lapsi: right(i) = 2 * i + 2
 * Vanhempi: parent(i) = (i-1) / 2
 * 
 */
public class Binaarikeko {

    private int[] keko;
    private int keonKoko;
    private int nykyinenKoko;
    
    /**
     * Konstruktori luo minimikeon.
     * Koko annetaan parametrina. 
     * Parametri keonKoko pitää muistissa keon maksimi kokoa.
     * Parametri nykyinenKoko pitää kirjaa keon senhetkisestä koosta.
     */
    
    public Binaarikeko(int koko) {
        keko = new int[koko];
        keonKoko = koko;
        nykyinenKoko = 0;
    }
    
    /**
     * Palauttaa keon senhetkisen koon. 
     */
    
    public int getKoko() {
        return nykyinenKoko;
    }
    
    /**
     * Tarkistaa onko keko tyhjä.
     * Palauttaa booleanin.
     */
    
    public boolean onkoTyhja() {
        return (keonKoko == 0);
    }
     
    /**
     * Palauttaa keon pienimmän alkion siis keon juurisolmun.
     */
    
    public int getMinimi() {
        return keko[0];
    }
    
    /**
     * Palauttaa indeksinä annetun solmun vanhemman indeksin.
     */
    
    public int vanhempi(int indeksi) {
        if (indeksi == 0) {
            return 0;
        }
        return (indeksi - 1) / 2;
    }
    
    /**
     * Palauttaa vasemman lapsen indeksin. 
     */

    public int vasenLapsi(int indeksi) {
        return 2 * indeksi + 1;
    }
    
    /**
     * Palauttaa oikean laspsen indeksin. 
     */

    public int oikeaLapsi(int indeksi) {
        return 2 * indeksi + 2;
    }
    
    /**
     * Lisätään kekoon alkio.
     */
    
    public void lisaa(int alkio) {
        if (nykyinenKoko >= keonKoko) {
            return;
        }
        keko[nykyinenKoko] = alkio;
        int j = nykyinenKoko;
        nykyinenKoko++;
        while (j > 1 && keko[vanhempi(j)] > alkio) {
            keko[j] = keko[vanhempi(j)];
            j = vanhempi(j);
        }
        keko[j] = alkio;
    }
    
    /**
     * Poistetaan alkio keosta. 
     */
    
    public void poista(int alkio) {
        int[] uusiKeko = new int[keonKoko];
        for (int j = 0; j < alkio; j++) {
            uusiKeko[j] = keko[j];
        }
        for (int j = alkio; j < nykyinenKoko; j++) {
            uusiKeko[j] = keko[j + 1];
        }
        keko = uusiKeko;
        nykyinenKoko--;
        heapify(vanhempi(alkio));
    }
    
    public void rakennaKeko(int[] taulukko) {
        keko = taulukko;
        nykyinenKoko = taulukko.length;
        for (int i = (taulukko.length / 2)-1; i >= 0; i--) {
            heapify(i);
        }
    }
    
    /**
     * Korjataan kekoehto.
     */
    
    int pienin = 0;
    private void heapify(int i) {
        int vasen = vasenLapsi(i);
        int oikea = oikeaLapsi(i);
        if (vasen < nykyinenKoko && keko[vasen] < keko[i]) {
                pienin = vasen;
            } else {
                pienin = i;
        }
        if (oikea < nykyinenKoko && keko[oikea] < keko[pienin]) {
                pienin = oikea;
        } 
        if (pienin != i) {
            int apu2 = keko[i];
            keko[i] = keko[pienin];
            keko[pienin] = apu2;
            heapify(pienin);
        }
    }
    
    /** 
     * Metodit keon alkioiden esitykseen.
     */
    
    @Override
    public String toString() {
        String tuloste = "";
        for (int i = 0; i < nykyinenKoko; i++) {
            tuloste += keko[i] + " ";
        }
        return tuloste;
    }

    public String toString(int[] a) {
        String tuloste = "";
        for (int i = 0; i < a.length; i++) {
            tuloste += a[i] + " ";
        }
        return tuloste;
    }
 
    public static void main(String[] args) {
        Binaarikeko kokeilu = new Binaarikeko(20);
        kokeilu.lisaa(14);
        kokeilu.lisaa(12);
        kokeilu.lisaa(1);
        kokeilu.lisaa(8);
        kokeilu.lisaa(7);
        kokeilu.lisaa(2);
        kokeilu.lisaa(3);

        System.out.println(kokeilu.toString());
        System.out.println("poistetaan ind. 3 oleva alkio 12");
        kokeilu.poista(3);
        System.out.println(kokeilu.toString());
        System.out.println("poistetaan juuri");
        kokeilu.poista(0);
        System.out.println(kokeilu.toString());
        System.out.println("rakennetaan keko {5,2,8,12,1,3,63,14}");
        int[] taulu = {5,2,8,12,1,3,63,14};
        kokeilu.rakennaKeko(taulu);
        System.out.println(kokeilu.toString(taulu));
    }   
}
