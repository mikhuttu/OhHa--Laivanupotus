package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;

/**
 * Luokka huolehtii nimensä mukaisesti AmpumisKomponenteista.
 * Samoin kuten LaivojenAsetusKomponentit, perii YlaOsanKomponentit luokan (joka perii JPanelin).
 */

public class AmpumisKomponentit extends YlaOsanKomponentit {
    private AmmunKuuntelija kuuntelija;
    
    public AmpumisKomponentit(Kayttoliittyma kayttoliittyma) {
        this.kuuntelija = new AmmunKuuntelija(kayttoliittyma);
        this.setLayout(new GridLayout(4,1));
    }
    
    public void luo() {
        this.add(ylakentta());
        this.add(alakentta());
        this.add(kommenttikentta());
        this.add(new JLabel());
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
        sijainti.setEnabled(false);
        
        JButton ammu = new JButton("AMMU");
        ammu.addActionListener(kuuntelija);
        
        kuuntelija.tuoKomponentit(sijainti, ammu);
        
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
    
    public void estaPaasyAmmuNappulaan() {
        JPanel komponentti = (JPanel) this.getComponent(1);
        JButton ammu = (JButton) komponentti.getComponent(1);
        ammu.setEnabled(false);
    }
}