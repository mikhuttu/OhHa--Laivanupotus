package Laivanupotus.Sovelluslogiikka;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Sijainti;


public class TietokoneTest {
    private Kayttaja tietokone;
    private Sijainti sijainti;
    
    @Before
    public void setUp() {
        tietokone = new Tietokone(Aly.EASY);
        sijainti = new Sijainti(1,2);
    }
    
    @Test
    public void getAlyPalauttaaAlyn() {
        Tietokone kone = (Tietokone) tietokone;
        assertEquals(Aly.EASY, kone.getAly());
    }
    
    @Test (expected = IllegalArgumentException.class) 
    public void suoritaVuoroPalauttaaVirheIlmoituksenKunAmmutaanSamaanRuutuunKaksiKertaa() {
        tietokone.suoritaVuoro(tietokone.getPelilauta(), sijainti);
        tietokone.suoritaVuoro(tietokone.getPelilauta(), sijainti);
    }
}
