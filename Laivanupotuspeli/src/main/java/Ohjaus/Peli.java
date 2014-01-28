package Ohjaus;
import Sovelluslogiikka.Pelaaja;
import Sovelluslogiikka.Pelilauta;
import Sovelluslogiikka.Sijainti;
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
    
    public Pelaaja getTietokone() {
        return this.tietokone;
    }
    
    public void suoritaPelaajanVuoro() throws IllegalArgumentException {
        System.out.println("Valitse ammuttava koordinaatti.");
        Pelilauta pelilauta = this.kayttaja.getPelilauta();         // ampuu omaansa
        Sijainti sijainti = valitseAmmuttavaSijainti(pelilauta);
        
        if (this.kayttaja.ammu(pelilauta, sijainti)) {      
            kasvataKayttajaanOsuneita();
        }
    }
    
    private Sijainti valitseAmmuttavaSijainti(Pelilauta pelilauta) throws IllegalArgumentException {
        KoordinaatinValitsin valitsin = new KoordinaatinValitsin(this.lukija, pelilauta);
        
        int x = valitsin.valitseKoordinaatti('X');
        System.out.println();
        int y = valitsin.valitseKoordinaatti('Y');
        System.out.println();
        
        return new Sijainti (x,y);
    }
    
    public boolean jatketaanko() {
        if (this.kayttajaanOsuneet == 12 || this.tietokoneeseenOsuneet == 12) {
            return false;
        }
        return true;
    }
    
    public void kasvataKayttajaanOsuneita() {
        this.kayttajaanOsuneet++;
    }
}