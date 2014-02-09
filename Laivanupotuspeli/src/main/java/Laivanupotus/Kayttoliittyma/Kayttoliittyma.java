package Laivanupotus.Kayttoliittyma;

import Laivanupotus.Kayttoliittyma.Ylaosa.LaivojenAsetusKomponentit;
import Laivanupotus.Kayttoliittyma.Ylaosa.AmpumisKomponentit;
import Laivanupotus.Kayttoliittyma.Ylaosa.YlaOsanKomponentit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Laivanupotus.Ohjaus.PelilaudanPiirtaja;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Peli;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Lukija;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private JPanel vasen;
    private JPanel oikea;
    private YlaOsanKomponentit ylaosa;
    
    private final Peli peli;
    
    public Kayttoliittyma() {
        this.peli = new Peli(Aly.EASY, new Lukija());
        this.ylaosa = new LaivojenAsetusKomponentit(this);
    }
    
    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoKomponentitAlussa();
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentitAlussa() {
        frame.getContentPane().setLayout(new BorderLayout());
        
        luoLaivojenAsetusKomponentit();
        
        // luoVasenPelilauta();
        // luoOikeaPelilauta();
        
        // JPanel alaosa = new JPanel(new GridLayout(2,1));
        // alaosa.add(vasen);
        // alaosa.add(oikea);
        //        
        // frame.getContentPane().add(alaosa, BorderLayout.SOUTH);
    }
    
    private void luoLaivojenAsetusKomponentit() {
        int laivanIndeksi = peli.getPelaaja().getPelilauta().getLaivat().size() + 1;
        LaivojenAsetusKomponentit komponentit = (LaivojenAsetusKomponentit) ylaosa;
        komponentit.luo(laivanIndeksi);
        
        paivitaYlaOsa();
    }
        
    private void luoVasenPelilauta() {
        vasen = new JPanel(new GridLayout(6,6));
    }
    
    private void luoOikeaPelilauta() {
        oikea = new JPanel(new GridLayout(6,6));
    }
    
    public JFrame getFrame() {
        return frame;
    }
    
    public Peli getPeli() {
        return this.peli;
    }
    
    public void laivaAsetettiinPelilaudalle() {
        int laivoja = peli.getPelaaja().getPelilauta().getLaivat().size();
        
        LaivojenAsetusKomponentit komponentit = (LaivojenAsetusKomponentit) ylaosa;
        komponentit.seuraava(laivoja + 1);
        piirraPelilauta(this.peli.getPelaaja());
        
        if (laivoja < 4) {
            paivitaYlaOsa();
        }
        else {
            luoAmpumisKomponentit();
        }
    }
    
    public void laivanAsetusPelilaudalleEiOnnistunut() {
        LaivojenAsetusKomponentit komponentit = (LaivojenAsetusKomponentit) ylaosa;
        komponentit.kommenttiPaivitys("Tähän paikkaan ei voi luoda laivaa.");
        paivitaYlaOsa();
    }
    
    private void piirraPelilauta(Kayttaja kayttaja) {
        PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(kayttaja);
        piirtaja.piirraPelilauta();
    }
    
    private void luoAmpumisKomponentit() {
        this.ylaosa = new AmpumisKomponentit(this);
        paivitaYlaOsa();
        
        AmpumisKomponentit komponentit = (AmpumisKomponentit) ylaosa;
        komponentit.luo();
        paivitaYlaOsa();
    }
    
    private void paivitaYlaOsa() {
        JPanel paivitetty = ylaosa.getPanel();
        frame.getContentPane().add(paivitetty, BorderLayout.NORTH);
        frame.pack();
    }
}