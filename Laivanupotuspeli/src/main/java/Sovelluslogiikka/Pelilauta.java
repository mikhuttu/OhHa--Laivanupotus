package Sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;

public class Pelilauta {
    private int koko;
    private List<Laiva> laivat;
    
    public Pelilauta() {
        this.koko = 8;
        this.laivat = new ArrayList<Laiva>();
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    public List<Laiva> getLaivat() {
        return this.laivat;
    }
    
    public void lisaaLaiva(Laiva laiva) {
        this.laivat.add(laiva);
    }
}