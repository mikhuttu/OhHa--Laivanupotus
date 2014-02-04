package Laivanupotus.Kayttoliittyma;

import Laivanupotus.Ohjaus.LaivojenLuoja;
import Laivanupotus.Sovelluslogiikka.Peli;
import Laivanupotus.Ohjaus.PelilaudanPiirtaja;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Lukija;

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
            System.out.println("\nValitse vaikeusaste:\n(1) Easy\n(2) Impossible\n");
        
            try {
                int luku = this.lukija.seuraavaRiviKokonaislukuna();
                System.out.println();
                
                if (luku == 1) {
                    return Aly.EASY;
                }
                else if (luku == 2) {
                    return Aly.IMPOSSIBLE;
                }
                System.out.println("Syötä joko '1' tai '2'");
            }
            catch (IllegalArgumentException e) {
                System.out.println("\nSyötä joko '1' tai '2'");
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
        
        System.out.println("Pelaajan pelilauta:\n");
        piirraPelilauta(this.peli.getPelaaja());
        
        asetaKayttajienLaivat();
        
        System.out.println("Tietokoneen pelilauta:\n");
        piirraPelilauta(this.peli.getTietokone());
        
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
        boolean osuikoVastustajanLaivaan = false;
        
        while (true) {  // mahdollisuus ampua monesti peräkäin.
            
            while (true) {
                try {
                    osuikoVastustajanLaivaan = this.peli.suoritaVuoro(kayttaja);
                    break;
                }
                catch (IllegalArgumentException e) {    // suoritetaan mikäli ammutaan jo ammuttuun ruutuun.
                }
            }
        
            if (kayttaja.getClass() == this.peli.getPelaaja().getClass()) {
                System.out.println("Tietokoneen pelilauta:\n");
                piirraPelilauta(this.peli.getTietokone());
            }
        
            else {
                System.out.println("Pelaajan pelilauta:\n");
                piirraPelilauta(this.peli.getPelaaja());
            }

            nuku();
            
            if (!this.peli.jatketaanko()) {
                return false;
            }
            
            if (!osuikoVastustajanLaivaan) {
                break;
            }
        }

        return true;
    }
    
    private void nuku() {
        try {
            Thread.sleep(300);
        }
        
        catch (InterruptedException ie) {
        }
    }
}
