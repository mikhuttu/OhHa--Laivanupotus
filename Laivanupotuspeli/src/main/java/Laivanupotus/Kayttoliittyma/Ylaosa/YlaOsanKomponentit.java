package Laivanupotus.Kayttoliittyma.Ylaosa;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Koska sekä AmpumisKomponenteilla että LaivanAsetusKomponenteilla on oma kommenttikenttä, tämä luokka hallitsee
 * niiden päivittämisen. 
 */

public abstract class YlaOsanKomponentit extends JPanel {
    
    protected JTextField kommentti;
    
    public JPanel getPanel() {
        return this;
    }
    
    public void kommenttiPaivitys(String paivitys) {
        this.kommentti.setText(paivitys);
    }
}