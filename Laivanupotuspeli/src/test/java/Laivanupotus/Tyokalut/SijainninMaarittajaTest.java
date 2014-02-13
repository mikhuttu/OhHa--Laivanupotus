package Laivanupotus.Tyokalut;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JTextField;
import Laivanupotus.Sovelluslogiikka.Sijainti;

public class SijainninMaarittajaTest {
    SijainninMaarittaja maarittaja;
    JTextField tekstikentta;
    
    @Before
    public void setUp() {
        maarittaja = new SijainninMaarittaja();
        tekstikentta = new JTextField();
    }
    
    @Test
    public void palauttaaNullKunKenttaTyhja() {
        assertEquals(null, maarittaja.palautaSijainti(tekstikentta));
    }
    
    @Test
    public void palauttaaKentanSisaltoaVastaavanSijainnin() {
        tekstikentta.setText("2,3");
        assertEquals(new Sijainti(2,3), maarittaja.palautaSijainti(tekstikentta));
    }
}
