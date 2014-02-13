package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.LaivojenLuoja;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.SijainninMaarittaja;
import Laivanupotus.Tyokalut.Suunta;


public class LuoLaivanKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JTextField sijaintiKentta;
    private JTextField suuntaKentta;
    
    public LuoLaivanKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    public void tuoSuuntaKentta(JTextField suuntaKentta) {
        this.suuntaKentta = suuntaKentta;
    }
    
    public void tuoSijaintiKentta(JTextField sijaintiKentta) {
        this.sijaintiKentta = sijaintiKentta;
    }
    
    public JTextField getSuuntaKentta() {
        return this.suuntaKentta;
    }    
    
    public JTextField getSijaintiKentta() {
        return this.sijaintiKentta;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        LaivojenLuoja luoja = new LaivojenLuoja(kayttoliittyma.getPeli());
        Sijainti sijainti = new SijainninMaarittaja().palautaSijainti(sijaintiKentta);
        Suunta suunta = palautaTekstiKentanSisaltoaVastaavaSuunta(suuntaKentta);
        
        try {
            luoja.asetaPelaajanLaudalleLaiva(suunta, sijainti);
            kayttoliittyma.laivaAsetettiinPelilaudalle();
        }
        
        catch (IllegalArgumentException e) {
            String virheIlmoitus = "Tähän paikkaan ei voi luoda laivaa.";
            if (suunta == null || sijainti == null) {
                virheIlmoitus = "Valitse laivalle ensin suunta ja sijainti.";
            }
            
            kayttoliittyma.paivitaKommentti(virheIlmoitus);
        }
    }
    
    private Suunta palautaTekstiKentanSisaltoaVastaavaSuunta(JTextField suuntaKentta) {
        if (suuntaKentta.getText().equals("")) {
            return null;
        }
            String sisalto = suuntaKentta.getText();
        
        if (sisalto.equals("ALAS")) {
            return Suunta.ALAS;
        }
        return Suunta.OIKEALLE;
    }
}
