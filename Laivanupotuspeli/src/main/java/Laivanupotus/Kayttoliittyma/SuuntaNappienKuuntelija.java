package Laivanupotus.Kayttoliittyma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import Laivanupotus.Tyokalut.Suunta;

public class SuuntaNappienKuuntelija implements ActionListener {
    private JTextField suuntaKentta;
    private JButton alas;
    private JButton oikealle;
    
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
