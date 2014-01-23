package Ohjaus;

import Sovelluslogiikka.Laiva;
import Sovelluslogiikka.Pelaaja;
import Sovelluslogiikka.Pelilauta;
import Sovelluslogiikka.Sijainti;
import Tyokalut.Suunta;

public class LaivojenLuoja {
    private Peli peli;
    
    public LaivojenLuoja(Peli peli) {
        this.peli = peli;
    }
    
    public void luoKayttajanLaudalleLaiva(Suunta suunta, int Xsij, int Ysij, int koko) throws IllegalArgumentException {
        Laiva laiva = luoLaiva(suunta, Xsij, Ysij, koko);
        Pelaaja kayttaja = this.peli.getKayttaja();
        Pelilauta pelilauta = kayttaja.getPelilauta();
        
        pelilauta.asetaLaiva(laiva);
    }
    
    private Laiva luoLaiva(Suunta suunta, int Xsij, int Ysij, int koko) {
        return new Laiva (new Sijainti(Xsij, Ysij), suunta, koko);
    }
}