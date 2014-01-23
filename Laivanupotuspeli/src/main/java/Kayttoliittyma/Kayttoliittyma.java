package Kayttoliittyma;
import Ohjaus.*;
 

public class Kayttoliittyma  {
    private Peli peli;
    private Lukija lukija;
    
    public void run() {
        tulostaOhjeet();
        this.lukija = new Lukija();
        this.peli = new Peli(this.lukija);
        
        asetaKayttajanLaivat();
        kaynnista();
    }
    
    private void tulostaOhjeet() {
        System.out.println("--------------------");
        System.out.println(" ------------------");
        System.out.println("  LAIVANUPOTUSPELI");
        System.out.println(" ------------------");
        System.out.println("--------------------");
        
        System.out.println("\nLuodaan laivat... Seuraa ohjeita.\n");
    }
    
    private void asetaKayttajanLaivat() {
        LaivojenLuoja luoja = new LaivojenLuoja(this.peli);

        for (int i = 1; i <= 4; i++) {
            
            int koko = haeKoko(i);
            System.out.println(i + ". laivan koko: " + koko + "\n");
            
            Suunta suunta = valitseSuunta(i);
            System.out.println();
            int Xsij = valitseKoordinaatti('X');
            System.out.println();
            int Ysij = valitseKoordinaatti('Y');
            System.out.println();

            try {
                luoja.luoKayttajanLaudalleLaiva(suunta, Xsij, Ysij, koko);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Tähän paikkaan ei voi luoda laivaa.\n");
                i--;
            }
        }
    }
    
    private Suunta valitseSuunta(int i) {
        
        System.out.println("Valitse " + i + ". laivan suunta.\n0 = Alas\n1 = Oikealle\n");
        Suunta suunta = Suunta.ALAS;
        
        while (true) {
            System.out.print("Suunta: ");
            
            try {
                int s = lukija.seuraavaRiviKokonaislukuna();
                if (s == 0 || s == 1) {
                    if (s == 1) {
                        suunta = Suunta.OIKEALLE;
                    }
                    break;
                }
                System.out.println("Valitse suunnaksi 0 (Alas) tai 1 (Oikealle)");     
            }
            
            catch (IllegalArgumentException e) {
                System.out.println("Valitse suunnaksi 0 (Alas) tai 1 (Oikealle)");
            }
        }

        return suunta;
    }
    
    private int valitseKoordinaatti(char XtaiY) {
        
        System.out.println("Valitse laivan " + XtaiY + " koordinaatin sijainti.");
        
        while (true) {
            System.out.print(XtaiY + " : ");
            
            try {
                int luku = lukija.seuraavaRiviKokonaislukuna();
                if (luku < 0) {
                    System.out.println("Valitse positiivinen kokonaisluku.");
                }
                else {
                    return luku;
                }
            }
            catch (IllegalArgumentException e) {
                System.out.println("Valitse positiivinen kokonaisluku.");
            }
        }
    }
    
    private int haeKoko(int i) {
        int koko = 4;
            
        if (i > 1 && i < 4) {
            koko = 3;
        }
        else if (i == 4) {
            koko = 2;
        }
        return koko;
    }
    
    
    public void kaynnista() {
        piirraPelilauta();
    }
    
    private void piirraPelilauta() {
        
    }
}
