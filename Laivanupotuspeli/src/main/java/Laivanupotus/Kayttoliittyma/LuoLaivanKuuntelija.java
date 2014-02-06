package Laivanupotus.Kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Laivanupotus.Ohjaus.LaivojenLuoja;
import Laivanupotus.Sovelluslogiikka.Peli;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.Suunta;

public class LuoLaivanKuuntelija implements ActionListener {
    private JTextField sijaintiKentta;
    private JTextField suuntaKentta;
    private Peli peli;
    
    public LuoLaivanKuuntelija(JTextField sijaintiKentta, JTextField suuntaKentta, Peli peli) {
        this.sijaintiKentta = sijaintiKentta;
        this.suuntaKentta = suuntaKentta;
        this.peli = peli;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Suunta suunta = palautaTekstiKentanSisaltoaVastaavaSuunta();
        Sijainti sijainti = palautaTekstiKentanSisaltoaVastaavaSijainti();
        
        LaivojenLuoja luoja = new LaivojenLuoja(peli);
        try {
            luoja.asetaPelaajanLaudalleLaiva(suunta, sijainti);
        }
        catch (IllegalArgumentException e) {
            // lähetä tieto eteenpäin että laivan asettaminen epäonnistui
        }
    }
    
    
    private Suunta palautaTekstiKentanSisaltoaVastaavaSuunta() {
        if (suuntaKentta == null) {
            return null;
        }
            String sisalto = this.suuntaKentta.getText();
        
        if (sisalto.equals("ALAS")) {
            return Suunta.ALAS;
        }
        return Suunta.OIKEALLE;
    }
    
    private Sijainti palautaTekstiKentanSisaltoaVastaavaSijainti() {
        if (sijaintiKentta.getText() == null) {
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
