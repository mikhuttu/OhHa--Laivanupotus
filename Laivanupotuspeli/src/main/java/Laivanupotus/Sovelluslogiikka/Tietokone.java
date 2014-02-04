package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Alykkyys;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Easy;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Impossible;

/**
 * Tietokone toimii hyvin paljon samalla tavalla kuin Pelaaja luokkakin.
 * Eroavaisuudet luokkien välillä ovat siinä, että tietokoneella on toteutettu tekoäly, ja tietokone ei siis
 * tietenkään kysy pelin pelaajalta syötteitä ammuttavista koordinaateista, vaan määrittää nämä tekoälynsä kautta itse.
 * 
 * Tietokone on siis pelin "vastustaja".
 */

public class Tietokone extends Kayttaja {
    private Aly aly;
    private Pelaaja pelaaja;
    
    public Tietokone(Aly aly) {
        this.aly = aly;
        this.pelaaja = new Pelaaja();
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    @Override
    public Pelilauta getPelilauta() {
        return this.pelaaja.getPelilauta();
    }
    
    /**
     * Vuoron suoritus tapahtuu siten että määritetään ammuttava sijainti (jonkun algoritmin avulla), ja
     * ammutaan tähän.
     * 
     * Kuten myös Pelaaja luokassa, mikäli ampuessa osutaan vastustajan johonkin laivaan, Kayttaja-luokassa
     * määritettyä arvoa "vastustajaanOsuneet" kasvatetaan ja palautetaan tieto siitä että osuttiin,
     * jotta tietokone saa jatkaa myös seuraavalla vuorolla (, sillä laivanupotus toimii näin).
     * 
     * @param vastustajanLauta
     * @return
     * @throws IllegalArgumentException 
     */
    
    @Override
    public boolean suoritaVuoro(Pelilauta vastustajanLauta) throws IllegalArgumentException {
        boolean osuiko = false;
        Sijainti sijainti = maaritaSijainti(vastustajanLauta);
        
        if (ammu(vastustajanLauta, sijainti)) {
            kasvataVastustajaanOsuneet();
            osuiko = true;
        }
        return osuiko;
    }
    
    /**
     * Riippuen Tietokoneen alykkyydestä, määritetään ammuttava sijainti ja palautetaan se.
     * 
     * @param vastustajanLauta
     * @return 
     */
    
    private Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Alykkyys alykkyys = new Easy();
        
        if (this.aly == Aly.IMPOSSIBLE) {
            alykkyys = new Impossible();
        }

        return alykkyys.maaritaSijainti(vastustajanLauta);
    }
    
    /**
     * Metodissa pelaajan pelilaudalla oleva sijainti muutetaan ammutuksi (mikäli siihen ei ole jo
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