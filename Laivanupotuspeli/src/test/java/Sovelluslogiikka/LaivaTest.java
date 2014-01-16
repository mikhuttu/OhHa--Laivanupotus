package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivaTest {
    Laiva laiva_alas;
    Laiva laiva_oikealle;
    
    @Before
    public void setUp() {
        laiva_alas      = new Laiva(new Sijainti(1,3), Suunta.ALAS, 3);
        laiva_oikealle  = new Laiva (new Sijainti(5,5), Suunta.OIKEALLE, 4);
    }
    
    @Test
    public void konstruktori_toimii() {
        get_Sijainti_toimii();
        get_Suunta_toimii();
        get_Koko_toimii();
    }
    
    @Test
    public void get_Sijainti_toimii() {
        assertEquals(new Sijainti(1,3), laiva_alas.get_Sijainti());
        assertEquals(new Sijainti(5,5), laiva_oikealle.get_Sijainti());
    }
    
    @Test
    public void get_Suunta_toimii() {
        assertEquals(Suunta.ALAS, laiva_alas.get_Suunta());
        assertEquals(Suunta.OIKEALLE, laiva_oikealle.get_Suunta()); 
    }
    
    @Test
    public void get_Koko_toimii() {
        assertEquals(3, laiva_alas.get_Koko());
    }
    
    @Test
    public void laivaan_osuu_Suunta_OIKEALLE() {
        Sijainti kohde = new Sijainti (5,5);
        for (int i = 0; i < laiva_oikealle.get_Koko(); i++) {
            assertTrue(laiva_oikealle.osuiko(kohde));
            kohde.kasvata_X(1);
        }
    }
            
    @Test
    public void laivaan_ei_osu_Suunta_OIKEALLE() {
        Sijainti kohde = new Sijainti (1,5);
        for (int i = 0; i < laiva_oikealle.get_Koko(); i++) {
            assertFalse(laiva_oikealle.osuiko(kohde));
            kohde.kasvata_X(1);
        }
    }
    
    @Test
    public void laivaan_osuu_Suunta_ALAS() {
        Sijainti kohde = new Sijainti (1,3);
        for (int i = 0; i < laiva_alas.get_Koko(); i++) {
            assertTrue(laiva_alas.osuiko(kohde));
            kohde.kasvata_Y(1);
        }  
    }
            
    @Test
    public void laivaan_ei_osu_Suunta_ALAS() {
        Sijainti kohde = new Sijainti(1,0);
        for (int i = 0; i < laiva_alas.get_Koko(); i++) {
            assertFalse(laiva_alas.osuiko(kohde));
            kohde.kasvata_Y(1);
        }
    }
}
