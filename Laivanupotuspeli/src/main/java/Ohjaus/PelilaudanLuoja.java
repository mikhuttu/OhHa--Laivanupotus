package Ohjaus;

import java.util.ArrayList;

import Sovelluslogiikka.Laiva;
import Sovelluslogiikka.Pelaaja;
import Sovelluslogiikka.Ruutu;
import Sovelluslogiikka.Sijainti;

public class PelilaudanLuoja {
    Pelaaja pelaaja;
    
    public PelilaudanLuoja(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
    }
    
    public void piirraPelilauta() {
        int koko = this.pelaaja.getPelilauta().getKoko();
        ArrayList<Sijainti> laivojenSijainnit = haeLaivojenOsienSijainnit();
        
        for (int y = 0; y < koko; y++) {
            for (int x = 0; x < koko; x++) {
                tulostaRuutu(x, y, laivojenSijainnit);
            }
            System.out.println();
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
    
    private void tulostaRuutu(int x, int y, ArrayList<Sijainti> laivojenSijainnit) {
        Ruutu ruutu = this.pelaaja.getPelilauta().haeRuutu(new Sijainti(x,y));
        if (ruutu.onkoAmmuttu()) {
            System.out.print("x");
        }
        else {
            Sijainti sijainti = new Sijainti(x,y);
            boolean paikassaLaiva = false;
                    
            for (Sijainti sij : laivojenSijainnit) {
                if (sij.equals(sijainti)) {
                    System.out.print("o");
                    paikassaLaiva = true;
                }
            }
            if (!paikassaLaiva) {
                System.out.print("~");
            }
         }
    }
}
