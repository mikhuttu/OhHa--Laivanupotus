package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;

/**
 * Peli -luokka tietää molemmat Kayttajat, ja tätä tietoa Käyttöliittymä hyödyntää.
 * Lisäksi peli luokka tietää, tuleeko peliä vielä jatkaa vai ei.
 */

public class Peli {
    private final Kayttaja pelaaja;
    private final Kayttaja tietokone;
 
    public Peli(Aly aly) {
        this.pelaaja = new Kayttaja();
        this.tietokone = new Tietokone(aly);
    }
    
    public Kayttaja getPelaaja() {
        return this.pelaaja;
    }
    
    public Kayttaja getTietokone() {
        return this.tietokone;
    }
    
    /**
     * Palauttaa false mikäli pelin tulee päättyä.
     * @return 
     */
    
    public boolean jatketaanko() {
        return Math.max(this.pelaaja.getOsuneet(), this.tietokone.getOsuneet()) < 12;
    }
}