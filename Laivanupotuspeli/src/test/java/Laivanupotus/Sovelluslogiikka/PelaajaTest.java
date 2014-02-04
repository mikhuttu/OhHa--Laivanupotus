package Laivanupotus.Sovelluslogiikka;

//import Ohjaus.KoordinaatinValitsin;

import Laivanupotus.Tyokalut.Lukija;
import Laivanupotus.Tyokalut.Suunta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PelaajaTest {
    Pelaaja pelaaja;
    Pelilauta pelilauta;
    Laiva laiva;
    Lukija lukija;
    
    @Before
    public void setUp(){
        lukija = new Lukija("1\n2");
        pelaaja = new Pelaaja(lukija);
        pelilauta = new Pelilauta();
        laiva = new Laiva(new Sijainti(0,0), Suunta.ALAS, 4);
    }
    
    @Test
    public void pelaajanKonstruktoriToimii() {
        pelaajallaOnPelilauta();
        pelaajallaOnLukija();
    }
    
    private void pelaajallaOnPelilauta() {
        assertTrue(pelaaja.getPelilauta() != null);
    }
    
    private void pelaajallaOnLukija() {
        assertTrue("1".equals(lukija.seuraavaRivi()));
    }
    
    @Test
    public void ampuminenEiAmmuttuunRuutuunMuuttaaRuudunAmmutuksi() {
        pelaaja.ammu(pelilauta, new Sijainti(0,3));
        assertTrue(pelilauta.onkoRuutuunAmmuttu(new Sijainti(0,3)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void ampuminenJoAmmuttuunRuutuunPalauttaaVirheIlmoituksen() {
        Sijainti sijainti = ammuRuutuunJaPalautaSenSijainti();
        pelaaja.ammu(pelilauta, sijainti);
    }
    
    @Test
    public void ampuessaOsutaanLaivaanPalauttaaTrue() {
        pelilauta.asetaLaiva(laiva);
        assertTrue(pelaaja.ammu(pelilauta, new Sijainti (0,2)));
    }
    
    @Test
    public void ampuessaEiOsutaLaivaanPalauttaaFalse() {
        pelilauta.asetaLaiva(laiva);
        assertFalse(pelaaja.ammu(pelilauta, new Sijainti (1,2)));
    }
    
//    @Test
//    public void ammuttavanSijainninValitseminenPalauttaaAmmuttavanSijainnin() {
//        Sijainti sijainti = haeSijainti();
//        assertTrue(new Sijainti(1,2).equals(sijainti));
//    }
//    
//    private Sijainti haeSijainti() {
//        KoordinaatinValitsin valitsin = new KoordinaatinValitsin(lukija, pelaaja.getPelilauta());
//        int x = valitsin.valitseKoordinaatti('X');
//        int y = valitsin.valitseKoordinaatti('Y');
//        return new Sijainti(x,y);
//    }
    
    private Sijainti ammuRuutuunJaPalautaSenSijainti() {
        Sijainti sijainti = new Sijainti(0,3);
        pelilauta.muutaAmmutuksi(sijainti);
        return sijainti;
    }
    
}