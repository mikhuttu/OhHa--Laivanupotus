package Ohjaus;

import Sovelluslogiikka.Kayttaja;
import Sovelluslogiikka.Pelaaja;
import Sovelluslogiikka.Tietokone;
import Sovelluslogiikka.tietokonealy.Aly;
import Tyokalut.Lukija;

public class Peli {
    private Kayttaja pelaaja;
    private Kayttaja tietokone;
    private Lukija lukija;
    
    public Peli(Aly aly, Lukija lukija) {
        this.lukija = lukija;
        this.pelaaja = new Pelaaja(lukija);
        this.tietokone = new Tietokone(aly);
    }
    
    public Kayttaja getPelaaja() {
        return this.pelaaja;
    }
    
    public Kayttaja getTietokone() {
        return this.tietokone;
    }
    
    public Lukija getLukija() {
        return this.lukija;
    }
    
    public void suoritaVuoro(Kayttaja kayttaja) throws IllegalArgumentException {
        if (kayttaja.equals(pelaaja)) {
            this.pelaaja.suoritaVuoro(this.tietokone.getPelilauta());
        }
        else {
            this.tietokone.suoritaVuoro(this.pelaaja.getPelilauta());
        } 
    }
    
    public boolean jatketaanko() {
        if (this.pelaaja.getOsuneet() == 12 || this.tietokone.getOsuneet() == 12) {
            return false;
        }
        return true;
    }
}