package Laivanupotus.Kayttoliittyma.Aloitusnakyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;

public class AloitusNakyma {
    private Kayttoliittyma kayttoliittyma;
    private JPanel aloitusnakyma;
    
    public AloitusNakyma(Kayttoliittyma kayttoliittyma) {             
        this.kayttoliittyma = kayttoliittyma;                        
        this.aloitusnakyma = new JPanel(new GridLayout(3,1));         
    }                                                                   
    
    public JPanel getPanel() {
        return this.aloitusnakyma;
    }
    
    public void luo() {
        this.aloitusnakyma.add(new JLabel("LAIVANUPOTUSPELI"));
        this.aloitusnakyma.add(new JLabel("Valitse vaikeustaso:"));
        this.aloitusnakyma.add(nappulat());
    }
    
    private JPanel nappulat() {
        JPanel nappulat = new JPanel(new GridLayout(1,3));
        
        JButton easy = new JButton("EASY");
        JButton hard = new JButton("HARD");
        JButton impossible = new JButton("IMPOSSIBLE");
        
        NapinKuuntelija kuuntelija = new NapinKuuntelija(kayttoliittyma, easy, hard, impossible);
        
        easy.addActionListener(kuuntelija);
        hard.addActionListener(null);
        impossible.addActionListener(kuuntelija);
        
        nappulat.add(easy);
        nappulat.add(hard);
        nappulat.add(impossible);
        
        return nappulat;
    }
}
