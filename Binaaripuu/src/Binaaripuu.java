
public class Binaaripuu {

    Puusolmu juuri;

    public void lisaa(long avain) {
        Puusolmu uusi = new Puusolmu();
        uusi.avain = avain;
        if (juuri == null) {
            juuri = uusi;
            return;
        }
        Puusolmu solmu = juuri;
        while (true) {
            if (avain == solmu.avain) {

                return;
            }
            if (avain < solmu.avain) {
                if (solmu.vasen == null) {
                    solmu.vasen = uusi;
                    return;
                } else {
                    solmu = solmu.vasen;
                }
            } else {
                if (solmu.oikea == null) {
                    solmu.oikea = uusi;
                } else {
                    solmu = solmu.oikea;
                }
            }
        }
    }

    public boolean haku(long avain) {
        Puusolmu solmu = juuri;
        while (true) {
            if (solmu == null) {
                return false;
            }
            if (avain == solmu.avain) {
                return true;
            }
            if (avain < solmu.avain) {
                solmu = solmu.vasen;
            } else {
                solmu = solmu.oikea;
            }
        }
    }
}

class Puusolmu {

    Puusolmu vasen;
    Puusolmu oikea;
    long avain;
}

