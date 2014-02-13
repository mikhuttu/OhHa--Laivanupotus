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
    
    public void aloitaPeli(Aly aly) {
        this.peli = new Peli(aly);
        luoTietokoneenLaivat();
        frame.getContentPane().remove(0);
        luoPaaKomponentit();
    }
    
    public Peli getPeli() {
        return this.peli;
    }
    
    private void luoPaaKomponentit() {
        luoLaivojenAsetusKomponentit();
        luoAlaOsanKomponentit();
        
        frame.pack();
    }
    
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
    
    private void piirraPelilauta(Kayttaja kayttaja) {
        alaosa.piirra(kayttaja);
        alaosa.update(alaosa.getGraphics());
    }
    
    private void luoAmpumisKomponentit() {
        this.ylaosa = new AmpumisKomponentit(this);
        
        AmpumisKomponentit komponentit = (AmpumisKomponentit) ylaosa;
        komponentit.luo();
        paivitaYlaOsa();
        frame.pack();
    }
    
    private void paivitaYlaOsa() {
        JPanel paivitetty = ylaosa;
        
        if (frame.getContentPane().getComponentCount() > 0) {
            frame.getContentPane().remove(0);                           // poistaa vanhan JPanel-olion.
        }
        
        frame.getContentPane().add(paivitetty, BorderLayout.NORTH, 0);  // asettaa uuden JPanel-olion vanhan paikalle.
    }
    
    
    // kaikki mitä tän alta löytyy vois olla jossain muualla. "KayttoliittymaLogiikka" ?
    
    private void luoTietokoneenLaivat() {
        LaivojenLuoja luoja = new LaivojenLuoja(this.peli);
        luoja.asetaTietokoneenLaivat();
    }
    
    
    public void pelaajaAmpui(boolean osuiko) {
        piirraPelilauta(this.peli.getTietokone());
        if (osuiko) {
            osuttiinLaivaan();
        }
        else {
            eiOsuttuLaivaan();
        }
    }
    
    private void osuttiinLaivaan() {
        // soita joku ääni koska osuttiin?
        
        if (!this.peli.jatketaanko()) {
            peliPaattyi(true);
            return;
        }

        seuraavaVuoro(this.peli.getPelaaja());
    }
    
    private void eiOsuttuLaivaan() {
        seuraavaVuoro(this.peli.getTietokone());
    }
    
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
    
    public void paivitaKommentti(String kommentti) {
        if (ylaosa.getClass() == AmpumisKomponentit.class) {
            AmpumisKomponentit komponentit = (AmpumisKomponentit) ylaosa;
            komponentit.kommenttiPaivitys(kommentti);
        }
        else {
            LaivojenAsetusKomponentit komponentit = (LaivojenAsetusKomponentit) ylaosa;
            komponentit.kommenttiPaivitys(kommentti);
        }
        paivitaYlaOsa();
    }
    
    private void nuku() {
        try {
            Thread.sleep(400);
        }
        catch (InterruptedException ie) {
        }
    }
}