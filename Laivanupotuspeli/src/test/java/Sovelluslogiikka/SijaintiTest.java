package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SijaintiTest {
    Sijainti sijainti;
    
    @Before
    public void setUp() {
        sijainti = new Sijainti (0,0);
    }
    
    @Test 
    public void alku_tilanne() {
        getX_toimii();
        getY_toimii();
    }
    
    @Test
    public void getX_toimii() {
        assertEquals(0, sijainti.getX()); 
    }
    
    @Test
    public void getY_toimii() {
        assertEquals(0, sijainti.getY());
    }    
    
    
    @Test
    public void sijainti_kasvaa_oikein_X() {
        sijainti.kasvataX(3);
        assertEquals(3, sijainti.getX()); 
    }
    
    @Test
    public void sijainti_kasvaa_oikein_Y() {
        sijainti.kasvataY(2);
        assertEquals(2, sijainti.getY()); 
    }    
}
