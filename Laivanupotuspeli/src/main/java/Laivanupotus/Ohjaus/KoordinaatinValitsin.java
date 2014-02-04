package Laivanupotus.Ohjaus;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Tyokalut.Lukija;

/**
 * Luokan ainoa toiminnallisuus on toimia metodina, jota voidaan kutsua eri luokista.
 */

public class KoordinaatinValitsin {
    private Pelilauta pelilauta;
    private Lukija lukija;
    
    public KoordinaatinValitsin(Lukija lukija, Pelilauta pelilauta) {
        this.lukija = lukija;
        this.pelilauta = pelilauta;
    }
    
/**
 * Pelaaja valitsee luvun väliltä 0 ja pelilauta.getKoko() ja metodi palauttaa sen.
 * @param XtaiY valittava koordinaatti
 * @return palauttaa x tai y koordinaatin
 */    
    public int valitseKoordinaatti(char XtaiY) {
        while (true) {
            
            System.out.print(XtaiY + " : ");
            int koko = this.pelilauta.getKoko();
            try {
                int luku = lukija.seuraavaRiviKokonaislukuna();
                if (luku < 0 || luku >= koko) {
                    System.out.println("Valitse luku väliltä 0-" + (koko-1));
                }
                else {
                    return luku;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Valitse luku väliltä 0-" + (koko-1));
            }
        }
    }
}
