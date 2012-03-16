// 4. harjoitukset, tehtävä 3.1, Hanna Hirvonen


import java.util.Scanner;

public class Kahvikirjanpito {

    public static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {

        Varasto kahvi = new Varasto(3.0, 2.0);

	int vastaus = 0;
        System.out.println("Kahvikassa");
        System.out.println("1\tJuo kahvi");
        System.out.println("0\tPoistu\n");
	System.out.println(tilannetiedotus(kahvi));
	System.out.print("Valitse toiminto: ");
	vastaus = lukija.nextInt();
		while (vastaus != 0) {
			if (vastaus == 1) {
				kahvi.otaVarastosta(1.0);

			System.out.println(tilannetiedotus(kahvi));
			}
		System.out.print("Valitse toiminto: ");
		vastaus = lukija.nextInt();
		}

    }

    private static String tilannetiedotus(Varasto v) {
        return ("Kahvia on jäljellä "+v.getSaldo()+"/"+v.getTilavuus() );
    }
}




