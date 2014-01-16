package Sovelluslogiikka;

public class Laiva {
    private Sijainti sijainti;
    private Suunta suunta;
    private int koko;
    
    public Laiva(Sijainti sijainti, Suunta suunta, int koko) {
        this.sijainti = sijainti;
        this.suunta = suunta;
        this.koko = koko;
    }
    
    public Sijainti get_Sijainti() {
        return this.sijainti;
    }
    
    public Suunta get_Suunta() {
        return this.suunta;
    }
    
    public int get_Koko() {
        return this.koko;
    }
    
    public boolean osuiko(Sijainti verrattava) {
        for (int i = 0; i < this.koko; i++) {
            Sijainti laivan_osa = hae_laivan_osan_sijainti (i);
            
            if (laivan_osa.equals(verrattava)) {
                return true;
            }
        }
        return false;
    }
    
    private Sijainti hae_laivan_osan_sijainti(int i) {
        Sijainti laivan_osa = new Sijainti (this.sijainti.get_X(), this.sijainti.get_Y());
        
        if (this.suunta == Suunta.ALAS) {
            laivan_osa.kasvata_Y(i);
        }
        else {
            laivan_osa.kasvata_X(i);
        }
        return laivan_osa;            
    }
}