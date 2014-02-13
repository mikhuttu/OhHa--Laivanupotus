package Laivanupotus.Kayttoliittyma.Alaosa;

import java.util.ArrayList;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Sovelluslogiikka.Tietokone;
import Laivanupotus.Tyokalut.Sijainti;


public class PelilaudanPiirtaja {
    Kayttaja kayttaja;
    
    public PelilaudanPiirtaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }
    
    private ArrayList<Sijainti> haeLaivojenOsienSijainnit() {
        Pelilauta pelilauta = this.kayttaja.getPelilauta();
        return pelilauta.haeLaivojenOsienSijainnit();
    }
        
    public char palautaSeuraavaMerkki(Sijainti sijainti) {
        
        ArrayList<Sijainti> laivojenSijainnit = haeLaivojenOsienSijainnit();
        return seuraavaMerkki(sijainti, laivojenSijainnit);
    }    
    
    private char seuraavaMerkki(Sijainti sijainti, ArrayList<Sijainti> laivojenSijainnit) {
        Pelilauta pelilauta = kayttaja.getPelilauta();
        
        Ruutu ruutu = pelilauta.haeRuutu(sijainti);
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
        Pelilauta pelilauta = this.kayttaja.getPelilauta();
        
        for (Laiva laiva : pelilauta.getLaivat()) {
            if (laiva.osuiko(sijainti)) {
                return pelilauta.onkoTuhottu(laiva);
            }
        }
        throw new IllegalArgumentException();
    }
}