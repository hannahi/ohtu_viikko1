
public class Ruutu {
	
	// Symboli kertoo sen, onko ruutu tyhj‰ (=0) vai t‰ytetty (punainen=1 tai vihre‰=2).
	// Jos ruutu on osa voittorivi‰, se saa arvokseen 3. 	

	private int symboli;  
	
	// Luo Ruutu - olion, joka on tyhj‰
	
	public Ruutu() {
		
		this.symboli = 0;
		
	}
	
	// T‰ytet‰‰n annetulla merkill‰	

	public void tayta(int merkki) {	
		
		this.symboli=merkki;
    
	}
	
	// Mik‰ v‰ri on kyseess‰
	
	public int mikaVari() {
		
		return this.symboli; 
	
	}
	
	// Tarkistetaan onko ruutu t‰ynn‰
	
	public boolean onkoTaynna() {
	    
		if ( this.symboli != 0 ) 
			return true;
		else
			return false;
	
	}
}  
