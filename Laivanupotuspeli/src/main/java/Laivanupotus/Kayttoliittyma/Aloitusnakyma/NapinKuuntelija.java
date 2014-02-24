package Laivanupotus.Kayttoliittyma.Aloitusnakyma;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.tietokonealy.Aly;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * NapinKuuntelija liittyy AloitusNakymaan, ja kun jotain sen JButton -napeista klikataan, tämä luokka toimittaa
 * siitä tiedon Kayttoliittymalle.
 */

public class NapinKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JButton hard;
    private JButton impossible;
    
    public NapinKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    public void tuoKomponentit(JButton hard, JButton impossible) {
        this.hard = hard;
        this.impossible = impossible;
    }
    
    /**
     * Klikattu painike määrittää pelin tekoälyn.
     * @param ae 
     */
    
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
