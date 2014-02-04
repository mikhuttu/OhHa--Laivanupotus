package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Ohjaus.KoordinaatinValitsin;
import Laivanupotus.Tyokalut.Lukija;

public class Pelaaja extends Kayttaja {
    private Pelilauta pelilauta;
    private Lukija lukija;
    
    public Pelaaja() {
        this.pelilauta = new Pelilauta();
    }
    
    public Pelaaja(Lukija lukija) {
        this();
        this.lukija = lukija;
    }
    
    @Override
    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }
    
    public Lukija getLukija() {
        return this.lukija;
    }
    
    @Override
    public boolean suoritaVuoro(Pelilauta vastustajanLauta) throws IllegalArgumentException {
        System.out.println("Valitse ammuttava koordinaatti.");
        Sijainti sijainti = valitseAmmuttavaSijainti(vastustajanLauta);
        
        boolean osuiko = false;
        
        if (ammu(vastustajanLauta, sijainti)) {
            kasvataVastustajaanOsuneet();
            osuiko = true;
        }
        return osuiko;
    }
    
    private Sijainti valitseAmmuttavaSijainti(Pelilauta pelilauta) {
        KoordinaatinValitsin valitsin = new KoordinaatinValitsin(this.lukija, pelilauta);
        
        int x = valitsin.valitseKoordinaatti('X');
        System.out.println();
        int y = valitsin.valitseKoordinaatti('Y');
        System.out.println();
        
        return new Sijainti (x,y);
    }
    
    @Override
    public boolean ammu(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        
        if(vastustajanLauta.onkoRuutuunAmmuttu(sijainti)) {
            System.out.println("Ruutuun on jo ammuttu.\nValitse toinen ruutu.\n");
            throw new IllegalArgumentException();
        }
        vastustajanLauta.muutaAmmutuksi(sijainti);
        
        if (vastustajanLauta.osuikoLaivaan(sijainti)) {
            return true;
        }
        return false;
    }
}