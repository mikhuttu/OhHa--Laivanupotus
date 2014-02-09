package Laivanupotus.Kayttoliittyma.Ylaosa;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Laivanupotus.Ohjaus.LaivojenLuoja;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.Suunta;

public class LuoLaivanKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JTextField sijaintiKentta;
    private JTextField suuntaKentta;
    
    public LuoLaivanKuuntelija(Kayttoliittyma kayttoliittyma, JTextField sijaintiKentta, JTextField suuntaKentta) {
        this.kayttoliittyma = kayttoliittyma;
        this.sijaintiKentta = sijaintiKentta;
        this.suuntaKentta = suuntaKentta;
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
