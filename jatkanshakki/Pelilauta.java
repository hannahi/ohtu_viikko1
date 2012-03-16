
public class Pelilauta { 
        
	public Ruutu apuruudukko[][];
	private int laskuri, alkui, alkuj;  // apulaskuri voiton ja tasapelin laskemiseen


	public Pelilauta(int koko) {
		
		apuruudukko= new Ruutu[koko][koko];
        	for ( int j = 0; j< apuruudukko.length; ++j) {
			for ( int i = 0; i< apuruudukko[j].length; ++i) 
				apuruudukko[i][j]= new Ruutu();
            }
    }


	// Voiton tarkistus : käydään annetusta ruudusta lähtien kaikki suunnat läpi,
	// palautetaan true, jos saadaan viisi peräkkäistä merkkiä johonkin suuntaan, muuten false.

	public boolean tarkistaVoitto(int i, int j) {
		laskuri = 1;

		Ruutu paikka=apuruudukko[i][j];

		// Aloituskoordinaatit talteen

        	alkui = i;
        	alkuj = j;

		// Tarkistus pohjois-eteläsuunnassa

        	while((j-1)>=0 && paikka.mikaVari() == apuruudukko[i][j-1].mikaVari() && laskuri < 5) {

		++laskuri;
        		--j;
	
        		if( laskuri == 5 ) {
        			for ( int a = 0; a < 5; ++a) {
        				this.apuruudukko[i][j].tayta(3);
					++j;
				}
        			return true;
        		}
        	}

        	i = alkui;
        	j = alkuj;

		// Tarkistus pohjois-eteläsuunnassa jatkuu

        	while( j<apuruudukko[i].length-1 && paikka.mikaVari() == apuruudukko[i][j+1].mikaVari() && laskuri < 5) { 

        		++laskuri;
        		++j;
	
        		if ( laskuri == 5 ){
        			for ( int a = 0; a < 5; ++a) {
        				this.apuruudukko[i][j].tayta(3);
					--j;
				}
        			return true;
        		}

        	}

        	laskuri = 1;

        	i = alkui;
        	j = alkuj;


		// Koillinen-lounas

        	while((j-1) >= 0 && i<apuruudukko[i].length-1 && paikka.mikaVari() == apuruudukko[i+1][j-1].mikaVari() && laskuri < 5) {

        		++laskuri;
        		++i;
        		--j;
	
        		if (laskuri == 5){
        			for ( int a = 0; a < 5; ++a) {
        				this.apuruudukko[i][j].tayta(3);
        				--i;
					++j;
				}
        			return true;
			}  
		}

        	i = alkui;
        	j = alkuj;

		// Koillinen-lounas jatkuu

        	while( (i-1) >= 0 && j<apuruudukko[i].length-1 && paikka.mikaVari() == apuruudukko[i-1][j+1].mikaVari() && laskuri < 5) { 

        		++laskuri;
        		--i;
        		++j;
	
        		if( laskuri == 5 ){
        			for ( int a = 0; a < 5; ++a) {
        				this.apuruudukko[i][j].tayta(3);
					++i;
					--j;
				}
        			return true;
        		}
        	}

        	laskuri = 1;
        	i=alkui;
        	j=alkuj;

		// Itä-länsi

        	while( i<apuruudukko[i].length-1 && paikka.mikaVari() == apuruudukko[i+1][j].mikaVari() &&  laskuri < 5) {
        		++laskuri;
        		++i;
	
        		if (laskuri == 5){
        			for ( int a = 0; a < 5; ++a) {
        				this.apuruudukko[i][j].tayta(3);
					--i;
				}
        			return true;
        		}
        	}

        	i = alkui;
        	j = alkuj;

		// Itä-länsi jatkuu

        	while((i-1) >= 0 && i<apuruudukko[i].length-1 && paikka.mikaVari() == apuruudukko[i-1][j].mikaVari() && laskuri < 5) {      

        		++laskuri;
        		--i;
	
        		if( laskuri == 5 ){
        			for ( int a = 0; a < 5; ++a) {
        				this.apuruudukko[i][j].tayta(3);
					++i;
				}
        			return true;
        		}
        	}
 
        	laskuri = 1;
		i=alkui;
		j=alkuj;


		 // Kaakko-luode	

		while( i<apuruudukko[i].length-1 && j<apuruudukko[i].length-1 && paikka.mikaVari() == apuruudukko[i+1][j+1].mikaVari() && laskuri < 5) { 
                    
			++laskuri;
			++i;
			++j;
                    	
			if (laskuri == 5){
				for ( int a = 0; a < 5; ++a) {
					this.apuruudukko[i][j].tayta(3);
					--i;
					--j;
				}
				return true;
			} 
		}

		i = alkui;
		j = alkuj;


		 // Kaakko-luode


		while( (i-1) >= 0 && (j-1) >= 0 && paikka.mikaVari() == apuruudukko[i-1][j-1].mikaVari() && laskuri < 5) {  
			++laskuri;
			--i;
			--j;
	
			if (laskuri == 5){
				for ( int a = 0; a < 5; ++a) {
					this.apuruudukko[i][j].tayta(3);
					++i;
					++j;
				}
				return true;
			}
		}

            return false;
	}
	
	// Tasapelin tarkistus: Käydään ruudukkoa läpi, kunnes löytyy tyhjä ruutu. Tällöin palautetaan false
	// ja peli jatkuu. Jos kaikki ovat täynnä, palautetaan true.
 
	public boolean tarkistaTasapeli() {

		laskuri = 0;
	
		for(int i=0; i < apuruudukko.length; ++i) {
        		for( int j=0; j < apuruudukko[i].length; ++j) { 
				if(!this.apuruudukko[i][j].onkoTaynna())
					return false;
				else
					++laskuri;
        		}

		}
	
	return true;
	}

}
