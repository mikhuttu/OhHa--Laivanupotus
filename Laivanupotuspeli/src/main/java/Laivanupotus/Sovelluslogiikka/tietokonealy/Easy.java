package Laivanupotus.Sovelluslogiikka.tietokonealy;

import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Tyokalut.Sijainti;
import java.util.Random;

/**
 * "Helppo vaikeustaso", joka toimii siten että tietokone määrittää ammuttavan sijainnin sattumanvaraisesti.
 */

public class Easy implements Alykkyys {
    
    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Random random = new Random();
        int x;
        int y;
        
        while (true) {
            x = random.nextInt(vastustajanLauta.getKoko());
            y = random.nextInt(vastustajanLauta.getKoko());
            
            Ruutu ruutu = vastustajanLauta.haeRuutu(new Sijainti (x,y));
            if (!ruutu.onkoAmmuttu()) {
                break;
            }
        }
        
        return new Sijainti(x, y);
    }
}