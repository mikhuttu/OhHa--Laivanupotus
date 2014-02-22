package Laivanupotus.Tyokalut;

import javax.swing.JTextField;

/**
 * SijainninMaarittajaa käytetään yleisluokkana eri käyttöliittymäkomponenteista.
 * 
 * Sen tarkoituksena on palauttaa tekstikentän sisältöä (muotoa 'x,y') vastaava sijainti.
 * 
 * Jos sisältö ei ole kyseistä muotoa, ohjelma kaatuu, mutta jatkossa sijaintia ei voi tekstikenttään kirjoittaa itse, joten
 * sillä ei niinkään väliä.
 */

public class SijainninMaarittaja {
    
    public Sijainti palautaSijainti(JTextField sijaintiKentta) {    // sijainti muotoa 'x,y'
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
