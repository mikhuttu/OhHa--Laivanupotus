package Laivanupotus.Sovelluslogiikka;

public class Sijainti {
    private int x;
    private int y;
    
    public Sijainti(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void kasvataX(int maara) {
        this.x += maara;
    }   
    
    public void kasvataY(int maara) {
        this.y += maara;
    }
    
    @Override
    public boolean equals(Object olio) {
        if (olio == null || olio.getClass() != this.getClass()) {
            return false;
        }
        
        Sijainti verrattava = (Sijainti) olio;
        return vertaaSijainteja(verrattava);

    }
    
    public boolean vertaaSijainteja(Sijainti verrattava) {
        if (this.getX() == verrattava.getX()) {
            if (this.getY() == verrattava.getY()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getX();
    }
}