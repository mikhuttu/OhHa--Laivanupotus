package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Tyokalut.Sijainti;

/**
 * Kayttaja on abstrakti luokka, joka kuvaa pelin pelaajaa, mutta myös tietokonetta.
 * Tietokone perii Kayttaja -luokan ja sillä on tämän luokan metodien lisäksi tekoäly, jota se käyttää ammuttavien sijaintien määrittämiseen.
 * Käyttäjä tietää, kuinka monta kertaan vastustajan laivoihin on osuttu (max. 12, sillä silloin ne kaikkin on jo tuhottu).
 */

public class Kayttaja {
    private final Pelilauta pelilauta;
    private int vastustajaanOsuneet;
    
    public Kayttaja() {
        this.vastustajaanOsuneet = 0;
        this.pelilauta = new Pelilauta();
    }
    
    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }
    
    public int getOsuneet() {
        return this.vastustajaanOsuneet;
    }
    
    public void kasvataVastustajaanOsuneet() {
        this.vastustajaanOsuneet++;
    }
    
    /**
    * Kayttaja suorittaa vuoronsa ampumalla vastustajan pelilautaan.
    * Mikäli ampuessa osutaan vastustajan johonkin laivaan, Kayttaja-luokassa määritettyä arvoa
    * "vastustajaanOsuneet" kasvatetaan ja palautetaan tieto siitä että osuttiin (jotta saadaan
    * jatkaa myös seuraavalla vuorolla, sillä laivanupotus toimii näin).
    * 
    * @param vastustajanLauta
    * @param sijainti
    * @return
    * @throws IllegalArgumentException Ampuminen epäonnistuu, koska sijaintiin on jo ammuttu, tai se on null.
    */
        
    public boolean suoritaVuoro(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        
        if (ammu(vastustajanLauta, sijainti)) {
            kasvataVastustajaanOsuneet();
            return true;
        }
        return false;
    }
    
    
    /**
     * Metodissa vastustajan pelilaudalla oleva sijainti muutetaan ammutuksi (mikäli siihen ei ole jo
     * ammuttu jolloin palautetaan virheilmoitus eteenpäin).
     * 
     * @param vastustajanLauta
     * @param sijainti
     * @return palauttaa "true" mikäli ampuessa osuttiin vastustajan johonkin laivaan, muuten "false".
     * @throws IllegalArgumentException 
     */
    
    private boolean ammu(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        if (sijainti == null || vastustajanLauta.onkoRuutuunAmmuttu(sijainti)) {
            throw new IllegalArgumentException();
        }
        
        vastustajanLauta.muutaAmmutuksi(sijainti);
        
        return vastustajanLauta.osuikoLaivaan(sijainti);
    }
}