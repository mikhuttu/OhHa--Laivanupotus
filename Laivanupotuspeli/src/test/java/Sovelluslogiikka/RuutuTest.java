package Sovelluslogiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RuutuTest {
    Ruutu ruutu;
    Ruutu ruutu2;
    
    @Before
    public void setUp() {
        ruutu = new Ruutu(new Sijainti(0,0));
        ruutu2 = new Ruutu(new Sijainti(1,0));        
    }
    
    @Test
    public void getSijaintiPalauttaaSijainnin() {
        assertEquals(new Sijainti (0,0), ruutu.getSijainti());
    }
    
    @Test
    public void ruutuunEiAlussaAmmuttu() {
        assertFalse(ruutu.onkoAmmuttu());
    }
    
    @Test
    public void ruutuunAmpuminenToimii() {
        ruutu.muutaAmmutuksi();
        assertTrue(ruutu.onkoAmmuttu());
    }
    
    @Test
    public void equalsPalauttaaFalseKunVerrattavaEiRuutu() {
        Object olio = null;
        assertFalse(ruutu.equals(olio));
        Sijainti olio2 = new Sijainti(0,0);
        assertFalse(ruutu.equals(olio2));
    }    
    
    @Test
    public void equalsPalauttaaTrueKunRuutujenSijainnitSamat() {
        ruutu2 = new Ruutu(new Sijainti(0,0));
        assertTrue(ruutu.equals(ruutu2));
    }
    
    @Test
    public void equalsPalauttaaFalseKunXTaiYKoordinaattiEri() {
        assertFalse(ruutu.equals(ruutu2));
        ruutu2 = new Ruutu(new Sijainti(0,1));
        assertFalse(ruutu.equals(ruutu2));
    }
}
