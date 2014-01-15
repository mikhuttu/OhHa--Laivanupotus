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
        getSijainti_toimii();
        getSuunta_toimii();
        getKoko_toimii();
    }
    
    @Test
    public void getSijainti_toimii() {
        assertEquals(new Sijainti(1,3), laiva_alas.getSijainti());
        assertEquals(new Sijainti(5,5), laiva_oikealle.getSijainti());
    }
    
    @Test
    public void getSuunta_toimii() {
        assertEquals(Suunta.ALAS, laiva_alas.getSuunta());
        assertEquals(Suunta.OIKEALLE, laiva_oikealle.getSuunta()); 
    }
    
    @Test
    public void getKoko_toimii() {
        assertEquals(3, laiva_alas.getKoko());
    }
    
    @Test
    public void laivaan_osuu_Suunta_OIKEALLE() {
        Sijainti kohde = new Sijainti (5,5);
        for (int i = 0; i < laiva_oikealle.getKoko(); i++) {
            assertTrue(laiva_oikealle.osuiko(kohde));
            kohde.kasvataX(1);
        }
    }
            
    @Test
    public void laivaan_ei_osu_Suunta_OIKEALLE() {
        Sijainti kohde = new Sijainti (1,5);
        for (int i = 0; i < laiva_oikealle.getKoko(); i++) {
            assertFalse(laiva_oikealle.osuiko(kohde));
            kohde.kasvataX(1);
        }
    }
    
    @Test
    public void laivaan_osuu_Suunta_ALAS() {
        Sijainti kohde = new Sijainti (1,3);
        for (int i = 0; i < laiva_alas.getKoko(); i++) {
            assertTrue(laiva_alas.osuiko(kohde));
            kohde.kasvataY(1);
        }  
    }
            
    @Test
    public void laivaan_ei_osu_Suunta_ALAS() {
        Sijainti kohde = new Sijainti(1,0);
        for (int i = 0; i < laiva_alas.getKoko(); i++) {
            assertFalse(laiva_alas.osuiko(kohde));
            kohde.kasvataY(1);
        }
    }
}
