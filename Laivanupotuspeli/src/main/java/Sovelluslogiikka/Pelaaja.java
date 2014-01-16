package Sovelluslogiikka;
import java.util.ArrayList;
import java.util.List;

public class Pelaaja {
    private List<Laiva> laivat;
    private Pelilauta pelilauta;
    
    public Pelaaja() {
        this.laivat = new ArrayList <Laiva>();
        this.pelilauta = new Pelilauta();
    }
    
    public List<Laiva> get_Laivat() {
        return this.laivat;
    }

    public Pelilauta get_Pelilauta() {
        return this.pelilauta;
    }    
    
    public void lisaa_Laiva(Laiva laiva) {
        this.laivat.add(laiva);
    }
    
}