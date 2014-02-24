package Laivanupotus.Tyokalut;

import javax.swing.JTextField;

/**
 * SijainninMaarittajaa käytetään yleisluokkana eri käyttöliittymäkomponenteista.
 * 
 * Sen tarkoituksena on palauttaa String-arvoa (x,y) vastaava sijainti.
 */

public class SijainninMaarittaja {
    
    public Sijainti palautaSijainti(JTextField sijaintiKentta) {
        if (sijaintiKentta.getText().equals("")) {
            return null;
        }
        
        String teksti = sijaintiKentta.getText();
        return maaritaSijainti(teksti);
    }
    
    public Sijainti maaritaSijainti(String sijaintiKentta) {
        
        String x = "";
        String y = "";
        boolean pilkku = false;
        
        for (int i = 0; i < sijaintiKentta.length(); i++) {
            char merkki = sijaintiKentta.charAt(i);

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
