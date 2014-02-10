
package Laivanupotus.Kayttoliittyma.Ylaosa;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import Laivanupotus.Sovelluslogiikka.Pelaaja;
import Laivanupotus.Sovelluslogiikka.Sijainti;

public class AmmunKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JTextField sijaintikentta;
    private JButton nappula;
    
    public AmmunKuuntelija(Kayttoliittyma kayttoliittyma, JTextField sijaintikentta, JButton nappula) {
        this.kayttoliittyma = kayttoliittyma;
        this.sijaintikentta = sijaintikentta;
        this.nappula = nappula;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        nappula.setEnabled(false);

        Sijainti sijainti = new SijainninMaarittaja().palautaSijainti(sijaintikentta);
        sijaintikentta.setText(null);
        
        Pelaaja pelaaja = (Pelaaja) this.kayttoliittyma.getPeli().getPelaaja();
        
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