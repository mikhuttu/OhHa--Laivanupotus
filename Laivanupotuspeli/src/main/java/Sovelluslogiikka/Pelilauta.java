package Sovelluslogiikka;

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
    
    public void lisaaLaiva(Laiva laiva) {
        this.laivat.add(laiva);
    }
    
    public void muutaAmmutuksi(Sijainti sijainti) {
        haeRuutu(sijainti).muutaAmmutuksi();
    }
 
    public boolean onkoRuutuunAmmuttu(Sijainti verrattava) throws IllegalArgumentException {
        Ruutu ruutu = haeRuutu(verrattava);
        return ruutu.onkoAmmuttu();
    }
    
    public boolean onkoRuudussaJoLaiva(Sijainti verrattava) {
        return true;
    }
    
    private Ruutu haeRuutu(Sijainti sijainti) throws IllegalArgumentException {
        for (Ruutu ruutu: this.ruudut) {
            if (ruutu.getSijainti().equals(sijainti)) {
                return ruutu;
            }
        }
        
        throw new IllegalArgumentException("Virhe: Valitsemasi ruutu ei ole koordinaatistossa!");
    }
    
}