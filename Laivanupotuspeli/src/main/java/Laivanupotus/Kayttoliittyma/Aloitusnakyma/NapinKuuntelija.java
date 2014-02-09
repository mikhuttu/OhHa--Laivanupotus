package Laivanupotus.Kayttoliittyma.Aloitusnakyma;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class NapinKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JButton easy;
    private JButton hard;
    private JButton impossible;
    
    public NapinKuuntelija(Kayttoliittyma kayttoliittyma, JButton easy, JButton hard, JButton impossible) {
        this.kayttoliittyma = kayttoliittyma;
        this.easy = easy;
        this.hard = hard;
        this.impossible = impossible;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Aly aly = Aly.EASY;
        
        if (ae.getSource() == hard) {
            aly = Aly.HARD;
        }
        else if (ae.getSource() == impossible) {
            aly = Aly.IMPOSSIBLE;
        }
        
        kayttoliittyma.aloitaPeli(aly);
    }
}
