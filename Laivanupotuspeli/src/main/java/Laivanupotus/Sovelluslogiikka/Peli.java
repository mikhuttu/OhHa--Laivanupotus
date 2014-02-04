package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Lukija;

/**
 * Pelin lopullinen käyttötarkoitus on yhä vähän "auki", mutta tällä hetkellä Peli
 * toimii linkkinä eri luokkien välillä siten että peli tuntee käyttäjät ja pyytää
 * käyttäjiä suorittamaan vuorojaan.
 * 
 * Lisäksi peli luokka tietää, tuleeko peliä vielä jatkaa vai ei.
 */

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
    
    /**
     * Pyytää parametrinaan saavaa käyttäjää suorittamaan vuoronsa.
     * Palauttaa "true" tai "false" sen mukaan, osuiko käyttäjä vuoroa suorittaessaan (= ampuessa) laivaan vai ei.
     * 
     * @param kayttaja
     * @return
     * @throws IllegalArgumentException 
     */
    
    public boolean suoritaVuoro(Kayttaja kayttaja) throws IllegalArgumentException {
        if (kayttaja.equals(pelaaja)) {
            return this.pelaaja.suoritaVuoro(this.tietokone.getPelilauta());
        }
        else {
            return this.tietokone.suoritaVuoro(this.pelaaja.getPelilauta());
        } 
    }
    
    /**
     * Palauttaa false mikäli pelin tulee päättyä.
     * @return 
     */
    
    public boolean jatketaanko() {
        if (this.pelaaja.getOsuneet() == 12 || this.tietokone.getOsuneet() == 12) {
            return false;
        }
        return true;
    }
}