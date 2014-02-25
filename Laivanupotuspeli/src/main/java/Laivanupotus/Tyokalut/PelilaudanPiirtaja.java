package Laivanupotus.Tyokalut;

import java.util.ArrayList;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Sovelluslogiikka.Tietokone;

/**
 *  Tämän luokan tarkoitus oli alun perin piirtää tekstikäyttöliittymälle pelilaudat, mutta päädyin pitämään sen mukana,
 *  sillä sen avulla sain graafiseen käyttöliittymään siirtyessä määritettyä helposti sen, minkä värinen JButton -olion,
 *  joka vastaa jotakin pelilaudan ruutua, pitäisi olla.
 * 
 *  Luokka siis palauttaa merkin (char -arvon), jota GraafinenPelilauta hyödyntää.
 */

public class PelilaudanPiirtaja {
    Kayttaja kayttaja;
    
    public PelilaudanPiirtaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public char palautaSeuraavaMerkki(Sijainti sijainti) {
        
        ArrayList<Sijainti> laivojenSijainnit = kayttaja.getPelilauta().haeLaivojenOsienSijainnit();
        return seuraavaMerkki(sijainti, laivojenSijainnit);
    }    
    
    /**
     * Selvittää mikä on se merkki, mikä liittyy pelilaudalla olevaan ruutuun jolla on Sijainti sijainti, ja
     * palauttaa sen.
     * 
     * @param sijainti
     * @param laivojenSijainnit
     * @return 
     */
    private char seuraavaMerkki(Sijainti sijainti, ArrayList<Sijainti> laivojenSijainnit) {
        
        Ruutu ruutu = kayttaja.getPelilauta().haeRuutu(sijainti);
        char tulostettava = '~';

        if (!tulostuksessaTietokoneenLauta()) {
            if (ruudussaOnLaiva(sijainti, laivojenSijainnit)) {
                tulostettava = 'O';
            }
        }
        
        if (ruutu.onkoAmmuttu()) {
            tulostettava = 'x';
                
            if (ruudussaOnLaiva(sijainti, laivojenSijainnit)) {
                tulostettava = 'X';
                    
                if(onkoSijainnissaOlevaLaivaTuhottu(sijainti)) {
                    tulostettava = 'L';
                }

            }
        }
        
        return tulostettava;
    }
    
    private boolean tulostuksessaTietokoneenLauta() {
        if (kayttaja == null) {     // halutaan nähdä pelaajan lauta Hard-moden kautta.
            return true;
        }
        
        return kayttaja.getClass() == Tietokone.class;
    }
    
    private boolean ruudussaOnLaiva(Sijainti sijainti, ArrayList<Sijainti> laivojenSijainnit) {
        for (Sijainti sij : laivojenSijainnit) {
            if (sij.equals(sijainti)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean onkoSijainnissaOlevaLaivaTuhottu(Sijainti sijainti) throws IllegalArgumentException {
        Pelilauta pelilauta = kayttaja.getPelilauta();
        
        for (Laiva laiva : pelilauta.getLaivat()) {
            if (laiva.osuiko(sijainti)) {
                return pelilauta.onkoTuhottu(laiva);
            }
        }
        throw new IllegalArgumentException();
    }
}