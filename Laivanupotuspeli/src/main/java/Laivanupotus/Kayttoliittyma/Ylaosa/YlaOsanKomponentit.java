package Laivanupotus.Kayttoliittyma.Ylaosa;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Koska sekä AmpumisKomponenteilla että LaivanAsetusKomponenteilla on oma kommenttikenttä, tämä luokka hallitsee
 * niiden päivittämisen.
 * 
 * Ehkä toteutuksena vähän turhan sekava, mutta säästää ainakin tilaa jos jotain..
 */

public abstract class YlaOsanKomponentit extends JPanel {
    
    protected JTextField kommentti;
    protected JTextField sijainti;
    
    public JPanel getPanel() {
        return this;
    }
    
    public void kommenttiPaivitys(String paivitys) {
        this.kommentti.setText(paivitys);
    }
    
    public void sijaintiPaivitys(String paivitys) {
        this.sijainti.setText(paivitys);
    }
    
    public String getSijaintiString() {
        return this.sijainti.getText();
    }
}