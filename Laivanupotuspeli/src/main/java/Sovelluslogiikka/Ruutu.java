package Sovelluslogiikka;

public class Ruutu {
    private Sijainti sijainti;
    private boolean ammuttu;
    
    public Ruutu(Sijainti sijainti) {
        this.sijainti = sijainti;
        this.ammuttu = false;
    }
    
    public Sijainti getSijainti() {
        return this.sijainti;
    }
    
    public void muutaAmmutuksi() {
        this.ammuttu = true;
    }
    
    public boolean onkoAmmuttu() {
        return this.ammuttu;
    }
    
    @Override
    public boolean equals(Object olio) {
        if (olio == null || olio.getClass() != this.getClass()) {
            return false;
        }
        
       Ruutu ruutu = (Ruutu) olio;
       Sijainti verrattava = ruutu.getSijainti();

       return this.sijainti.vertaaSijainteja(verrattava);
    }
    
    @Override
    public int hashCode() {
        return this.sijainti.hashCode();
    }
}