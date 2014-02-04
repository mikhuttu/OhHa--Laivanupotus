package Laivanupotus.Sovelluslogiikka.tietokonealy;

import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import java.util.Random;

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