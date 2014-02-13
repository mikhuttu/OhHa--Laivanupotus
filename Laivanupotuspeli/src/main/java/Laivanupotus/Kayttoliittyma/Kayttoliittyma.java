package Laivanupotus.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Laivanupotus.Kayttoliittyma.Alaosa.AlaOsanKomponentit;
import Laivanupotus.Kayttoliittyma.Aloitusnakyma.AloitusNakyma;
import Laivanupotus.Kayttoliittyma.Ylaosa.LaivojenAsetusKomponentit;
import Laivanupotus.Kayttoliittyma.Ylaosa.AmpumisKomponentit;
import Laivanupotus.Kayttoliittyma.Ylaosa.YlaOsanKomponentit;
import Laivanupotus.Sovelluslogiikka.LaivojenLuoja;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Peli;
import Laivanupotus.Sovelluslogiikka.Tietokone;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;

/**
 * Kayttoliittyma tuntee käyttöliittymän pääkomponentit, joita ovat aloitusnäkymä, ala- ja yläosat sekä lopetusnäkymä (jota
 * ei ole vielä toteutettu).
 * 
 * Käyttöliittymä luo muut komponentit ja saa näiden komponenttien kuuntelijoilta viestejä, joiden pohjalta peli toimii.
 */

public class Kayttoliittyma implements Runnable {

    private Peli peli;
    private JFrame frame;
    private AloitusNakyma aloitusnakyma;
    private AlaOsanKomponentit alaosa;
    private YlaOsanKomponentit ylaosa;
    
    
    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.getContentPane().setLayout(new BorderLayout());
        
        luoAloitusNakyma();
        
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoAloitusNakyma() {
        this.aloitusnakyma = new AloitusNakyma(this);
        aloitusnakyma.luo();
        frame.getContentPane().add(aloitusnakyma, BorderLayout.NORTH);
    }
    
    /**
     * Peli alkaa, kun pelaaja on ensin valinnut jonkun mahdollisista vaikeusasteista ja se on tässä metodin parametrina.
     * 
     * Metodi käskee luomaan uuden pelin ja tietokoneen laivat.
     * Tämän jälkeen poistetaan alkuperäinen käyttöliittymäkomponentti "Aloitusnäkymä" ja korvataan se uusilla pääkomponenteilla.
     * @param aly 
     */
    
    public void aloitaPeli(Aly aly) {
        this.peli = new Peli(aly);
        luoTietokoneenLaivat();
        frame.getContentPane().remove(0);
        luoPaaKomponentit();
    }
    
    public Peli getPeli() {
        return this.peli;
    }
    
    /**
     * Luodaan komponentit laivojen asetusta ja pelilaudan näyttämistä varten ja päivitetään nämä näkyviin pelaajaa varten.
     */
    
    private void luoPaaKomponentit() {
        luoLaivojenAsetusKomponentit();
        luoAlaOsanKomponentit();
        
        frame.pack();
    }
    
    /**
     * ylaosa laitetaan sisältämään LaivojenAsetusKomponentit, joka toteuttaa abstraktin luokan YlaOsanKomponentit.
     * laivanIndeksi alustetaan arvoon 1.
     */
    
    private void luoLaivojenAsetusKomponentit() {
        this.ylaosa = new LaivojenAsetusKomponentit(this);
        
        int laivanIndeksi = peli.getPelaaja().getPelilauta().getLaivat().size() + 1;
        LaivojenAsetusKomponentit komponentit = (LaivojenAsetusKomponentit) ylaosa;
        komponentit.luo(laivanIndeksi);
        
        paivitaYlaOsa();
    }
    
    private void luoAlaOsanKomponentit() {
        this.alaosa = new AlaOsanKomponentit(this.peli);
        alaosa.luo();
        frame.getContentPane().add(alaosa, BorderLayout.CENTER);
    }
    
    /**
     * Käyttöliittymä saanut tiedon siitä, että käyttäjä on nyt asettanut edeltävän laivan pelilaudalle.
     * 
     * Piirretään käyttäjän pelilauta ja päivitetään yläosan näkymä metodia "seuraava(laivoja + 1)" käyttäen.
     * Mikäli pelaaja on asettanut kaikki 4 laivaansa, luodaan ampumiskomponentit.
     */
    
    public void laivaAsetettiinPelilaudalle() {
        int laivoja = peli.getPelaaja().getPelilauta().getLaivat().size();
        piirraPelilauta(this.peli.getPelaaja());
        
        if (laivoja >= 4) {
            luoAmpumisKomponentit();
            return;
        }
        
        LaivojenAsetusKomponentit komponentit = (LaivojenAsetusKomponentit) ylaosa;
        komponentit.seuraava(laivoja + 1);

        paivitaYlaOsa();
    }
    
    /*
    * Metodi pyytää piirtämään käyttäjän pelilaudan ja sen jälkeen päivittämään ko. pelilaudan näkymän.
    */
    
    private void piirraPelilauta(Kayttaja kayttaja) {
        alaosa.piirra(kayttaja);
        alaosa.update(alaosa.getGraphics());
    }
    
    /*
    * Yläosa vaihdetaan AmpumisKomponenteiksi ja luodaan ko. komponentit.
    */
    
