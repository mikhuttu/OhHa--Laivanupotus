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
    private JPanel ylaosa;
    private JPanel vasen;
    private JPanel oikea;
    
    private Peli peli;
    
    public Kayttoliittyma() {
        this.peli = new Peli(Aly.EASY, new Lukija());
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
        
        luoYlaOsanKomponentit();
        luoVasemmanKomponentit();
        luoOikeanKomponentit();
        
        JPanel alaosa = new JPanel(new GridLayout(2,1));
        alaosa.add(vasen);
        alaosa.add(oikea);
        
        container.add(ylaosa, BorderLayout.NORTH);
//        container.add(new JPanel(), BorderLayout.WEST);
//        container.add(new JPanel(), BorderLayout.EAST);
    }
    
    private void luoYlaOsanKomponentit() {
        ylaosa = new LuoYlaosanLaivojenAsetusKomponetit().luo();
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
}