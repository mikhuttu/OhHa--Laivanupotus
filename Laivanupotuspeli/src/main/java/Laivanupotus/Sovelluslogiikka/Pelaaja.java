package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Ohjaus.KoordinaatinValitsin;
import Laivanupotus.Tyokalut.Lukija;

/**
 * Pelaaja on Kayttaja, joten se toteuttaa "ammu" ja "suoritaVuoro" metodit. 
 * Luokka pyrkii kuvaamaan oikean maailman pelaajaa, jolla on tässä tapauksessa oma pelilautansa ja
 * Pelaaja voi suorittaa vuoron ampumalla vastustajansa pelilautaan.
 */

public class Pelaaja extends Kayttaja {
    private Pelilauta pelilauta;
    private Lukija lukija;
    
    /**
     * Pelaajalla on kaksi eri konstruktoria, sillä Tietokone tuntee Pelaajan ja osaa tätä kautta hakea pelilautansa.
     * Tietokone ei kuitenkaan tarvitse Lukija -oliota toisin kuin se "tämä" pelaaja, joka käyttää sitä kysyessään
     * arvoja ammuttavaa sijaintia varten.
     * 
     * @param lukija 
     */    
    
    public Pelaaja() {
        this.pelilauta = new Pelilauta();
    }
    
    public Pelaaja(Lukija lukija) {
        this();
        this.lukija = lukija;
    }
    
    @Override
    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }
    
    public Lukija getLukija() {
        return this.lukija;
    }
    
    /**
     * Pelaaja suorittaa vuoron ampulla vastustajan pelilautaan.
     * Ensin pyydetään valitsemaan ammuttava sijainti, ja tämän jälkeen yritetään ampua siihen.
     * 
     * Mikäli ampuessa osutaan vastustajan johonkin laivaan, Kayttaja-luokassa määritettyä arvoa
     * "vastustajaanOsuneet" kasvatetaan ja palautetaan tieto siitä että osuttiin (, jotta saadaan
     * jatkaa myös seuraavalla vuorolla, sillä laivanupotus toimii näin).
     * 
     * @param vastustajanLauta
     * @return
     * @throws IllegalArgumentException Ampuminen epäonnistuu, koska sijaintiin on jo ammuttu.
     */
    
    public boolean suoritaVuoroAlkuperainen(Pelilauta vastustajanLauta) throws IllegalArgumentException {
        System.out.println("Valitse ammuttava koordinaatti.");
        Sijainti sijainti = valitseAmmuttavaSijainti(vastustajanLauta);
        
        boolean osuiko = false;
        
        if (ammu(vastustajanLauta, sijainti)) {
            kasvataVastustajaanOsuneet();
            osuiko = true;
        }
        return osuiko;
    }
    
    public boolean suoritaVuoro(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        boolean osuiko = false;
        
        if (ammu(vastustajanLauta, sijainti)) {
            kasvataVastustajaanOsuneet();
            osuiko = true;
        }
        return osuiko;
    }    
    
    /**
     * Palauttaa ammuttavan sijainnin "suoritaVuoro" metodille.
     * 
     * @param pelilauta
     * @return 
     */
    
    private Sijainti valitseAmmuttavaSijainti(Pelilauta pelilauta) {
        KoordinaatinValitsin valitsin = new KoordinaatinValitsin(this.lukija, pelilauta);
        
        int x = valitsin.valitseKoordinaatti('X');
        System.out.println();
        int y = valitsin.valitseKoordinaatti('Y');
        System.out.println();
        
        return new Sijainti (x,y);
    }
    
    /**
     * Metodissa vastustajan pelilaudalla oleva sijainti muutetaan ammutuksi (mikäli siihen ei ole jo
     * ammuttu jolloin palautetaan virheilmoitus eteenpäin).
     * 
     * Metodi palauttaa "true" mikäli ampuessa osuttiin vastustajan johonkin laivaan, muuten false.
     * 
     * @param vastustajanLauta
     * @param sijainti
     * @return
     * @throws IllegalArgumentException 
     */
    
    @Override
    public boolean ammu(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        if (sijainti == null) {
            throw new IllegalArgumentException();
        }
        
        if(vastustajanLauta.onkoRuutuunAmmuttu(sijainti)) {
            throw new IllegalArgumentException();
        }
        vastustajanLauta.muutaAmmutuksi(sijainti);
        
        if (vastustajanLauta.osuikoLaivaan(sijainti)) {
            return true;
        }
        return false;
    }
}