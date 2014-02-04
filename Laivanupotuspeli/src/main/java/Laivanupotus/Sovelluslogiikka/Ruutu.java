package Laivanupotus.Sovelluslogiikka;

/**
 * Pelilaudalla on Ruutuja.
 * Ruuduilla on Sijainti, ja Ruutu tietää lisäksi, onko siihen ammuttu.
 */

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
    
    /**
     * equals metodi vertaa, onko parametrina saatu olio sama ruutu kuin "tämä" ruutu.
     * Se käyttää vertailun apuna Sijaintien vertailuun perustuvaa equals metodia.

     * @param olio
     * @return 
     */
    
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