package Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PelaajaTest {
    Pelaaja pelaaja;
    Pelilauta pelilauta;
    Ruutu ruutu;
    
    @Before
    public void setUp(){
        pelaaja = new Pelaaja();
        pelilauta = new Pelilauta();
        ruutu = new Ruutu(new Sijainti(0,0));
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
    
    @Test
    public void ampuminenEiAmmuttuunRuutuun() {
        assertTrue(pelaaja.ammu(pelilauta, ruutu));
    }
    
    @Test
    public void ampuminenAmmuttuunRuutuun() {
        ruutu.muutaAmmutuksi();
        assertFalse(pelaaja.ammu(pelilauta, ruutu));
    }
}