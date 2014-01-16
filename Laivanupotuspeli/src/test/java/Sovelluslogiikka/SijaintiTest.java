package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SijaintiTest {
    Sijainti sijainti;
    Sijainti sijainti_2;
    
    @Before
    public void setUp() {
        sijainti = new Sijainti (0,0);
        sijainti_2 = new Sijainti(1,0);
    }
    
    @Test 
    public void alku_tilanne() {
        get_X_toimii();
        get_Y_toimii();
    }
    
    private void get_X_toimii() {
        assertEquals(0, sijainti.get_X()); 
    }
    
    private void get_Y_toimii() {
        assertEquals(0, sijainti.get_Y());
    }    
    
    
    @Test
    public void sijainti_kasvaa_oikein_X() {
        sijainti.kasvata_X(3);
        assertEquals(3, sijainti.get_X()); 
    }
    
    @Test
    public void sijainti_kasvaa_oikein_Y() {
        sijainti.kasvata_Y(2);
        assertEquals(2, sijainti.get_Y()); 
    }
    
    @Test
    public void equals_palauttaa_false_kun_verrattava_ei_Sijainti() {
        Object olio = null;
        assertFalse(sijainti.equals(olio));
        String olio_2 = "";
        assertFalse(sijainti.equals(olio_2));
    }
    
    @Test
    public void equals_toimii_kun_Sijaintien_koordinaatit_samat() {
        sijainti_2 = new Sijainti(0,0);
        assertTrue(sijainti.equals(sijainti_2));
    }
    
    @Test
    public void equals_palauttaa_false_kun_X_tai_Y_koordinaatti_eri() {
        assertFalse(sijainti.equals(sijainti_2));
        sijainti_2 = new Sijainti(0,1);
        assertFalse(sijainti.equals(sijainti_2));
    }
    
    @Test
    public void hashCode_palauttaa_jonkin_arvon() {
        int palautus_arvo = sijainti.hashCode();
        assertEquals(palautus_arvo, sijainti.hashCode());
    }
}
