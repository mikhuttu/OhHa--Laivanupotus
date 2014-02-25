package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.*;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Tietokone perii Kayttaja -luokan ja on siis toinen pelin kayttajista.
 * Tietokone luokka osaa määrittää ammuttavan sijainnin itse, ja se onkin luokan ainoa "mielenkiintoinen" toiminnallisuus.
 * 
 * Tietokone on siis pelin "vastustaja".
 */

public class Tietokone extends Kayttaja {
    private final Aly aly;
    private Alykkyys alykkyys;
    
    public Tietokone(Aly aly) {
        this.aly = aly;
        alustaAlykkyys();
    }
    
    private void alustaAlykkyys() {
        if (this.aly == Aly.EASY) {
            alykkyys = new Easy();
        }
        
        else if (this.aly == Aly.HARD) {
            alykkyys = new Hard();
        }
        
        else {
            alykkyys = new Impossible();
        }
    }
    
    public Aly getAly() {
        return this.aly;
    }
    
    /**
     * Vuoron suoritus tapahtuu siten että määritetään ensin ammuttava sijainti (jonkun algoritmin avulla), jonka
     * jälkeen kutsutaan yliluokan (Kayttaja) suoritaVuoro metodia.
     * 
     * @param vastustajanLauta
     * @return Kayttaja.suoritaVuoro();
     */
    
    public boolean suoritaVuoro(Pelilauta vastustajanLauta) {
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
        return alykkyys.maaritaSijainti(vastustajanLauta);
    }
}