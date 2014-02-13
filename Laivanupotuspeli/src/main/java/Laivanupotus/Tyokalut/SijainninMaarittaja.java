package Laivanupotus.Tyokalut;

import javax.swing.JTextField;
import Laivanupotus.Sovelluslogiikka.Sijainti;

public class SijainninMaarittaja {
    
    public Sijainti palautaSijainti(JTextField sijaintiKentta) {    // sijainti muotoa 'x,y'
        if (sijaintiKentta.getText().equals("")) {
            return null;
        }
        
        String teksti = sijaintiKentta.getText();
        String x = "";
        String y = "";
        boolean pilkku = false;
        
        for (int i = 0; i < teksti.length(); i++) {
            char merkki = teksti.charAt(i);

            try {
                Integer.parseInt("" + merkki);
                if (!pilkku) {
                    x += merkki;
                }
                else {
                    y += merkki;
                }
            }
            catch (NumberFormatException e) {
                if (merkki == ',') {
                    pilkku = true;
                }
            }
            
        }
        
        int Xsij = Integer.parseInt(x);
        int Ysij = Integer.parseInt(y);
        
        return new Sijainti(Xsij, Ysij);
    }    
}
