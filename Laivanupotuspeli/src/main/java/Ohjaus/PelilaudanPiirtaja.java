package Ohjaus;

import java.util.ArrayList;

import Sovelluslogiikka.Laiva;
import Sovelluslogiikka.Pelaaja;
import Sovelluslogiikka.Ruutu;
import Sovelluslogiikka.Sijainti;

public class PelilaudanPiirtaja {
    Pelaaja pelaaja;
    
    public PelilaudanPiirtaja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }
    
    public void piirraPelilauta() {
        int koko = this.pelaaja.getPelilauta().getKoko();
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
        
        for (int i = 0; i < this.pelaaja.getPelilauta().getLaivat().size(); i++) {
            Laiva laiva = this.pelaaja.getPelilauta().getLaivat().get(i);
            
            for (int j = 0; j < laiva.getKoko(); j++) {     
                laivojenSijainnit.add(laiva.haeLaivanOsanSijainti(j));
            }
        }
        return laivojenSijainnit;
    }
    
    private char seuraavaMerkki(Sijainti sijainti, ArrayList<Sijainti> laivojenSijainnit) {
        Ruutu ruutu = this.pelaaja.getPelilauta().haeRuutu(sijainti);
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