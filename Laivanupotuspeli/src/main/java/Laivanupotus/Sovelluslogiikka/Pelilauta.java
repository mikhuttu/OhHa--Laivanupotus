package Laivanupotus.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import Laivanupotus.Tyokalut.Sijainti;
import Laivanupotus.Tyokalut.Suunta;

/**
 * Pelilauta pitää kirjaa sekä pelilaudalla olevista laivoista sekä ruuduista.
 */

public class Pelilauta {
    private final int koko = 6;
    private final List<Laiva> laivat;
    private final Ruutu[][] ruudut;
    
    public Pelilauta() {
        this.laivat = new ArrayList<>();
        this.ruudut = new Ruutu[koko][koko];
        asetaRuudutPelilaudalle();
    }

    /**
     * Luodaan ruudut yksi kerrallaan ja asetetaan taulukkoon.
    */
    
    private void asetaRuudutPelilaudalle() {
        for (int y = 0; y < koko; y++) {
            for (int x = 0; x < koko; x++) {
                Ruutu ruutu = new Ruutu(new Sijainti(x,y));
                this.ruudut[x][y] = ruutu;
            }
        }
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    public Ruutu[][] getRuudut() {
        return this.ruudut;
    }
    
    public List<Laiva> getLaivat() {
        return this.laivat;
    }
    
    /**
     * asetaLaiva on "yleismetodi", joka lisää pelilaudalle laivan mikäli se voidaan pelilaudalle asettaa.
     * 
     * @param laiva
     * @throws IllegalArgumentException
     */
    
    public void asetaLaiva(Laiva laiva) throws IllegalArgumentException {
        if (voidaankoAsettaa(laiva)) {
            lisaaLaiva(laiva);
        }
        else {
            throw new IllegalArgumentException ();
        }
    }
    
    private void lisaaLaiva(Laiva laiva) {
        this.laivat.add(laiva);
    }
    
    /**
     * Mikäli laiva voidaan asettaa pelilaudalle, palauttaa true.
     * @param laiva
     */
    
    public boolean voidaankoAsettaa(Laiva laiva) {
        return !laivatMenevatPaallekain(laiva) && !laivaMeneeRuudukonYli(laiva);
    }
    
    /**
     * Metodi kysyy jokaiselta pelilaudalla jo olevalta laivalta erikseen, meneekö se
     * päällekäin asetettavan laivan kanssa. Mikäli jokin menee, palauttaa true.
     * @param verrattava
     */
    
    private boolean laivatMenevatPaallekain(Laiva verrattava) {
        for (Laiva laiva : this.laivat) {
            if (laiva.menevatkoPaallekain(verrattava)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Mikäli asetettavan laivan "häntä" olisi pelilaudan ulkopuolella, palautetaan true.
     * Muuten palautetaan false, ja laiva voidaan tältä osin pelilaudalle lisätä.
     * @param laiva
     */
    
    private boolean laivaMeneeRuudukonYli(Laiva laiva) {
        int siirtyma = laiva.getKoko() - 1;
        Sijainti sijainti = new Sijainti(laiva.getSijainti().getX(), laiva.getSijainti().getY());
        
        if (laiva.getSuunta() == Suunta.ALAS) {
            sijainti.kasvataY(siirtyma);
        }
        else {
            sijainti.kasvataX(siirtyma);
        }

        return Math.max(sijainti.getX(), sijainti.getY()) >= this.koko;
    }
    
    /**
     * Metodi palauttaa true mikäli johonkin pelilaudalla olevaan laivaan osuttiin.
     * @param sijainti
     */
    
    public boolean osuikoLaivaan(Sijainti sijainti) {
        for (Laiva laiva : this.laivat) {
            if (laiva.osuiko(sijainti)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodi kysyy, onko Sijaintia verrattava vastaavaan ruutuun ammuttu ja palauttaa siitä tiedon.
     * @param verrattava
     * @throws IllegalArgumentException 
     */
    
    public boolean onkoRuutuunAmmuttu(Sijainti verrattava) {
        Ruutu ruutu = haeRuutu(verrattava);
        return ruutu.onkoAmmuttu();
    }
    
    /**
     * Metodi muuttaa sijaintia vastaavan pelilaudan ruudun ammutuksi.
     * @param sijainti 
     */
    
    public void muutaAmmutuksi(Sijainti sijainti) {
        haeRuutu(sijainti).muutaAmmutuksi();
    }
    
    /**
     * Metodi palauttaa sijaintia vastaavan ruudun.
     * @param sijainti
     */
    
    public Ruutu haeRuutu(Sijainti sijainti) {
        int x = sijainti.getX();
        int y = sijainti.getY();
        
        return this.ruudut[x][y];
    }
    
    /**
     * PelilaudanPiirtaja kysyy pelilaudan tältä metodilta, onko ko. laiva tuhottu.
     * Metodi käy läpi laivan osien sijainteja ja mikäli kaikki sijainnit on tuhottu, metodi palauttaa true.
     * @param laiva
     */
    
    public boolean onkoTuhottu(Laiva laiva) {
        for (int i = 0; i < laiva.getKoko(); i++) {
            Sijainti sijainti = laiva.haeLaivanOsanSijainti(i);
            Ruutu ruutu = haeRuutu(sijainti);
            
            if (!ruutu.onkoAmmuttu()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodi palauttaa listana kaikki pelilaudalla olevien laivojen osien sijainnit.
     */
    
    public ArrayList<Sijainti> haeLaivojenOsienSijainnit() {
        ArrayList<Sijainti> laivojenSijainnit = new ArrayList<>();
        
        for (int i = 0; i < this.laivat.size(); i++) {
            Laiva laiva = this.laivat.get(i);
              
            for (int j = 0; j < laiva.getKoko(); j++) {     
                laivojenSijainnit.add(laiva.haeLaivanOsanSijainti(j));
            }
        }
        return laivojenSijainnit;
    }
}