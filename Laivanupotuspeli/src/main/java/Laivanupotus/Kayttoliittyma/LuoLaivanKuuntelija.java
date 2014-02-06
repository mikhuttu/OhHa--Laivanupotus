package Laivanupotus.Kayttoliittyma;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.Suunta;

public class LuoLaivanKuuntelija implements ActionListener {
    private JTextField sijainti;
    private JTextField suunta;
    
    public LuoLaivanKuuntelija(JTextField sijainti, JTextField suunta) {
        this.sijainti = sijainti;
        this.suunta = suunta;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Suunta s = palautaTekstiKentanSisaltoaVastaavaSuunta();
        Sijainti sij = palautaTekstiKentanSisaltoaVastaavaSijainti();
    }
    
    
    private Suunta palautaTekstiKentanSisaltoaVastaavaSuunta() {
        String sisalto = this.suunta.getText();
        
        if (sisalto.equals("ALAS")) {
            return Suunta.ALAS;
        }
        return Suunta.OIKEALLE;
    }
    
    private Sijainti palautaTekstiKentanSisaltoaVastaavaSijainti() {
        String teksti = sijainti.getText();
        char x = teksti.charAt(0);
        
        return new Sijainti(0,0);
    }
}
