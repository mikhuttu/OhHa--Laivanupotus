package Laivanupotus.Sovelluslogiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Laivanupotus.Tyokalut.Suunta;
import Laivanupotus.Tyokalut.Sijainti;

public class KayttajaTest {
    Kayttaja kayttaja;
    Pelilauta pelilauta;
    Laiva laiva;
    
    @Before
    public void setUp() {
        kayttaja = new Kayttaja();
        pelilauta = new Pelilauta();
        laiva = new Laiva(new Sijainti(0,0), Suunta.ALAS, 4);
    }
    
    @Test
    public void kasvataVastustajaanOsuneetKasvattaaOsuneita() {
        kayttaja.kasvataVastustajaanOsuneet();
        assertEquals(1, kayttaja.getOsuneet());
    }
    
    @Test
    public void kayttajanKonstruktoriToimii() {
        kayttajallaOnPelilauta();
        vastustajaanEiAlussaOsuttu();
    }
    
    private void kayttajallaOnPelilauta() {
        assertTrue(kayttaja.getPelilauta() != null);
    }
    
    private void vastustajaanEiAlussaOsuttu() {
        assertEquals(0, kayttaja.getOsuneet());
    }    
    
    @Test
    public void ampuminenEiAmmuttuunRuutuunMuuttaaRuudunAmmutuksi() {
        kayttaja.suoritaVuoro(pelilauta, new Sijainti(0,3));
        assertTrue(pelilauta.onkoRuutuunAmmuttu(new Sijainti(0,3)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ampuminenJoAmmuttuunRuutuunPalauttaaVirheIlmoituksen() {
        Sijainti sijainti = ammuRuutuunJaPalautaSenSijainti();
        kayttaja.suoritaVuoro(pelilauta, sijainti);
    }
    
    @Test
    public void ampuessaOsutaanLaivaanPalauttaaTrue() {
        pelilauta.asetaLaiva(laiva);
        assertTrue(kayttaja.suoritaVuoro(pelilauta, new Sijainti (0,2)));
    }
    
    @Test
    public void ampuessaEiOsutaLaivaanPalauttaaFalse() {
        pelilauta.asetaLaiva(laiva);
        assertFalse(kayttaja.suoritaVuoro(pelilauta, new Sijainti (1,2)));
    }
    
    private Sijainti ammuRuutuunJaPalautaSenSijainti() {
        Sijainti sijainti = new Sijainti(0,3);
        pelilauta.muutaAmmutuksi(sijainti);
        return sijainti;
    }
    
    @Test
    public void ampuminenJaOsuminenKasvattaaVastustajaanOsuneita() {
        pelilauta.asetaLaiva(laiva);
        kayttaja.suoritaVuoro(pelilauta, new Sijainti(0,1));
        assertEquals(1, kayttaja.getOsuneet());
    }
}
