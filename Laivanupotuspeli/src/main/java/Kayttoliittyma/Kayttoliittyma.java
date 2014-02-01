package Kayttoliittyma;

import Ohjaus.LaivojenLuoja;
import Ohjaus.Peli;
import Ohjaus.PelilaudanPiirtaja;
import Sovelluslogiikka.Kayttaja;
import Sovelluslogiikka.tietokonealy.Aly;
import Tyokalut.Lukija;

public class Kayttoliittyma implements Runnable {
    private Peli peli;
    private Lukija lukija;
    
    @Override
    public void run() {
        tulostaAloitusNakyma();
        this.lukija = new Lukija();
        this.peli = new Peli(valitseTietokoneAly(), this.lukija);
       
        kaynnista();
    }
    
    private void tulostaAloitusNakyma() {
        System.out.println("------------------");
        System.out.println(" LAIVANUPOTUSPELI ");
        System.out.println("------------------");
        
        System.out.println("\nLuodaan laivat... Seuraa ohjeita.");
    }
    
    private Aly valitseTietokoneAly() {
        while (true) {
            System.out.println("\nValitse tietokoneen älykkyys:\n(1) Sattuma\n(2) Cheater\n");
        
            try {
                int luku = this.lukija.seuraavaRiviKokonaislukuna();
                if (luku == 1) {
                    return Aly.ARPA;
                }
                else if (luku == 2) {
                    return Aly.CHEATER;
                }
                System.out.println("Syötä joko '1' tai '2'");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Syötä joko '1' tai '2'");
            }
        }
        
    }
    
    private void asetaKayttajienLaivat() {
        LaivojenLuoja luoja = new LaivojenLuoja(this.peli);
        luoja.asetaPelaajanLaivat();
        luoja.asetaTietokoneenLaivat();
    }

    private void piirraPelilauta(Kayttaja kayttaja) {
        PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(kayttaja);
        piirtaja.piirraPelilauta();
    }
    
    public void kaynnista() {
        
        piirraPelilauta(this.peli.getPelaaja());
        asetaKayttajienLaivat();
        String tuloste = "HÄVISIT!!! :P";
        
        while (true) {
            if(!seuraavaKierros(this.peli.getPelaaja())) {
                tuloste = "VOITIT PELIN!!!";
                break;
            }
            System.out.println("(Tietokoneen vuoro...)\n");
            if (!seuraavaKierros(this.peli.getTietokone())) {
                break;
            }
        }
        System.out.println(tuloste);
    }
    
    private boolean seuraavaKierros(Kayttaja kayttaja) {
        while (true) {
            try {
                this.peli.suoritaVuoro(kayttaja);
                break;
            }
            catch (IllegalArgumentException e) {    // suoritetaan mikäli ammutaan jo ammuttuun ruutuun.
            }  
        }
        
        System.out.println("Pelaajan pelilauta:\n");
        piirraPelilauta(this.peli.getPelaaja());
        
        System.out.println("Tietokoneen pelilauta:\n");
        piirraPelilauta(this.peli.getTietokone());
        
        return this.peli.jatketaanko();
    }
}
