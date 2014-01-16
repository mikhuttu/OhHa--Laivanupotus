package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    Pelaaja pelaaja;
    Pelilauta pelilauta;
    Laiva laiva;
    
    @Before
    public void setUp(){
        pelaaja = new Pelaaja();
        pelilauta = new Pelilauta();
        laiva = new Laiva(new Sijainti(0,0), Suunta.ALAS, 3);
    }
    
    @Test
    public void pelaajan_konstruktori_toimii() {
        pelaajalla_ei_ole_alussa_laivoja();
        pelaajalla_on_pelilauta();
    }
    
    private void pelaajalla_ei_ole_alussa_laivoja(){
        assertTrue(pelaaja.get_Laivat().isEmpty());
    }
    
    private void pelaajalla_on_pelilauta() {
        assertFalse(pelaaja.get_Pelilauta() == null);
    }
    
    @Test
    public void lisaa_laiva_toimii() {
        pelaaja.lisaa_Laiva(laiva);
        assertEquals(1, pelaaja.get_Laivat().size());
    }
    
}
