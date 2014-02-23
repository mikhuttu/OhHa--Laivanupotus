package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;

/**
 * Luokka suorittaa tekstikentän, johon on valittu asetettavan laivan suunta, sisällön päivittämisen.
 */

public class SuuntaNappienKuuntelija implements ActionListener {
    private JTextField suuntaKentta;
    private JButton alas;
    private Kayttoliittyma kayttoliittyma;
    
    public SuuntaNappienKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    public void tuoKomponentit(JTextField suuntaKentta, JButton alas) {
        this.suuntaKentta = suuntaKentta;
        this.alas = alas;
    }
    
    public String suuntaKenttaString() {
        return this.suuntaKentta.getText();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == alas) {
            suuntaKentta.setText("ALAS");
        }
        else {
            suuntaKentta.setText("OIKEALLE");
        }
        kayttoliittyma.LaivojenAsetusKomponenttejaKlikattu();
    }
}