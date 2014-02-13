package Laivanupotus.Sovelluslogiikka.tietokonealy;

import java.util.Random;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * "Mahdoton vaikeustaso", joka toimii siten että mikäli tietokoneen älykkyys on Aly.IMPOSSIBLE, tietokone määrittää
 * ammuttavan sijainnin siten että se on aina jonkun vastustajan laudalla olevan laivan osan sijainti.
 * 
 * Pelin nopeuttamiseksi metodi on tehty jo itse tarkistamaan, onko sijaintia vastaavaan ruutuun ammuttu.
 */

public class Impossible implements Alykkyys {
    
    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Sijainti sijainti = new Sijainti(0,0);
        
        while (true) {
            int arpa = arvo(vastustajanLauta.getLaivat().size());
            Laiva laiva = vastustajanLauta.getLaivat().get(arpa);
        
            arpa = arvo(laiva.getKoko());
            sijainti = laiva.haeLaivanOsanSijainti(arpa);
        
            Ruutu ruutu = vastustajanLauta.haeRuutu(sijainti);
            if (!ruutu.onkoAmmuttu()) {
                break;
            }
        }
        return sijainti;
    }
    
    private int arvo(int n) {
        Random random = new Random();
        return random.nextInt(n);
    }
}