package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivaTest {
    Laiva laivaAlas;
    Laiva laivaOikealle;
    
    @Before
    public void setUp() {
        laivaAlas      = new Laiva(new Sijainti(1,3), Suunta.ALAS, 3);
        laivaOikealle  = new Laiva (new Sijainti(5,5), Suunta.OIKEALLE, 4);
    }
    
    @Test
    public void konstruktoriToimii() {
        getSijaintiToimii();
        getSuuntaToimii();
        getKokoToimii();
    }
    
    @Test
    public void getSijaintiToimii() {
        assertEquals(new Sijainti(1,3), laivaAlas.getSijainti());
        assertEquals(new Sijainti(5,5), laivaOikealle.getSijainti());
    }
    
    @Test
    public void getSuuntaToimii() {
        assertEquals(Suunta.ALAS, laivaAlas.getSuunta());
        assertEquals(Suunta.OIKEALLE, laivaOikealle.getSuunta()); 
    }
    
    @Test
    public void getKokoToimii() {
        assertEquals(3, laivaAlas.getKoko());
    }
    
    @Test
    public void laivaanOsuuSuuntaOIKEALLE() {
        Sijainti kohde = new Sijainti (5,5);
        for (int i = 0; i < laivaOikealle.getKoko(); i++) {
            assertTrue(laivaOikealle.osuiko(kohde));
            kohde.kasvataX(1);
        }
    }
            
    @Test
    public void laivaanEiOsuSuuntaOIKEALLE() {
        Sijainti kohde = new Sijainti (1,5);
        for (int i = 0; i < laivaOikealle.getKoko(); i++) {
            assertFalse(laivaOikealle.osuiko(kohde));
            kohde.kasvataX(1);
        }
    }
    
    @Test
    public void laivaanOsuuSuuntaALAS() {
        Sijainti kohde = new Sijainti (1,3);
        for (int i = 0; i < laivaAlas.getKoko(); i++) {
            assertTrue(laivaAlas.osuiko(kohde));
            kohde.kasvataY(1);
        }  
    }
            
    @Test
    public void laivaanEiOsuSuuntaALAS() {
        Sijainti kohde = new Sijainti(1,0);
        for (int i = 0; i < laivaAlas.getKoko(); i++) {
            assertFalse(laivaAlas.osuiko(kohde));
            kohde.kasvataY(1);
        }
    }
}