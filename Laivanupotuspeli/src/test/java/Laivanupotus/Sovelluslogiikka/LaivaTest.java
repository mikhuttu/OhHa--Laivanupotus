package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Tyokalut.Suunta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LaivaTest {
    Laiva laivaAlas;
    Laiva laivaOikealle;
    
    @Before
    public void setUp() {
        laivaAlas = new Laiva(new Sijainti(1,3), Suunta.ALAS, 3);
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
    public void laivatMenevatPaallekainSuuntaALAS() {
        Laiva laivaAlas2 = new Laiva(new Sijainti(1,5), Suunta.ALAS, 2);
        assertTrue(laivaAlas.menevatkoPaallekain(laivaAlas2));
        assertTrue(laivaAlas2.menevatkoPaallekain(laivaAlas));
    }
    
    @Test
    public void laivatMenevatPaallekainSuuntaOIKEALLE() {
        Laiva laivaOikealle2 = new Laiva(new Sijainti(4,5), Suunta.OIKEALLE, 2);
        assertTrue(laivaOikealle.menevatkoPaallekain(laivaOikealle2));
        assertTrue(laivaOikealle2.menevatkoPaallekain(laivaOikealle));
    }    
    
    @Test
    public void laivatMenevatPaallekainEriSuunnat() {
        Laiva laivaAlas2 = new Laiva(new Sijainti(7,4), Suunta.ALAS, 3);
        assertTrue(laivaOikealle.menevatkoPaallekain(laivaAlas2));
        assertTrue(laivaAlas2.menevatkoPaallekain(laivaOikealle));
    }

    @Test
    public void laivatEivatMenePaallekain() {
        assertFalse(laivaAlas.menevatkoPaallekain(laivaOikealle));
        assertFalse(laivaOikealle.menevatkoPaallekain(laivaAlas));
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