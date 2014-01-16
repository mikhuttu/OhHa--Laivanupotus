package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    Pelaaja pelaaja;
    Pelilauta pelilauta;
    
    @Before
    public void setUp(){
        pelaaja = new Pelaaja();
        pelilauta = new Pelilauta();
    }
    
    @Test
    public void pelaajanKonstruktoriToimii() {
        pelaajallaOnPelilauta();
    }
    
    private void pelaajallaOnPelilauta() {
        assertFalse(pelaaja.getPelilauta() == null);
    }
    
    @Test
    public void laivanLisaysPelilaudalleToimii() {
        Laiva laiva = new Laiva(new Sijainti (0,0), Suunta.ALAS, 3);
        pelaaja.lisaaLaivaPelilaudalle(laiva);       
        assertEquals(1, pelaaja.getPelilauta().getLaivat().size());
    }
}