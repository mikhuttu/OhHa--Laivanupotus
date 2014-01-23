package Sovelluslogiikka;
import Tyokalut.Suunta;

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
        pelilaudallaEiOleAlussaLaivoja();
        pelilaudallaOikeaMaaraRuutuja();
    }
    
    private void KoonAsetusToimii() {
        assertEquals(8, pelilauta.getKoko());
    }
    
    private void pelilaudallaOikeaMaaraRuutuja() {
        assertEquals(64, pelilauta.getRuudut().size());
    }
    
    private void pelilaudallaEiOleAlussaLaivoja(){
        assertTrue(pelilauta.getLaivat().isEmpty());
    }
    
    @Test
    public void laivanLisaaminenOnnistuu() {
        pelilauta.asetaLaiva(laiva);
        assertFalse(pelilauta.getLaivat().isEmpty());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanLisaaminenEiOnnistuLaivatMenevatPaallekain() {
        pelilauta.asetaLaiva(laiva);
        pelilauta.asetaLaiva(laiva);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanLisaaminenEiOnnistuLaivaMeneeReunanYliALAS() {
        laiva = new Laiva(new Sijainti(5,14), Suunta.ALAS, 4);
        pelilauta.asetaLaiva(laiva);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanLisaaminenEiOnnistuLaivaMeneeReunanYliOIKEALLE() {    
        laiva = new Laiva(new Sijainti(14,5), Suunta.OIKEALLE, 4);
        pelilauta.asetaLaiva(laiva);
    }
    
    @Test
    public void ruutuihinEiOleAlussaAmmuttu() {
        assertFalse(pelilauta.onkoRuutuunAmmuttu(new Sijainti(3,5)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void virheIlmoitusKunRuutuEiPelilaudalla() {
        pelilauta.onkoRuutuunAmmuttu(new Sijainti(5, 20));
    }
    
}