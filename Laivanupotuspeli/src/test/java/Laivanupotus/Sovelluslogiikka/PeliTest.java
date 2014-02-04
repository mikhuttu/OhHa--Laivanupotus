package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Lukija;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliTest {
    Peli peli;
    
    @Before
    public void setUp() {
        peli = new Peli(Aly.EASY, new Lukija());
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(new Pelaaja().getClass() == peli.getPelaaja().getClass());
        assertTrue(new Tietokone(Aly.EASY).getClass() == peli.getTietokone().getClass());
    }
    
    @Test
    public void jatketaankoPalauttaaTrueKunPeliJatkuu() {
        assertTrue(peli.jatketaanko());
    }
    
    @Test
    public void jatketaankoPalauttaaTrueKunTietokoneenTuhottu() {
        for (int i = 0; i < 12; i++) {
            peli.getPelaaja().kasvataVastustajaanOsuneet();
        }
        assertFalse(peli.jatketaanko());  
    }
    
    @Test
    public void jatketaankoPalauttaaTrueKunPelaajanTuhottu() {
        for (int i = 0; i < 12; i++) {
            peli.getTietokone().kasvataVastustajaanOsuneet();
        }
        assertFalse(peli.jatketaanko());  
    }
}