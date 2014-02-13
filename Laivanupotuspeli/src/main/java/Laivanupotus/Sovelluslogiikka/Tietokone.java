package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Alykkyys;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Easy;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Impossible;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Tietokone perii Kayttaja -luokan ja on siis toinen pelin kayttajista.
 * Tietokone luokka osaa määrittää ammuttavan sijainnin itse, ja se onkin luokan ainoa "mielenkiintoinen" toiminnallisuus.
 * 
 * Tietokone on siis pelin "vastustaja".
 */

public class Tietokone extends Kayttaja {
    private final Aly aly;
    
    public Tietokone(Aly aly) {
        this.aly = aly;
    }
    
    /**
     * Vuoron suoritus tapahtuu siten että määritetään ensin ammuttava sijainti (jonkun algoritmin avulla), jonka
     * jälkeen kutsutaan yliluokan (Kayttaja) suoritaVuoro metodia.
     * 
     * @param vastustajanLauta
     * @return Kayttaja.suoritaVuoro();
     * @throws IllegalArgumentException 
     */
    
    public boolean suoritaVuoro(Pelilauta vastustajanLauta) throws IllegalArgumentException {
        Sijainti sijainti = maaritaSijainti(vastustajanLauta);
        return super.suoritaVuoro(vastustajanLauta, sijainti);
    }
    
    /**
     * Riippuen Tietokoneen alykkyydestä, määritetään ammuttava sijainti ja palautetaan se.
     * 
     * @param vastustajanLauta
     * @return 
     */
    
    private Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Alykkyys alykkyys = new Easy();
        
        if (this.aly == Aly.IMPOSSIBLE) {
            alykkyys = new Impossible();
        }

        return alykkyys.maaritaSijainti(vastustajanLauta);
    }
}