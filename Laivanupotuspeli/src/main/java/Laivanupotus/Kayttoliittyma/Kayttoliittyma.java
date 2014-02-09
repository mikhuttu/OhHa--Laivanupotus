package Laivanupotus.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Laivanupotus.Kayttoliittyma.Ylaosa.LaivojenAsetusKomponentit;
import Laivanupotus.Kayttoliittyma.Ylaosa.AmpumisKomponentit;
import Laivanupotus.Kayttoliittyma.Ylaosa.YlaOsanKomponentit;
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
        
        luoKomponentit();
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoKomponentit() {
        frame.getContentPane().setLayout(new BorderLayout());
        
        luoLaivojenAsetusKomponentit();
        
         luoVasenPelilauta();
         luoOikeaPelilauta();
        
         JPanel alaosa = new JPanel(new GridLayout(2,1));
         alaosa.add(vasen);
         alaosa.add(oikea);
                
         frame.getContentPane().add(alaosa, BorderLayout.SOUTH);
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
        PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(kayttaja);
        piirtaja.piirraPelilauta();
    }
    
    private void luoAmpumisKomponentit() {
        this.ylaosa = new AmpumisKomponentit(this);
        
        AmpumisKomponentit komponentit = (AmpumisKomponentit) ylaosa;
        komponentit.luo();
        paivitaYlaOsa();
    }
    
    private void paivitaYlaOsa() {
        JPanel paivitetty = ylaosa.getPanel();
        
        if (frame.getContentPane().getComponentCount() > 0) {
            frame.getContentPane().remove(0);                           // poistaa vanhan JPanel-olion.
        }
        
        frame.getContentPane().add(paivitetty, BorderLayout.NORTH, 0);  // asettaa uuden JPanel-olion vanhan paikalle.
        frame.pack();
    }
    
    
    public void osuttiinLaivaan() {
         paivitaKommentti("");
        // soita joku ääni koska osuttiin?
        // seuraavaksi tietokoneen vuoro
    }
    
    public void eiOsuttuLaivaan() {
        paivitaKommentti("");
        // seuraavaksi tietokoneen vuoro
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
}