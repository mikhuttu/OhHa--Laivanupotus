package Laivanupotus.Sovelluslogiikka;

import org.junit.Before;
import org.junit.Test;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Suunta;

public class LaivojenLuojaTest {
    private LaivojenLuoja luoja;
    
    @Before
    public void setUp() {
        luoja = new LaivojenLuoja(new Peli(Aly.EASY));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanAsetusPelaajanLaudalleHeittaaPoikkeuksenKunSuuntaTaiSijaintiNull() {
        luoja.asetaPelaajanLaudalleLaiva(null, new Sijainti(0,0));
        luoja.asetaPelaajanLaudalleLaiva(Suunta.ALAS, null);
    }
}
