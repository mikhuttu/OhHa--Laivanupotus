package Ohjaus;
import Sovelluslogiikka.Pelilauta;
import Tyokalut.Lukija;

public class KoordinaatinValitsin {
    private Pelilauta pelilauta;
    private Lukija lukija;
    
    public KoordinaatinValitsin(Lukija lukija, Pelilauta pelilauta) {
        this.lukija = lukija;
        this.pelilauta = pelilauta;
    }
    
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
