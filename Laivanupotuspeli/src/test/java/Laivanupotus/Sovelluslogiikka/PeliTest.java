package Laivanupotus.Sovelluslogiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;

public class PeliTest {
    Peli peli;
    
    @Before
    public void setUp() {
        peli = new Peli(Aly.EASY);
    }
    
    @Test
    public void konstruktoriToimii() {
        assertTrue(peli.getPelaaja().getClass() == Kayttaja.class);
        assertTrue(peli.getTietokone().getClass() == Tietokone.class);
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