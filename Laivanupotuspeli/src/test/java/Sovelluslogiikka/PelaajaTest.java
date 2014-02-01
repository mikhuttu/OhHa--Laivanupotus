package Sovelluslogiikka;

import Tyokalut.Suunta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PelaajaTest {
    Pelaaja pelaaja;
    Pelilauta pelilauta;
    Laiva laiva;
    
    @Before
    public void setUp(){
        pelaaja = new Pelaaja();
        pelilauta = new Pelilauta();
        laiva = new Laiva(new Sijainti(0,0), Suunta.ALAS, 4);
    }
    
    @Test
    public void pelaajanKonstruktoriToimii() {
        pelaajallaOnPelilauta();
    }
    
    private void pelaajallaOnPelilauta() {
        assertFalse(pelaaja.getPelilauta() == null);
    }
    
    @Test
    public void ampuminenEiAmmuttuunRuutuun() {
        pelaaja.ammu(pelilauta, new Sijainti(0,3));
        assertTrue(pelilauta.onkoRuutuunAmmuttu(new Sijainti(0,3)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ampuminenJoAmmuttuunRuutuun() {
        Sijainti sijainti = new Sijainti(0,3);
        pelilauta.muutaAmmutuksi(sijainti);
        pelaaja.ammu(pelilauta, sijainti);
    }
    
    @Test
    public void ampuessaOsutaanLaivaan() {
        pelilauta.asetaLaiva(laiva);
        assertTrue(pelaaja.ammu(pelilauta, new Sijainti (0,2)));
    }
    
    @Test
    public void ampuessaEiOsutaLaivaan() {
        pelilauta.asetaLaiva(laiva);
        assertFalse(pelaaja.ammu(pelilauta, new Sijainti (1,2)));
    }    
}