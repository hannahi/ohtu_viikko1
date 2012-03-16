/* 		Ohjelmoinnin harjoitustyö, 2008
 * 		Jätkänshakki
 * 		Tekijä : Jussi Saarinen
 * 		  
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


public class JätkänshakkiGUI extends JFrame  
					implements MouseListener {
        
		
	private JFrame peli;
	private JPanel pane, menu, ruudut;
	private JLabel ilmoitus;
        
	// Parittomalla punaisen vuoro,parillisella vihreän. Taulukko on ns. aputaulukko.

	private int vuorolaskuri = 0;	
	private JPanel[][] taulukko;
	private boolean voittomuuttuja = false;
        
	private Pelilauta pelialue;
        
        
    
        // Konstruktori.
        
	public JätkänshakkiGUI() {
                
        
        	taulukko = new JPanel[15][15];
        	ruudut = new JPanel(new GridLayout(15,15));
        
        
		// Ohjelman Frame, johon laitetaan kaksi paneelia, valikolle ja ruudukolle. 

        	peli = new JFrame("Jätkänshakki");
        	pane = new JPanel(new BorderLayout());
        	menu = new JPanel(new FlowLayout());					
        	peli.add(menu,BorderLayout.NORTH);
        	peli.add(pane);
        	pane.add(ruudut);
        
		// Voitto- tai tasapeli-ilmoitus, alussa ei luonnollisesti mitään tekstiä.

        	ilmoitus=new JLabel("");								
        															
        
		// Nappien luontia.

        	JButton pieni = new JButton("15 x 15");					
        	menu.add(pieni,BorderLayout.LINE_START);
        
        	JButton iso = new JButton("30 x 30");
        	menu.add(iso,BorderLayout.LINE_START);
        
        	JButton lopetus= new JButton("Lopeta peli");
        	menu.add(lopetus, BorderLayout.LINE_START);
        
        
        	peli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        	peli.getContentPane();
        	peli.setSize(800,800);
        	peli.setLocationRelativeTo(null);
        
        	peli.setVisible(true);
        	peli.setResizable(false);
        
        	
		// Nappien MouseListenerit.

        	pieni.addMouseListener(new MouseAdapter() {				
        		public void mouseClicked(MouseEvent e){
        			pieniHiirenKlikkaus();
        		}
        	});  
        
        	
		iso.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent e){
        			isoHiirenKlikkaus();
        		}
        	});
	
        	lopetus.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                        lopetusHiirenKlikkaus(e);
                }
                });
        
        }

        // 15 x 15 - ruudukko.
        
        private void pieniHiirenKlikkaus() {
	
        	voittomuuttuja=false;

		// Luodaan uusi pelilauta, joka on kooltaan 15 x 15 ja asetetaan vuorolaskuri nollaan.
	
        	pelialue = new Pelilauta(15);
		vuorolaskuri = 0;

		// voittoteksti / tasapeliteksti poistetaan ruudusta.

		ilmoitus.setText(""); 

		// Luodaan GridLayoutin avulla ruudukko, jossa jokainen ruutu on oma paneeli ja liitetään kuhunkin paneeliin 
		// MouseListener.

		taulukko = new JPanel[15][15];
		pane.remove(ruudut);
		ruudut = new JPanel(new GridLayout(15,15));
		pane.add(ruudut,BorderLayout.CENTER);


            	for(int j = 0; j < taulukko.length; ++j) {
            		for(int i=0; i < taulukko[j].length; ++i) {
            			taulukko[i][j] = new JPanel();
                        taulukko[i][j].addMouseListener(this);
                        taulukko[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                        taulukko[i][j].setBackground(Color.white);
                        ruudut.add(taulukko[i][j]);
                    }
                }
 
            	peli.setVisible(true);

        }

	// 30 x 30 - ruudukko. Sama periaate kuin pieniHiirenKlikkaus(), mutta taulukko on isompi, 30 x 30.

        private void isoHiirenKlikkaus() {
        	voittomuuttuja=false;
	
        	pelialue = new Pelilauta(30);
		vuorolaskuri = 0;
		ilmoitus.setText("");

		taulukko = new JPanel[30][30];     

		pane.remove(ruudut);
		ruudut = new JPanel(new GridLayout(30,30));
		pane.add(ruudut,BorderLayout.CENTER);


		for(int j = 0; j < taulukko.length ; ++j) {
			for(int i=0; i < taulukko[j].length; ++i) {
				taulukko[i][j] = new JPanel();
				taulukko[i][j].addMouseListener(this);
				taulukko[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				taulukko[i][j].setBackground(Color.white);
				ruudut.add(taulukko[i][j]);
		}
			}

		peli.setVisible(true);

        }

	// Ohjelman lopettaminen.

        private void lopetusHiirenKlikkaus(MouseEvent e) {

        	System.exit(0);

        }

	// Lopetus - napin viereen tulee voiton tai tasapelin sattuessa ilmoitus.

        private void ilmoitusTeksti(String kumpi) {
	
	ilmoitus = new JLabel();
	
        	if ( kumpi == "voitto" ){
        		if ( vuorolaskuri%2 == 0 ) 
			ilmoitus = new JLabel(" Vihreä voitti! ");
		else
			ilmoitus = new JLabel(" Punainen voitti! ");
        	}
	
        	if ( kumpi == "tasuri")
        		ilmoitus=new JLabel(" Tasapeli! ");


        	menu.add(ilmoitus,BorderLayout.AFTER_LAST_LINE);
        	peli.setVisible(true);

        }

	// Ruudun täyttö jommalla kummalla värillä (vihreä tai punainen). Punainen aloittaa pelin.

        private void piirra(JPanel ruutu){

        	if((vuorolaskuri%2) == 0)
                        ruutu.setBackground(Color.green);
                else
                        ruutu.setBackground(Color.red); 
        }

	// Voittorivi maalataan keltaiseksi. Pelilauta - luokassa voittorivin ruutujen arvo laitetaan kolmeksi, 
	// joka vastaa keltaista väriä.

        private void piirraVoittorivi() {

        	for(int i = 0; i < taulukko.length; ++i) {
			for(int j=0; j < taulukko[i].length; ++j) {
                		if (pelialue.apuruudukko[i][j].mikaVari()==3)
					taulukko[i][j].setBackground(Color.yellow);
                	}
        	}

        }


        public void mouseClicked(MouseEvent e) {

		// Kun toinen voittaa, ruutuja ei voi enää täyttää.

        	if(voittomuuttuja)
        		return;

		// Etsitään taulukosta klikattu paneeli. Jos se ei ole täynnä, lisätään vuorolaskuria yhdellä
		// ja ruutu täytetään kyseisen arvon mukaan.

        	for(int i = 0; i < taulukko.length; ++i) {
        		for(int j=0; j < taulukko[i].length; ++j) {
        			if(e.getSource() == taulukko[i][j]) {
        				if(!pelialue.apuruudukko[i][j].onkoTaynna()) {
        					++vuorolaskuri;
        						if(vuorolaskuri%2 == 0)
								pelialue.apuruudukko[i][j].tayta(2);
							else
								pelialue.apuruudukko[i][j].tayta(1);

        						piirra(taulukko[i][j]);

				// Jokaisella ruudun täytöllä tarkistetaan voittaja.

				if(pelialue.tarkistaVoitto(i,j)){
					voittomuuttuja=true;
					piirraVoittorivi();
					ilmoitusTeksti("voitto");
				}

				// Tarkistetaan tasapeli, ja optimoidaan vähän niin ei
				// tarvitse joka kerta tarkistaa ( alussa tasapeli ei 
                             	// ole mitenkään mahdollinen ).

				if ( vuorolaskuri >= (taulukko.length/2)){
					if(pelialue.tarkistaTasapeli()){

					ilmoitusTeksti("tasuri");
	
				}
                                }
        				}

        			}
        		}
        	}
 
                peli.setVisible(true);
        }
        
        
        public void mousePressed(MouseEvent e){

        }
        
        
        public void mouseEntered(MouseEvent e){
 
        }
        
        
        public void mouseExited(MouseEvent e){

        }
        
        
        public void mouseReleased(MouseEvent e){

        }


	public static void main(String[] args){
	
		JätkänshakkiGUI ristinolla = new JätkänshakkiGUI();
               
            }

}

  