package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;


public class AmpumisKomponentit extends YlaOsanKomponentit {
    private Kayttoliittyma kayttoliittyma;
    private JTextField sijainti;
    
    public AmpumisKomponentit(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        ylaosa = new JPanel(new GridLayout(4,1));
    }
    
    public void luo() {
        ylaosa.add(ylakentta());
        ylaosa.add(alakentta());
        ylaosa.add(kommenttikentta());
        ylaosa.add(new JPanel());
    }
    
    private JPanel ylakentta() {
        JPanel ylakentta = new JPanel(new GridLayout(1,1));
        
        JLabel valitseRuutu = new JLabel("Valitse ammuttava ruutu oik. pelilaudalta: ");
        
        ylakentta.add(valitseRuutu);
        return ylakentta;
    }
    
    private JPanel alakentta() {
        JPanel alakentta = new JPanel(new GridLayout(1,3));
        
        sijainti = new JTextField();
        sijainti.setEnabled(true);              // testauksen vuoksi
        sijainti.addActionListener(null);
        
        JButton ammu = new JButton("AMMU");
        ammu.addActionListener(new AmmunKuuntelija(kayttoliittyma, sijainti, ammu));
        
        alakentta.add(sijainti);
        alakentta.add(new JLabel());
        alakentta.add(ammu);
        
        return alakentta;
    }
    
    private JPanel kommenttikentta() {
        JPanel kommenttikentta = new JPanel(new GridLayout(1,2));
        
        kommentti = new JTextField();
        kommentti.setEnabled(false);

        kommenttikentta.add(kommentti);
        kommenttikentta.add(new JPanel());
        
        return kommenttikentta;
    }
}