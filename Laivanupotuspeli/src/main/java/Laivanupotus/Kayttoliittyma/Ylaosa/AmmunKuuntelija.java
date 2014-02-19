package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Tyokalut.SijainninMaarittaja;
import Laivanupotus.Tyokalut.Sijainti;

public class AmmunKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JTextField sijaintiKentta;
    private JButton nappula;
    
    public AmmunKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    public void tuoKomponentit(JTextField sijaintiKentta, JButton nappula) {
        this.sijaintiKentta = sijaintiKentta;
        this.nappula = nappula;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
         // bugi: osuiko saa arvon false, joten "pelaaja.suoritaVuoro" toimii.
         // try-haaran metodista "kayttoliittyma.pelaajaAmpui(osuiko)" saatetaan päätyä catch- haaraan suorittamatta metodia loppuun.
         // ko. metodi palauttaa IllegalArgumentException ennen kuin tietokone pystyy ampumaan
        
        nappula.setEnabled(false);
        
        Sijainti sijainti = new SijainninMaarittaja().palautaSijainti(sijaintiKentta);
        
        Kayttaja pelaaja = this.kayttoliittyma.getPeli().getPelaaja();
        
        try {
            boolean osuiko = pelaaja.suoritaVuoro(this.kayttoliittyma.getPeli().getTietokone().getPelilauta(), sijainti);
            kayttoliittyma.pelaajaAmpui(osuiko);
        }
        catch (IllegalArgumentException e) {
            String virheIlmoitus = "Valitse ensin ruutu johon ampua.";
            
            if (sijainti != null) {
                virheIlmoitus = "Ruutuun on jo ammuttu. Valitse toinen ruutu.";     // palauttaa tämän myös jos sijainti ei koordinaatistossa. 
                                                                                    // "mahdoton tilanne"   
            }
            
            kayttoliittyma.paivitaKommentti(virheIlmoitus);
        }
        nappula.setEnabled(true);
    }
}