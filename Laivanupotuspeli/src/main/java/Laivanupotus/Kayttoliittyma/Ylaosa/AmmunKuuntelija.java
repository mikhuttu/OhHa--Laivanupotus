package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Tyokalut.SijainninMaarittaja;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Luokka toimii "AMMU" nappulan kuuntelijana ja sen tarkoituksena on "viestittää" käyttöliittymälle
 * tieto siitä että nappulaa on painettu.
 */

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
    
    /**
     * Mikäli keskeytystä ei Kayttaja olion suoritaVuoro metodin aikana tule, kayttoliittymälle kerrotaan että pelaaja ampui.
     * Jos tulee, pelaaja saa jatkaa sillä hän ei ollut valinnut ammuttavaa ruutua, tai yritti ampua jo ammuttuun ruutuun.
     * @param ae  
     */
    @Override
    public void actionPerformed(ActionEvent ae) { 
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
                virheIlmoitus = "Ruutuun on jo ammuttu. Valitse toinen ruutu.";
            }
            
            kayttoliittyma.paivitaKommentti(virheIlmoitus);
        }
        
        if (kayttoliittyma.getPeli().jatketaanko()) {
            nappula.setEnabled(true);    
        }
    }
}