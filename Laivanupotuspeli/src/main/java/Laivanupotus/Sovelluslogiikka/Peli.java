package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Lukija;

public class Peli {
    private Kayttaja pelaaja;
    private Kayttaja tietokone;
 
    public Peli(Aly aly, Lukija lukija) {
        this.pelaaja = new Pelaaja(lukija);
        this.tietokone = new Tietokone(aly);
    }
    
    public Kayttaja getPelaaja() {
        return this.pelaaja;
    }
    
    public Kayttaja getTietokone() {
        return this.tietokone;
    }
    
    public boolean suoritaVuoro(Kayttaja kayttaja) throws IllegalArgumentException {
        if (kayttaja.equals(pelaaja)) {
            return this.pelaaja.suoritaVuoro(this.tietokone.getPelilauta());
        }
        else {
            return this.tietokone.suoritaVuoro(this.pelaaja.getPelilauta());
        } 
    }
    
    public boolean jatketaanko() {
        if (this.pelaaja.getOsuneet() == 12 || this.tietokone.getOsuneet() == 12) {
            return false;
        }
        return true;
    }
}