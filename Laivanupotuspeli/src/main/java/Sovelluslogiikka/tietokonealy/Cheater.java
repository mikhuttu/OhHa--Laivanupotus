package Sovelluslogiikka.tietokonealy;

import Sovelluslogiikka.Laiva;
import Sovelluslogiikka.Pelilauta;
import Sovelluslogiikka.Ruutu;
import Sovelluslogiikka.Sijainti;
import java.util.Random;

public class Cheater implements Alykkyys {
    
    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Laiva laiva = vastustajanLauta.getLaivat().get(0);
        Sijainti sijainti = new Sijainti(0,0);
        
        while (true) {
            int arpa = arvo();

            for (int i = 0; i < vastustajanLauta.getLaivat().size(); i++) {
                laiva = vastustajanLauta.getLaivat().get(i);
            
                if (i >= arpa) {
                    continue;
                }
                if (i == vastustajanLauta.getLaivat().size()) {
                    System.out.println("Virhetilanne. arvo() metodi ei toimi oikein.");
                }
            }  
        
            sijainti = laiva.haeLaivanOsanSijainti(arvo());
            Ruutu ruutu = vastustajanLauta.haeRuutu(sijainti);
            
            if (!ruutu.onkoAmmuttu()) {
                break;
            }
        }
        
        return sijainti;
    }
    
    private int arvo() {
        Random random = new Random();
        return random.nextInt(4);
    }
}
