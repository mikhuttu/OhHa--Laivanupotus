package Laivanupotus.Sovelluslogiikka.tietokonealy;

import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Sijainti;

/**
 * Rajapinta, joka määrittää metodit, jotka eri tekoälyjen tasojen pitää toteuttaa.
 */

public interface Alykkyys {
    
    Sijainti maaritaSijainti(Pelilauta vastustajanLauta);
}