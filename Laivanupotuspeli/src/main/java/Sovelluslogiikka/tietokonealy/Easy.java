package Sovelluslogiikka.tietokonealy;

import Sovelluslogiikka.Pelilauta;
import Sovelluslogiikka.Sijainti;
import java.util.Random;

public class Easy implements Alykkyys {
    
    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        
        Random random = new Random();
        int x = random.nextInt(vastustajanLauta.getKoko());
        int y = random.nextInt(vastustajanLauta.getKoko());
        
        return new Sijainti(x, y);
    }
}