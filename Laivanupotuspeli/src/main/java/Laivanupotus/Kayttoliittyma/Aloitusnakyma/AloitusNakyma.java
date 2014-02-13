package Laivanupotus.Kayttoliittyma.Aloitusnakyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;

/**
 * AloitusNakyma perii JPanel -luokan ja sisältää aloitusnäkymän komponentit.
 */

public class AloitusNakyma extends JPanel {
    private NapinKuuntelija kuuntelija;
    
    public AloitusNakyma(Kayttoliittyma kayttoliittyma) {
        this.setLayout(new GridLayout(3,1));
        this.kuuntelija = new NapinKuuntelija(kayttoliittyma);                     
    }                                                                   
    
    public JPanel getPanel() {
        return this;
    }
    
    public void luo() {
        this.add(new JLabel("LAIVANUPOTUSPELI"));
        this.add(new JLabel("Valitse vaikeustaso:"));
        this.add(nappulat());
    }
    
    private JPanel nappulat() {
        JPanel nappulat = new JPanel(new GridLayout(1,3));
        
        JButton easy = new JButton("EASY");
        JButton hard = new JButton("HARD");
        JButton impossible = new JButton("IMPOSSIBLE");
        
        easy.addActionListener(kuuntelija);
        hard.addActionListener(kuuntelija);
        hard.setEnabled(false);
        impossible.addActionListener(kuuntelija);
        
        kuuntelija.tuoKomponentit(easy, hard, impossible);
        
        nappulat.add(easy);
        nappulat.add(hard);
        nappulat.add(impossible);
        
        return nappulat;
    }
}
