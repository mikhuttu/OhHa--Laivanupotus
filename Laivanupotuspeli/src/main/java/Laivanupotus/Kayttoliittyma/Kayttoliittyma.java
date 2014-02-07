package Laivanupotus.Kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import Laivanupotus.Sovelluslogiikka.Peli;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import Laivanupotus.Tyokalut.Lukija;

public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private JPanel vasen;
    private JPanel oikea;
    
    private LuoYlaosanLaivojenAsetusKomponetit ylaosa;
    
    private Peli peli;
    
    public Kayttoliittyma() {
        this.peli = new Peli(Aly.EASY, new Lukija());
        this.ylaosa = new LuoYlaosanLaivojenAsetusKomponetit(this);
    }
    
    @Override
    public void run() {
        frame = new JFrame("Laivanupotus");
        frame.setPreferredSize(new Dimension(640,480));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        luoJPanelienKomponentit(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    
    private void luoJPanelienKomponentit(Container container) {
        container.setLayout(new BorderLayout());
        
        luoYlaOsanKomponentit(container);
        luoVasemmanKomponentit();
        luoOikeanKomponentit();
        
        JPanel alaosa = new JPanel(new GridLayout(2,1));
        alaosa.add(vasen);
        alaosa.add(oikea);

//        container.add(new JPanel(), BorderLayout.WEST);
//        container.add(new JPanel(), BorderLayout.EAST);
    }
    
    private void luoYlaOsanKomponentit(Container container) {
        int laivanIndeksi = peli.getPelaaja().getPelilauta().getLaivat().size() + 1;
        JPanel ylhaalla = ylaosa.luo(laivanIndeksi);
        
        container.add(ylhaalla, BorderLayout.NORTH);
    }
        
    private void luoVasemmanKomponentit() {
        vasen = new JPanel(new GridLayout(6,6));
    }
    
    private void luoOikeanKomponentit() {
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
        if (laivoja < 4) {
            JPanel paivitetty = ylaosa.paivita(laivoja + 1);
            frame.getContentPane().add(paivitetty, BorderLayout.NORTH);
            frame.pack();
        }
        else {
        }
    }
    
    public void laivanAsetusPelilaudalleEiOnnistunut() {
        
    }
}