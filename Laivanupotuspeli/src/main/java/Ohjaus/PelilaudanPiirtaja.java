package Ohjaus;

import Sovelluslogiikka.Kayttaja;
import Sovelluslogiikka.Laiva;
import Sovelluslogiikka.Ruutu;
import Sovelluslogiikka.Sijainti;
import java.util.ArrayList;

public class PelilaudanPiirtaja {
    Kayttaja kayttaja;
    
    public PelilaudanPiirtaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }
    
    public void piirraPelilauta() {
        int koko = this.kayttaja.getPelilauta().getKoko();
        ArrayList<Sijainti> laivojenSijainnit = haeLaivojenOsienSijainnit();
        
        for (int y = 0; y < koko; y++) {
            String seuraavaRivi = "";
            for (int x = 0; x < koko; x++) {
                seuraavaRivi += seuraavaMerkki(new Sijainti(x,y), laivojenSijainnit);
            }
            System.out.println(seuraavaRivi);
        }
        System.out.println();
    }
    
    private ArrayList<Sijainti> haeLaivojenOsienSijainnit() {
        ArrayList<Sijainti> laivojenSijainnit = new ArrayList<Sijainti>();
        
        for (int i = 0; i < this.kayttaja.getPelilauta().getLaivat().size(); i++) {
            Laiva laiva = this.kayttaja.getPelilauta().getLaivat().get(i);
            
            for (int j = 0; j < laiva.getKoko(); j++) {     
                laivojenSijainnit.add(laiva.haeLaivanOsanSijainti(j));
            }
        }
        return laivojenSijainnit;
    }
    
    private char seuraavaMerkki(Sijainti sijainti, ArrayList<Sijainti> laivojenSijainnit) {
        Ruutu ruutu = this.kayttaja.getPelilauta().haeRuutu(sijainti);
        char tulostettava = '~';
        
        if (ruutu.onkoAmmuttu()) {
            tulostettava = 'x';
        }
        else {
            for (Sijainti sij : laivojenSijainnit) {
                if (sij.equals(sijainti)) {
                    tulostettava = 'o';
                    continue;
                }
            }
        }
        
        return tulostettava;
    }
}