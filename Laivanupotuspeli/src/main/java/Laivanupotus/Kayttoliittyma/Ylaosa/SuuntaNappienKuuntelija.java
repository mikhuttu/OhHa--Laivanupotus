package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Luokka suorittaa tekstikentän, johon on valittu asetettavan laivan suunta, sisällön päivittämisen.
 */

public class SuuntaNappienKuuntelija implements ActionListener {
    private final JTextField suuntaKentta;
    private final JButton alas;
    private final JButton oikealle;
    
    public SuuntaNappienKuuntelija(JTextField suuntaKentta, JButton alas, JButton oikealle) {
        this.suuntaKentta = suuntaKentta;
        this.alas = alas;
        this.oikealle = oikealle;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == alas) {
            suuntaKentta.setText("ALAS");
        }
        else {
            suuntaKentta.setText("OIKEALLE");
        }
    }
}