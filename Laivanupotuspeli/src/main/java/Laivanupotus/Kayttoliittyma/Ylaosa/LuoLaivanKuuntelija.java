package Laivanupotus.Kayttoliittyma.Ylaosa;


import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Laivanupotus.Ohjaus.LaivojenLuoja;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.Suunta;

import java.util.Random;

public class LuoLaivanKuuntelija implements ActionListener {
    private JTextField sijaintiKentta;
    private JTextField suuntaKentta;
    private Kayttoliittyma kayttoliittyma;
    
    public LuoLaivanKuuntelija(JTextField sijaintiKentta, JTextField suuntaKentta, Kayttoliittyma kayttoliittyma) {
        this.sijaintiKentta = sijaintiKentta;
        this.suuntaKentta = suuntaKentta;
        this.kayttoliittyma = kayttoliittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Suunta suunta = palautaTekstiKentanSisaltoaVastaavaSuunta();
        Sijainti sijainti = palautaTekstiKentanSisaltoaVastaavaSijainti();
        
        Random arpoja = new Random();
        
        
        sijainti = new Sijainti(arpoja.nextInt(6), arpoja.nextInt(6));
        
        LaivojenLuoja luoja = new LaivojenLuoja(kayttoliittyma.getPeli());
        try {
            luoja.asetaPelaajanLaudalleLaiva(suunta, sijainti);
            kayttoliittyma.laivaAsetettiinPelilaudalle();
        }
        catch (IllegalArgumentException e) {
            kayttoliittyma.laivanAsetusPelilaudalleEiOnnistunut();
        }
    }
    
    
    private Suunta palautaTekstiKentanSisaltoaVastaavaSuunta() {
        if (suuntaKentta.getText().equals("")) {
            return null;
        }
            String sisalto = this.suuntaKentta.getText();
        
        if (sisalto.equals("ALAS")) {
            return Suunta.ALAS;
        }
        return Suunta.OIKEALLE;
    }
    
    private Sijainti palautaTekstiKentanSisaltoaVastaavaSijainti() {
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
