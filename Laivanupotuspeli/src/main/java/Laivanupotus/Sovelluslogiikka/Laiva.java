package Laivanupotus.Sovelluslogiikka;
import Laivanupotus.Tyokalut.Suunta;

/**
 * Laiva on luokka, joka nimensä mukaisesti kuvaa laivaa.
 * Laivalla on tietty yksikäsitteinen Sijainti, mutta koska sen koko on suurempi kuin 1,
 * se jatkuu sijainnista toiseen.
 */

public class Laiva {
    private Sijainti sijainti;
    private Suunta suunta;
    private int koko;
    
    public Laiva(Sijainti sijainti, Suunta suunta, int koko) {
        this.sijainti = sijainti;
        this.suunta = suunta;
        this.koko = koko;
    }
    
    public Sijainti getSijainti() {
        return this.sijainti;
    }
    
    public Suunta getSuunta() {
        return this.suunta;
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    /**
     * Metodi testaa, meneekö tämä ko. laiva päällekäin asetettavan (verrattava) laivan kanssa.
     * Mikäli menee, metodi palauttaa true, ja tämä käsitellään muualla (myöh. LaivojenLuoja).
     * 
     * @param verrattava
     * @return
     */
    
    public boolean menevatkoPaallekain(Laiva verrattava) {
        for (int i = 0; i < this.getKoko(); i++) {
            Sijainti thisSij = haeLaivanOsanSijainti(i);
            if (verrattava.osuiko(thisSij)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Metodissa testataan, kuuluuko Sijainti verrattava tähän ko. laivaan siten että laivan Sijainti joko
     * olisi se, tai sen osa olisi siinä Sijainnissa.
     * Metodia käytetään mm. ampumiseen, mutta sillä on myös muita käyttötarkoituksia.
     * 
     * @param verrattava
     * @return Palauttaa true mikäli "verrattava" on osa laivaa.
     */
    
    public boolean osuiko(Sijainti verrattava) {
        for (int i = 0; i < this.koko; i++) {
            Sijainti laivanOsa = haeLaivanOsanSijainti (i);
            
            if (laivanOsa.equals(verrattava)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodi hakee laivan osan sijainnin.
     * Mikäli i = 0, metodi palauttaa laivan sijainnin. Muuten, se palauttaa i:n päässä laivan sijannista olevan
     * sijainnin.
     * 
     * Metodia kutsuttaessa, i < laiva.koko.
     * 
     * @param i viittaa siihen, kuinka mones laivan osa kyseessä
     * @return 
     */
    
    public Sijainti haeLaivanOsanSijainti(int i) {
        Sijainti laivanOsa = new Sijainti (this.sijainti.getX(), this.sijainti.getY());
        
        if (this.suunta == Suunta.ALAS) {
            laivanOsa.kasvataY(i);
        }
        else {
            laivanOsa.kasvataX(i);
        }
        return laivanOsa;            
    }
}