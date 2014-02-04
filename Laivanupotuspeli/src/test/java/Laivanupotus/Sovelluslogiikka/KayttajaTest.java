package Laivanupotus.Sovelluslogiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KayttajaTest {
    Kayttaja kayttaja;
    
    @Before
    public void setUp() {
        kayttaja = new Pelaaja();
    }
    
    @Test
    public void vastustajaanEiAlussaOsuttu() {
        assertEquals(0, kayttaja.getOsuneet());
    }
    
    @Test
    public void kasvataVastustajaanOsuneetKasvattaaOsuneita() {
        kayttaja.kasvataVastustajaanOsuneet();
        assertEquals(1, kayttaja.getOsuneet());
    }
    
}
