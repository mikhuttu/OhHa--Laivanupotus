package Laivanupotus.Sovelluslogiikka.tietokonealy;

import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Tyokalut.Sijainti;
import java.util.Random;

/**
 * "Helppo vaikeustaso", joka toimii siten että tietokone määrittää ammuttavan sijainnin sattumanvaraisesti.
 */

public class Easy implements Alykkyys {
    
    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        
        Random random = new Random();
        int x = random.nextInt(vastustajanLauta.getKoko());
        int y = random.nextInt(vastustajanLauta.getKoko());
        
        return new Sijainti(x, y);
    }
}