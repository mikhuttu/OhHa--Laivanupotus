package Laivanupotus.Sovelluslogiikka.tietokonealy;

import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.Suunta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

public class ImpossibleTest {
    Pelilauta pelilauta;
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        pelilauta.asetaLaiva(new Laiva(new Sijainti(1,1), Suunta.ALAS, 2));
    }
    
    @Test
    public void maaritaSijaintiEiAmmutuunLaivaan() {
        Laiva laiva = pelilauta.getLaivat().get(0);
        int arpa = arvo(laiva.getKoko());
        Sijainti sijainti = laiva.haeLaivanOsanSijainti(arpa);
        
        int y = sijainti.getY();
        assertEquals(new Sijainti(1, y), sijainti);
    }
    
    @Test
    public void maaritaSijaintiAmmuttuunLaivaan() {
        Sijainti sijainti = new Sijainti(1,1);
        pelilauta.muutaAmmutuksi(sijainti);
        
        while (true) {
            Laiva laiva = pelilauta.getLaivat().get(0);
            int arpa = arvo(laiva.getKoko());
            sijainti = laiva.haeLaivanOsanSijainti(arpa);
            
            Ruutu ruutu = pelilauta.haeRuutu(sijainti);
            if (!ruutu.onkoAmmuttu()) {
                break;
            }
        }
        
        assertEquals(new Sijainti(1,2), sijainti);
    }
    
    private int arvo(int n) {
        return new Random().nextInt(n);
    }
    
}