    private void luoAmpumisKomponentit() {
        this.ylaosa = new AmpumisKomponentit(this);
        
        AmpumisKomponentit komponentit = (AmpumisKomponentit) ylaosa;
        komponentit.luo();
        paivitaYlaOsa();
        frame.pack();
    }
    
    /**
     * frame-oliolle asetetaan uusi JPanel (ylaosa) indeksiin "0" (tässä tapauksessa ylaosan paikalle).
     * Samalla vanha JPanel -olio poistetaan indeksistä 0, mikäli sellainen siellä jo on.
     */
    
    private void paivitaYlaOsa() {
        JPanel paivitetty = ylaosa;
        
        if (frame.getContentPane().getComponentCount() > 0) {
            frame.getContentPane().remove(0);
        }
        
        frame.getContentPane().add(paivitetty, BorderLayout.NORTH, 0);
    }
    
    
    /**
     * Luodaan tietokoneen laivat ja asetetaan ne tietokoneen pelilaudalle.
     * Laivat eivät tule pelaajalle näkyviin.
     */
    
    private void luoTietokoneenLaivat() {
        LaivojenLuoja luoja = new LaivojenLuoja(this.peli);
        luoja.asetaTietokoneenLaivat();
    }
    
    /**
     * Kun pelaaja on ampunut johonkin tietokoneen pelilaudalla olevaan ruutuun ja ko. ruutuun ei ole vielä ammuttu,
     * tämän metodin suoritus alkaa.
     * 
     * @param osuiko - kuvaa osuttiinko ampuessa laivaan vai ei
     */
    public void pelaajaAmpui(boolean osuiko) {
        piirraPelilauta(this.peli.getTietokone());
        if (osuiko) {
            osuttiinLaivaan();
        }
        else {
            eiOsuttuLaivaan();
        }
    }
    
    /**
     * Koska laivaan osuttiin, tarkistetaan päättyykö peli, eli ovatko vastustajan kaikki laivat tuhottu vai ei.
     * Mikäli eivät ole, pelaaja saa jatkaa.
     */
    
    private void osuttiinLaivaan() {
        // soita joku ääni koska osuttiin?
        
        if (!this.peli.jatketaanko()) {
            peliPaattyi(true);
            return;
        }

        seuraavaVuoro(this.peli.getPelaaja());
    }
    
    /**
     * Koska pelaaja ei ampuessaan vastustajan laivoihin osunut, tietokone saa jatkaa.
     */
    
    private void eiOsuttuLaivaan() {
        seuraavaVuoro(this.peli.getTietokone());
    }
    
    /**
     * nuku -metodi pyytää odottamaan 0,4s ennen suorituksen jatkumista.
     * Seuraavaksi tarkistetaan onko vuoron saaja pelaaja vai tietokone. Jos se on tietokone, suoritetaan tietokoneenVuoro().
     * 
     * @param kayttaja 
     */
    
    private void seuraavaVuoro(Kayttaja kayttaja) {
        nuku();
        
        if (kayttaja.getClass() == Tietokone.class) {
            paivitaKommentti("(Tietokoneen vuoro...)");
            tietokoneenVuoro();
        }
        else {
            paivitaKommentti("Valitse ammuttava ruutu oik. pelilaudalta: ");    // pelaaja saa jatkaa / pelaajan vuoro alkaa
        }
    }
    
    /**
     * Tietokone suorittaa vuoronsa kutsumalla oman luokkansa suoritaVuoro -metodia, joka palauttaa true (osuttiin), tai false.
     * Jos tietokone osuu pelaajan laivaan, saa se jatkaa mikäli peli ei pääty (eli mikäli pelaajan laivat eivät ole tuhottu).
     */
    
    private void tietokoneenVuoro() {
        Tietokone tietokone = (Tietokone) this.peli.getTietokone();
        boolean osuiko = tietokone.suoritaVuoro(this.peli.getPelaaja().getPelilauta());
        
        piirraPelilauta(this.peli.getPelaaja());
        
        if (osuiko) {
            if (!this.peli.jatketaanko()) {
                peliPaattyi(false);
                return;
            }

            seuraavaVuoro(this.peli.getTietokone());
        }
        else {
            seuraavaVuoro(this.peli.getPelaaja());
        }
    }
    
    private void peliPaattyi(boolean voitettiinko) {
        if (voitettiinko) {
            paivitaKommentti("VOITIT PELIN!!!");
        }
        else {
            paivitaKommentti("HÄVISIT :P");
        }
        paivitaYlaOsa();
        ylaosa.update(ylaosa.getGraphics());
        
        // laita peli päättymään jotenkin fiksusti. Uusi näkymä ja mahdollisuus aloittaa uusi peli?
    }
    
    /**
     * Päivitetään YlaOsanKomponentit -luokan määrittämää kommenttia.
     * @param kommentti 
     */
    
    public void paivitaKommentti(String kommentti) {
        ylaosa.kommenttiPaivitys(kommentti);
        paivitaYlaOsa();
    }
    
    /**
     * Odota 0,4s.
     */
    
    private void nuku() {
        try {
            Thread.sleep(400);
        }
        catch (InterruptedException ie) {
        }
    }
}