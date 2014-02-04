package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Suunta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TietokoneTest {
    private Tietokone tietokone;
    private Pelaaja pelaaja;
    private Sijainti sijainti;
    
    @Before
    public void setUp() {
        tietokone = new Tietokone(Aly.EASY);
        sijainti = new Sijainti(1,1);
    }
    
    @Test
    public void getPelaajaPalauttaaPelaajan() {
        assertTrue(new Pelaaja().getClass() == tietokone.getPelaaja().getClass());
    }
    
    @Test
    public void getPelilautaPalauttaaPelilaudan() {
        assertTrue(new Pelilauta().getClass() == tietokone.getPelilauta().getClass());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ampuminenPalauttaaVirheIlmoituksenKunSijaintiinOnJoAmmuttu() {
        muutaSijaintiAmmutuksi();
        tietokone.ammu(tietokone.getPelilauta(), sijainti);
    }
    
    @Test
    public void ampuminenMuuttaaPelilaudanRuudunAmmutuksi() {
        tietokone.ammu(tietokone.getPelilauta(), sijainti);
        Ruutu ruutu = tietokone.getPelilauta().haeRuutu(sijainti);
        
        assertTrue(ruutu.onkoAmmuttu());
    }
    
    @Test
    public void ampuminenPalauttaaTrueKunOsuuLaivaan() {
        tietokone.getPelilauta().asetaLaiva(new Laiva(sijainti, Suunta.OIKEALLE, 2));
        assertTrue(tietokone.ammu(tietokone.getPelilauta(), sijainti));
    }
    
    @Test
    public void ampuminenPalauttaaFalseKunEiOsuLaivaan() {
        tietokone.getPelilauta().asetaLaiva(new Laiva(new Sijainti(1,2), Suunta.OIKEALLE, 2));
        assertFalse(tietokone.ammu(tietokone.getPelilauta(), sijainti));
    }
    
//    @Test (expected = IllegalArgumentException.class)
//    public void suoritaVuoroPalauttaaVirheIlmoituksenKunAmmutaanAmmuttuunSijaintiin {
//        muutaSijaintiAmmutuksi();
//        tietokone.suoritaVuoro(tietokone.getPelilauta());
//    }
    
    private void muutaSijaintiAmmutuksi() {
        Pelilauta lauta = tietokone.getPelilauta();
        lauta.muutaAmmutuksi(sijainti);
    }
}
