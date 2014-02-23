package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Sovelluslogiikka.Tietokone;
import Laivanupotus.Tyokalut.LaivanKoonMaarittaja;
import Laivanupotus.Tyokalut.SijainninMaarittaja;
import Laivanupotus.Tyokalut.Sijainti;
import Laivanupotus.Tyokalut.Suunta;


public class GraafinenPelilauta extends JPanel {
    private final Kayttaja kayttaja;
    private final int koko = 6;
    private boolean painettavissa;

    public GraafinenPelilauta(Kayttoliittyma kayttoliittyma, Kayttaja kayttaja) {
        this.setLayout(new GridLayout(koko, koko));
        this.kayttaja = kayttaja;
        
        if (kayttaja.getClass() == Tietokone.class) {
            painettavissa = false;
        }
        else {
            painettavissa = true;
        }
        
        luoPainikkeet(kayttoliittyma, kayttaja);
    }
    
    private void luoPainikkeet(Kayttoliittyma kayttoliittyma, Kayttaja kayttaja) {
        
        for (int j = 0; j < koko; j++) {
            
            JPanel rivi = new JPanel(new GridLayout(1, koko));
            
            for (int i = 0; i < koko; i++) {    
                String sijainti = haeSijaintiText(i,j);
                
                JButton nappula = new JButton();
                nappula.setBackground(Color.WHITE);
                nappula.setEnabled(painettavissa);
                
                PelilaudanNappulanKuuntelija kuuntelija = new PelilaudanNappulanKuuntelija(kayttoliittyma, kayttaja, sijainti);
                nappula.addActionListener(kuuntelija);
                
                rivi.add(nappula);
            }
            this.add(rivi);
        }
    }
    
    private String haeSijaintiText(Integer i, Integer j) {
        return i.toString() + "," + j.toString();
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    
    public void varitaNappulat() {
        Pelilauta pelilauta = kayttaja.getPelilauta();
        
        for (int j = 0; j < koko; j++) {
            for (int i = 0; i < koko; i++) {
                Ruutu ruutu = pelilauta.haeRuutu(new Sijainti(i, j));
                
                JButton nappula = haeNappula(ruutu.getSijainti());
                
                char merkki = haeMerkki(ruutu.getSijainti());
                varita(nappula, merkki);
            }
        }
    }
        
    private void varita(JButton nappula, char merkki) {
        
        if (merkki == '~') {
            nappula.setBackground(Color.WHITE);
        }
        else if (merkki == 'O') {
            nappula.setBackground(Color.BLUE);
        }
        else if (merkki == 'x') {
            nappula.setBackground(Color.CYAN);
        }
        else if (merkki == 'X') {
            nappula.setBackground(Color.RED);
        }
        else {  // merkki == 'L'
            nappula.setBackground(Color.BLACK);
        }
    }
    
    private char haeMerkki(Sijainti sijainti) {
        PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(kayttaja);
        return piirtaja.palautaSeuraavaMerkki(sijainti);
    }
    
    private JButton haeNappula(Sijainti sijainti) {
        JPanel rivi = (JPanel) this.getComponent(sijainti.getY());
        return (JButton) rivi.getComponent(sijainti.getX());
    }
    
    public void varitaRuutu(String sijaintiKentta) {
        Sijainti sijainti = new SijainninMaarittaja().maaritaSijainti(sijaintiKentta);
        varitaNappulat();
        varitaRuutu(sijainti);
    }
    
    private void varitaRuutu(Sijainti sijainti) {
        JButton nappula = haeNappula(sijainti);
        
        if (nappula.getBackground() == Color.WHITE) {
            nappula.setBackground(Color.PINK);    
        }
    }
    
    public void laivanAsettaminen(String sijaintiKentta, String suuntaKentta) {
        Laiva laiva = maaritaLaiva(sijaintiKentta, suuntaKentta, kayttaja.getPelilauta().getLaivat().size());
        varitaRuudut(laiva);
    }
    
    public Laiva maaritaLaiva(String sijaintiKenttaText, String suuntaKenttaText, int laivoja) {
        int laivanKoko = new LaivanKoonMaarittaja().maaritaKoko(laivoja);
        Sijainti sijainti = new SijainninMaarittaja().maaritaSijainti(sijaintiKenttaText);
        Suunta suunta = Suunta.ALAS;
        
        if (suuntaKenttaText.equals("OIKEALLE")) {
            suunta = Suunta.OIKEALLE;
        }
        
        return new Laiva(sijainti, suunta, laivanKoko);
    }
    
    private void varitaRuudut(Laiva laiva) {
        varitaNappulat();
        
        if (kayttaja.getPelilauta().voidaankoAsettaa(laiva)) {
        
            for (int i = 0; i < laiva.getKoko(); i++) {
                Sijainti sijainti = laiva.haeLaivanOsanSijainti(i);
                varitaRuutu(sijainti);
            }
        }
    }
}
