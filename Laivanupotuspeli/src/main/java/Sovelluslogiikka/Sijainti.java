package Sovelluslogiikka;

public class Sijainti {
    private int x;
    private int y;
    
    public Sijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int get_X() {
        return this.x;
    }
    
    public int get_Y() {
        return this.y;
    }
    
    public void kasvata_X(int maara) {
        this.x += maara;
    }   
    
    public void kasvata_Y(int maara) {
        this.y += maara;
    }
    
    @Override
    public boolean equals(Object olio) {
        if (olio == null || olio.getClass() != this.getClass()) {
            return false;
        }
        Sijainti verrattava = (Sijainti) olio;
        if (this.get_X() == verrattava.get_X()) {
            if (this.get_Y() == verrattava.get_Y()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.get_X();
    }
}