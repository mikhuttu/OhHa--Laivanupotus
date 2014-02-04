package Laivanupotus.Sovelluslogiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SijaintiTest {
    Sijainti sijainti;
    Sijainti sijainti2;
    
    @Before
    public void setUp() {
        sijainti = new Sijainti (0,0);
        sijainti2 = new Sijainti(1,0);
    }
    
    @Test 
    public void alkuTilanne() {
        getXToimii();
        getYToimii();
    }
    
    private void getXToimii() {
        assertEquals(0, sijainti.getX()); 
    }
    
    private void getYToimii() {
        assertEquals(0, sijainti.getY());
    }    
    
    
    @Test
    public void sijaintiKasvaaOikeinX() {
        sijainti.kasvataX(3);
        assertEquals(3, sijainti.getX()); 
    }
    
    @Test
    public void sijaintiKasvaaOikeinY() {
        sijainti.kasvataY(2);
        assertEquals(2, sijainti.getY()); 
    }
    
    @Test
    public void equalsPalauttaaFalseKunVerrattavaEiSijainti() {
        Object olio = null;
        assertFalse(sijainti.equals(olio));
        String olio2 = "";
        assertFalse(sijainti.equals(olio2));
    }
    
    @Test
    public void equalsToimiiKunSijaintienKoordinaatitSamat() {
        sijainti2 = new Sijainti(0,0);
        assertTrue(sijainti.equals(sijainti2));
    }
    
    @Test
    public void equalsPalauttaaFalseKunXTaiYKoordinaattiEri() {
        assertFalse(sijainti.equals(sijainti2));
        sijainti2 = new Sijainti(0,1);
        assertFalse(sijainti.equals(sijainti2));
    }
}