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
    
    public Sijainti getSijainti() {
        return this.sijainti;
    }
    
    public Suunta getSuunta() {
        return this.suunta;
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    public boolean osuiko(Sijainti verrattava) {
        for (int i = 0; i < this.koko; i++) {
            Sijainti laivanOsa = haeLaivanOsanSijainti (i);
            
            if (laivanOsa.equals(verrattava)) {
                return true;
            }
        }
        return false;
    }
    
    private Sijainti haeLaivanOsanSijainti(int i) {
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