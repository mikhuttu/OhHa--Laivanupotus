package Laivanupotus.Sovelluslogiikka;

/**
 * Kayttaja on abstrakti luokka, jonka metodit sekä Pelaaja että Tietokone toteuttavat.
 * Käyttäjä tietää, kuinka monta kertaan vastustajan laivoihin on osuttu (max. 12, sillä silloin ne kaikkin on jo tuhottu).
 */
public abstract class Kayttaja {
    private int vastustajaanOsuneet;
    
    public abstract boolean ammu(Pelilauta vastustajanLauta, Sijainti sijainti);
    public abstract boolean suoritaVuoro(Pelilauta vastustajanLauta);
    public abstract Pelilauta getPelilauta();
    
    public Kayttaja() {
        this.vastustajaanOsuneet = 0;
    }
    
    public int getOsuneet() {
        return this.vastustajaanOsuneet;
    }
    
    public void kasvataVastustajaanOsuneet() {
        this.vastustajaanOsuneet++;
    }
}