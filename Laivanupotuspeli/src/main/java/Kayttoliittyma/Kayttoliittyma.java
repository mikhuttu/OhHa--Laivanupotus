package Kayttoliittyma;
import Ohjaus.Peli;

public class Kayttoliittyma  {
    private Peli peli;
    
    public void run() {
        tulostaAlkuTilanne();
        luoPeli();
        asetaKayttajanLaivat();
        
    }
    
    private void tulostaAlkuTilanne() {
        System.out.println("------------------");
        System.out.println(" LAIVANUPOTUSPELI");
        System.out.println("------------------");
    }
    
    private void luoPeli() {
        this.peli = new Peli();
    }
    
    private void asetaKayttajanLaivat() {
        this.peli.asetaKayttajanLaivat();
    }
    
}
