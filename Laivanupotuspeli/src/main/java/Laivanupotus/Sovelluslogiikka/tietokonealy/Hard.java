package Laivanupotus.Sovelluslogiikka.tietokonealy;

import java.util.TreeMap;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Vaikea vaikeustaso
 * Hard:in ideana on toimia siten että tietokoneella olisi oikeasti jonkin sortin tekoäly, jota se käyttäisi
 * ammuttavan sijainnin määrittämiseen. Pelipuu?
 */
public class Hard implements Alykkyys {

    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        return new Sijainti(0,0);
    }
    
}
