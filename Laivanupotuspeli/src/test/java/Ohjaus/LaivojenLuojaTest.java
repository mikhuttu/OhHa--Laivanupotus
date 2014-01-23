package Ohjaus;

import Tyokalut.Lukija;
import Tyokalut.Suunta;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaivojenLuojaTest {
    LaivojenLuoja luoja;
    Lukija lukija;
    Peli peli;
    Suunta suunta;
    
//    @Before
//    public void setUp() {
//        this.lukija = new Lukija();
//        this.peli = new Peli(this.lukija);
//        this.luoja = new LaivojenLuoja(this.peli);
//        this.suunta = Suunta.ALAS;
//    }
//    
//    @Test
//    public void laivanLuontiKayttajanPelilaudalleOnnistuu() {
//        this.luoja.luoKayttajanLaudalleLaiva(suunta, 1, 1, 3);
//        assertEquals(1, this.peli.getKayttaja().getPelilauta().getLaivat().size());
//    }
//    
//    @Test (expected = IllegalArgumentException.class)
//    public void laivanLuontiKayttajanPelilaudalleEiOnnistu() {
//        this.luoja.luoKayttajanLaudalleLaiva(suunta, 15, 15, 3);
//    }
}
