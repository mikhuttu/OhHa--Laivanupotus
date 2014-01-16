package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {
    Pelilauta pelilauta;
    Laiva laiva;    
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        laiva = new Laiva(new Sijainti(0,0), Suunta.ALAS, 3);
    }
    
    @Test
    public void konstruktori_toimii() {
        KoonAsetusToimii();
        pelaajallaEiOleAlussaLaivoja();
    }
    
    private void KoonAsetusToimii() {
        assertEquals(8, pelilauta.getKoko());
    }
    
    private void pelaajallaEiOleAlussaLaivoja(){
        assertTrue(pelilauta.getLaivat().isEmpty());
    }    
    
    @Test
    public void lisaaLaivaToimii() {
        pelilauta.lisaaLaiva(laiva);
        assertEquals(1, pelilauta.getLaivat().size());
    }
}