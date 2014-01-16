package Ohjaus;
import Sovelluslogiikka.Pelaaja;

public class Peli {
    private Pelaaja kayttaja;
    private Pelaaja tietokone;
    private int kayttajaanOsuneet;
    private int tietokoneeseenOsuneet;
    
    public Peli() {
        this.kayttaja = new Pelaaja();
        this.tietokone = new Pelaaja();
        this.kayttajaanOsuneet = 0;
        this.tietokoneeseenOsuneet = 0;
    }
}
