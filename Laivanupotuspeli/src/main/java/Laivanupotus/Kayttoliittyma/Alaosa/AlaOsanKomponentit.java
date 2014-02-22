package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Tietokone;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Alaosan toiminnallisuuden kehittäminen on täysin kesken, joten en viitsi sen metodeja varten alkaa Javadociakaan kirjoittamaan.
 * 
 * Tarkoituksena on toteuttaa seuraavaksi JButtoneista koostuva näkymä.
*/

public class AlaOsanKomponentit extends JPanel {
    private final GraafinenPelilauta vasen;
    private final GraafinenPelilauta oikea;
    
    public AlaOsanKomponentit(Kayttoliittyma kayttoliittyma) {
        this.setLayout(new GridLayout(1,3));
        this.vasen = new GraafinenPelilauta(kayttoliittyma, kayttoliittyma.getPeli().getPelaaja());
        this.oikea = new GraafinenPelilauta(kayttoliittyma, kayttoliittyma.getPeli().getTietokone());

        this.add(vasen);
        this.add(new JLabel());
        this.add(oikea);
    }
    
    public void luo() {
        vasen.repaint();
        oikea.repaint();
    }
    
    public void piirra(Kayttaja kayttaja) {
        if (kayttaja.getClass() != Tietokone.class) {
            vasen.varitaNappulat();
        }
        else {
            oikea.varitaNappulat();
        }
    }
    
    public void vaihdaPainikkeisiinPaasyPaittain() {
        vaihdaLaudanPainikkeisiinPaasy(vasen, false);
        vaihdaLaudanPainikkeisiinPaasy(oikea, true);
    }
    
    private void vaihdaLaudanPainikkeisiinPaasy(GraafinenPelilauta lauta, boolean paasy) {
         for (int i = 0; i < lauta.getKoko(); i++) {
             JPanel rivi = (JPanel) lauta.getComponent(i);
             
             for (int j = 0; j < lauta.getKoko(); j++) {
                 JButton nappula = (JButton) rivi.getComponent(j);
                 nappula.setEnabled(paasy);
             }
         }
    }
    
    public void estaPaasyTietokoneenLautaan() {
        vaihdaLaudanPainikkeisiinPaasy(oikea, false);
    }
    
    public void ruutuValittu(String sijaintiKentta) {
        oikea.varitaRuutu(sijaintiKentta);
    }
}