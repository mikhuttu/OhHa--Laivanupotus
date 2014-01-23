package Ohjaus;
import Sovelluslogiikka.Pelaaja;
import Tyokalut.Lukija;

public class Peli {
    private Pelaaja kayttaja;
    private Pelaaja tietokone;
    private Lukija lukija;
    
    private int kayttajaanOsuneet;
    private int tietokoneeseenOsuneet;
    
    public Peli(Lukija lukija) {
        this.kayttaja = new Pelaaja();
        this.tietokone = new Pelaaja();
        this.lukija = lukija;
        
        this.kayttajaanOsuneet = 0;
        this.tietokoneeseenOsuneet = 0;
    }
    
    public Pelaaja getKayttaja() {
        return this.kayttaja;
    }
}