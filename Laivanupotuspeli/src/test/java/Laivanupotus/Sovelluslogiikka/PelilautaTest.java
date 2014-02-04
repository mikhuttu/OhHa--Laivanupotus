package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Tyokalut.Suunta;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class PelilautaTest {
    Pelilauta pelilauta;
    Laiva laiva;  
    Ruutu ruutu;
    
    @Before
    public void setUp() {
        pelilauta = new Pelilauta();
        laiva = new Laiva(new Sijainti(0,0), Suunta.ALAS, 3);
    }
    
    @Test
    public void konstruktori_toimii() {
        KoonAsetusToimii();
        pelilaudallaOikeaMaaraRuutuja();
        pelilaudallaEiOleAlussaLaivoja();
    }
    
    private void KoonAsetusToimii() {
        assertEquals(6, pelilauta.getKoko());
    }
    
    private void pelilaudallaOikeaMaaraRuutuja() {
        assertEquals(36, pelilauta.getRuudut().size());
    }
    
    private void pelilaudallaEiOleAlussaLaivoja(){
        assertTrue(pelilauta.getLaivat().isEmpty());
    }
    
    @Test
    public void laivanLisaaminenOnnistuu() {
        pelilauta.asetaLaiva(laiva);
        assertFalse(pelilauta.getLaivat().isEmpty());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanLisaaminenEiOnnistuLaivatMenevatPaallekain() {
        pelilauta.asetaLaiva(laiva);
        pelilauta.asetaLaiva(laiva);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanLisaaminenEiOnnistuLaivaMeneeReunanYliALAS() {
        laiva = new Laiva(new Sijainti(5,14), Suunta.ALAS, 4);
        pelilauta.asetaLaiva(laiva);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void laivanLisaaminenEiOnnistuLaivaMeneeReunanYliOIKEALLE() {    
        laiva = new Laiva(new Sijainti(14,5), Suunta.OIKEALLE, 4);
        pelilauta.asetaLaiva(laiva);
    }
    
    @Test
    public void ruutuihinEiOleAlussaAmmuttu() {
        assertFalse(pelilauta.onkoRuutuunAmmuttu(new Sijainti(3,5)));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void virheIlmoitusKunRuutuEiPelilaudalla() {
        ruutu = pelilauta.haeRuutu(new Sijainti(5, 20));
    }
    
    @Test
    public void haeRuutuPalauttaaOikeanRuudun() {
        Sijainti sijainti = new Sijainti(4,3);
        ruutu = pelilauta.haeRuutu(sijainti);
        assertEquals(new Sijainti(4,3), ruutu.getSijainti());
    }
    
    @Test
    public void palauttaaFalseKunLaivaEiOleTuhottu() {
        pelilauta.asetaLaiva(laiva);
        
        assertFalse(pelilauta.onkoTuhottu(laiva));
    }
    
    @Test
    public void palauttaaTrueKunLaivaOnTuhottu() {
        pelilauta.asetaLaiva(laiva);
        
        for (int i = 0; i < laiva.getKoko(); i++) {
            Sijainti sijainti = laiva.haeLaivanOsanSijainti(i);
            ruutu = pelilauta.haeRuutu(sijainti);
            ruutu.muutaAmmutuksi();
        }
        
        assertTrue(pelilauta.onkoTuhottu(laiva));
    }
    
    @Test
    public void osuikoLaivaanPalauttaaFalseKunEiOsu() {
        pelilauta.asetaLaiva(laiva);
        assertFalse(pelilauta.osuikoLaivaan(new Sijainti(0,3)));
    }
    
    @Test
    public void osuikoLaivaanPalauttaaTrueKunOsui() {
        pelilauta.asetaLaiva(laiva);
        assertTrue(pelilauta.osuikoLaivaan(new Sijainti(0,1)));
    }
    
    @Test
    public void muutaAmmutuksiMuuttaaRuudunAmmutuksi() {
        ruutu = pelilauta.haeRuutu(new Sijainti(2,1));
        pelilauta.muutaAmmutuksi(new Sijainti(2,1));
        
        assertTrue(ruutu.onkoAmmuttu());
    }
    
    @Test
    public void haeLaivojenOsienSijainnitPalauttaaKaikkiSijainnit() {
        pelilauta.asetaLaiva(laiva);
        pelilauta.asetaLaiva(new Laiva(new Sijainti (1,0), Suunta.OIKEALLE, 2));
        
        ArrayList<Sijainti> kaikkiSijainnit = pelilauta.haeLaivojenOsienSijainnit();
        assertEquals(5, kaikkiSijainnit.size());
        
    }
    
}