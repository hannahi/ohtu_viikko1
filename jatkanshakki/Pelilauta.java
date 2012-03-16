
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


	// Voiton tarkistus : k�yd��n annetusta ruudusta l�htien kaikki suunnat l�pi,
	// palautetaan true, jos saadaan viisi per�kk�ist� merkki� johonkin suuntaan, muuten false.

	public boolean tarkistaVoitto(int i, int j) {
		laskuri = 1;

		Ruutu paikka=apuruudukko[i][j];

		// Aloituskoordinaatit talteen

        	alkui = i;
        	alkuj = j;

		// Tarkistus pohjois-etel�suunnassa

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

		// Tarkistus pohjois-etel�suunnassa jatkuu

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

		// It�-l�nsi

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

		// It�-l�nsi jatkuu

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
	
	// Tasapelin tarkistus: K�yd��n ruudukkoa l�pi, kunnes l�ytyy tyhj� ruutu. T�ll�in palautetaan false
	// ja peli jatkuu. Jos kaikki ovat t�ynn�, palautetaan true.
 
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
