package Laivanupotus.Kayttoliittyma.Ylaosa;

import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class YlaOsanKomponentit extends JPanel {
    
    protected JTextField kommentti;
    
    public JPanel getPanel() {
        return this;
    }
    
    public void kommenttiPaivitys(String paivitys) {
        this.kommentti.setText(paivitys);
    }
}