package Laivanupotus.Kayttoliittyma.Ylaosa;

import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class YlaOsanKomponentit {
    
    protected JPanel ylaosa;
    protected JTextField kommentti;
    
    public JPanel getPanel() {
        return this.ylaosa;
    }
    
    public void kommenttiPaivitys(String paivitys) {
        this.kommentti.setText(paivitys);
    }
}