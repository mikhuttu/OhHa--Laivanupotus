package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelilautaTest {
    Pelilauta pelilauta;
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
    }
    
    @Test
    public void konstruktori_toimii() {
        koon_asetus_toimii();
    }
    
    private void koon_asetus_toimii() {
        assertEquals(8, pelilauta.get_Koko());
    }
}
