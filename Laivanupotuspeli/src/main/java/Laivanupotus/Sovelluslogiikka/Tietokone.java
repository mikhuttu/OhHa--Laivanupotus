package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Sovelluslogiikka.tietokonealy.Alykkyys;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Easy;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Impossible;

public class Tietokone extends Kayttaja {
    private Aly aly;
    private Pelaaja pelaaja;
    
    public Tietokone(Aly aly) {
        this.aly = aly;
        this.pelaaja = new Pelaaja();
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    @Override
    public Pelilauta getPelilauta() {
        return this.pelaaja.getPelilauta();
    }
    
    @Override
    public boolean suoritaVuoro(Pelilauta vastustajanLauta) throws IllegalArgumentException {
        boolean osuiko = false;
        Sijainti sijainti = maaritaSijainti(vastustajanLauta);
        
        if (ammu(vastustajanLauta, sijainti)) {
            kasvataVastustajaanOsuneet();
            osuiko = true;
        }
        return osuiko;
    }
    
    private Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Alykkyys alykkyys = new Easy();
        
        if (this.aly == Aly.IMPOSSIBLE) {
            alykkyys = new Impossible();
        }

        return alykkyys.maaritaSijainti(vastustajanLauta);
    }
    
    @Override
    public boolean ammu(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        if(vastustajanLauta.onkoRuutuunAmmuttu(sijainti)) {
            throw new IllegalArgumentException();
        }
        vastustajanLauta.muutaAmmutuksi(sijainti);
        
        if (vastustajanLauta.osuikoLaivaan(sijainti)) {
            return true;
        }
        return false;
    }
}