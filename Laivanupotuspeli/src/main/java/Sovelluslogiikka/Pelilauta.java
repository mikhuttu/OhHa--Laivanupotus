package Sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import Tyokalut.Suunta;

public class Pelilauta {
    private int koko;
    private List<Laiva> laivat;
    private List<Ruutu> ruudut;
    
    public Pelilauta() {
        this.koko = 8;
        this.laivat = new ArrayList<Laiva>();
        this.ruudut = new ArrayList<Ruutu>();
        asetaRuudutPelilaudalle();
    }
    
    private void asetaRuudutPelilaudalle() {
        for (int i = 0; i < koko; i++) {
            for (int j = 0; j < koko; j++) {
                Ruutu ruutu = new Ruutu(new Sijainti(i,j));
                this.ruudut.add(ruutu);
            }
        }
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    public List<Ruutu> getRuudut() {
        return this.ruudut;
    }
    
    public List<Laiva> getLaivat() {
        return this.laivat;
    }
    
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
    
    private boolean voidaankoAsettaa(Laiva laiva) {
        if (!laivatMenevatPaallekain(laiva) && !laivaMeneeRuudukonYli(laiva)) {
            return true;
        }
        return false;
    }
    
    private boolean laivatMenevatPaallekain(Laiva verrattava) {
        for (Laiva laiva : this.laivat) {
            if (laiva.menevatkoPaallekain(verrattava)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean laivaMeneeRuudukonYli(Laiva laiva) {
        Suunta suunta = laiva.getSuunta();
        Sijainti sijainti = new Sijainti(laiva.getSijainti().getX(), laiva.getSijainti().getY());
        
        for (int i = 0; i < laiva.getKoko(); i++) {
            if (sijainti.getX() >= this.koko || sijainti.getY() >= this.koko) {
                return true;
            }
            
            if (suunta == Suunta.ALAS) {
                sijainti.kasvataY(1);
            }
            else {
                sijainti.kasvataX(1);
            }
        }
        return false;
        
    }
    
    public boolean osuikoLaivaan(Sijainti sijainti) {
        for (Laiva laiva : this.laivat) {
            if (laiva.osuiko(sijainti)) {
                return true;
            }
        }
        return false;
    }
    
 
    public boolean onkoRuutuunAmmuttu(Sijainti verrattava) throws IllegalArgumentException {
        Ruutu ruutu = haeRuutu(verrattava);
        return ruutu.onkoAmmuttu();
    }
    
    public void muutaAmmutuksi(Sijainti sijainti) {
        haeRuutu(sijainti).muutaAmmutuksi();
    }
    
    public Ruutu haeRuutu(Sijainti sijainti) throws IllegalArgumentException {
        for (Ruutu ruutu: this.ruudut) {
            if (ruutu.getSijainti().equals(sijainti)) {
                return ruutu;
            }
        }
        
        throw new IllegalArgumentException("Virhe: Valitsemasi ruutu ei ole koordinaatistossa!");
    }
    
}