package Sovelluslogiikka;

import Sovelluslogiikka.tietokonealy.*;

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
    public void suoritaVuoro(Pelilauta vastustajanLauta) throws IllegalArgumentException {
        Sijainti sijainti = maaritaSijainti(vastustajanLauta);
        
        if (ammu(vastustajanLauta, sijainti)) {
                kasvataVastustajaanOsuneet();
        }
    }
    
    private Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        Alykkyys alykkyys = new Sattuma();
        
        if (this.aly == Aly.CHEATER) {
            alykkyys = new Cheater();
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