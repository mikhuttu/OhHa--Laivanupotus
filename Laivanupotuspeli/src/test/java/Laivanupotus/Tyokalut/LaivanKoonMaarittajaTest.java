package Laivanupotus.Tyokalut;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LaivanKoonMaarittajaTest {
    LaivanKoonMaarittaja maarittaja;
    
    @Before
    public void setUp() {
        maarittaja = new LaivanKoonMaarittaja();
    }
    
    @Test
    public void maaritaKoko0() {
        assertEquals(4, maarittaja.maaritaKoko(0));
    }
    
    @Test
    public void maaritaKoko1() {
        assertEquals(3, maarittaja.maaritaKoko(1));
    }
    
    @Test
    public void maaritaKoko2() {
        assertEquals(3, maarittaja.maaritaKoko(2));
    }
    
    @Test
    public void maaritaKoko3() {
        assertEquals(2, maarittaja.maaritaKoko(3));
    }
}
