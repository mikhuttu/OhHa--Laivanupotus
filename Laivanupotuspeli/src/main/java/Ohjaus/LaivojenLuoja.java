package Ohjaus;

import Sovelluslogiikka.Kayttaja;
import Sovelluslogiikka.Laiva;
import Sovelluslogiikka.Pelilauta;
import Sovelluslogiikka.Sijainti;
import Tyokalut.Lukija;
import Tyokalut.Suunta;
import java.util.Random;

public class LaivojenLuoja {
    private Lukija lukija;
    private Peli peli;
    
    public LaivojenLuoja(Peli peli) {
        this.lukija = peli.getLukija();
        this.peli = peli;
    }
    
    public void asetaPelaajanLaivat() {
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
                luoKayttajanLaudalleLaiva(this.peli.getPelaaja(), suunta, Xsij, Ysij, koko);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Tähän paikkaan ei voi luoda laivaa.\n");
                i--;
            }
            
            PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(this.peli.getPelaaja());
            piirtaja.piirraPelilauta();
        }
    }
    
    public void asetaTietokoneenLaivat() {
        Random random = new Random();
        
        for (int i = 1; i <= 4; i++) {
            
            int koko = haeKoko(i);
            Suunta suunta = Suunta.ALAS;
            
            if (random.nextInt(2) == 1) {
                suunta = Suunta.OIKEALLE;
            }
            
            int laudanKoko = this.peli.getTietokone().getPelilauta().getKoko();
            
            int Xsij = random.nextInt(laudanKoko - 1);
            int Ysij = random.nextInt(laudanKoko - 1);
            
            try {
                luoKayttajanLaudalleLaiva(this.peli.getTietokone(), suunta, Xsij, Ysij, koko);
            }
            catch (IllegalArgumentException e) {
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
                int s = this.lukija.seuraavaRiviKokonaislukuna();
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
        
        KoordinaatinValitsin valitsin = new KoordinaatinValitsin(this.lukija, this.peli.getPelaaja().getPelilauta());
        return valitsin.valitseKoordinaatti(XtaiY);
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
    
    private void luoKayttajanLaudalleLaiva(Kayttaja kayttaja, Suunta suunta, int Xsij, int Ysij, int koko) throws IllegalArgumentException {
        Laiva laiva = new Laiva(new Sijainti(Xsij, Ysij), suunta, koko);
        Pelilauta pelilauta = kayttaja.getPelilauta();  
        pelilauta.asetaLaiva(laiva);
    }
}