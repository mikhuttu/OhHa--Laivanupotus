package Sovelluslogiikka;

import Ohjaus.Tulostaja;
import java.util.ArrayList;
import java.util.List;

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
    
    public boolean onkoRuutuunAmmuttu(Ruutu verrattava) {
        for (Ruutu ruutu : this.ruudut) {
            if (ruutu.equals(verrattava)) {
                return ruutu.onkoAmmuttu();
            }
        }
        
        ruutuEiKoordinaatistossa();
        return true;    // palautetaan true koska silloin ruutuun ei voi ampua.
    }
    
    private void ruutuEiKoordinaatistossa() {
        new Tulostaja().tulostaVirheIlmoitus(0);
    }
    
    public void lisaaLaiva(Laiva laiva) {
        this.laivat.add(laiva);
    }
}