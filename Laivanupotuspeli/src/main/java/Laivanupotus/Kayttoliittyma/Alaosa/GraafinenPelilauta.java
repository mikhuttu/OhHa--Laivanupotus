package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Sovelluslogiikka.Tietokone;
import Laivanupotus.Tyokalut.Sijainti;


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
        
        luoPainikkeet(kayttoliittyma);
    }
    
    private void luoPainikkeet(Kayttoliittyma kayttoliittyma) {
        
        for (int j = 0; j < koko; j++) {
            
            JPanel rivi = new JPanel(new GridLayout(1, koko));
            
            for (int i = 0; i < koko; i++) {    
                String sijainti = haeSijaintiText(i,j);
                PelilaudanNappulanKuuntelija kuuntelija = new PelilaudanNappulanKuuntelija(kayttoliittyma, sijainti);
                
                JButton nappula = new JButton();
                nappula.setBackground(Color.WHITE);
                
                nappula.addActionListener(kuuntelija);
                nappula.setEnabled(painettavissa);
                
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
    
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        piirra(graphics);
    }
    
    
    public void piirra(Graphics graphics) {
        Pelilauta pelilauta = kayttaja.getPelilauta();
        
        for (int j = 0; j < koko; j++) {
            for (int i = 0; i < koko; i++) {
                Ruutu ruutu = pelilauta.haeRuutu(new Sijainti(i, j));
                
                JButton nappula = haeNappula(ruutu.getSijainti());
                
                char merkki = haeMerkki(ruutu.getSijainti());
                piirra(graphics, nappula, merkki);
            }
        }
    }
        
    private void piirra(Graphics graphics, JButton nappula, char merkki) {
        
        if (merkki == '~') {
            nappula.setBackground(Color.WHITE);
        }
        else if (merkki == 'O') {
            nappula.setBackground(Color.BLACK);
        }
        else if (merkki == 'x') {
            nappula.setBackground(Color.CYAN);
        }
        else if (merkki == 'X') {
            nappula.setBackground(Color.RED);
        }
        else {  // merkki == 'L'
            nappula.setBackground(Color.BLUE);
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
}
