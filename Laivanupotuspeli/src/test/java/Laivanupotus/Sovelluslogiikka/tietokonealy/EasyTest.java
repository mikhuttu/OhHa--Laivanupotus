package Laivanupotus.Sovelluslogiikka.tietokonealy;

import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

public class EasyTest {
    
    private Pelilauta pelilauta;
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
    }
    
    @Test
    public void maaritaSijainti() {
        Sijainti sijainti = arvoSijainti();
        int x = sijainti.getX();
        int y = sijainti.getY();
        
        assertTrue(x < pelilauta.getKoko());
        assertTrue(y < pelilauta.getKoko());
        
    }
    
    private Sijainti arvoSijainti() {
        int koko = pelilauta.getKoko();
        Random random = new Random();
        return new Sijainti(random.nextInt(koko), random.nextInt(koko));
    }
    
}
