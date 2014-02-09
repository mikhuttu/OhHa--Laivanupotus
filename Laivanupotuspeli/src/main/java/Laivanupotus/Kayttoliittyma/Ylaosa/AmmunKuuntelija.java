
package Laivanupotus.Kayttoliittyma.Ylaosa;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import Laivanupotus.Sovelluslogiikka.Pelaaja;
import Laivanupotus.Sovelluslogiikka.Sijainti;

public class AmmunKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private JTextField sijaintikentta;
    
    public AmmunKuuntelija(Kayttoliittyma kayttoliittyma, JTextField sijaintikentta) {
        this.kayttoliittyma = kayttoliittyma;
        this.sijaintikentta = sijaintikentta;    
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Sijainti sijainti = new SijainninMaarittaja().palautaSijainti(sijaintikentta);
        Pelaaja pelaaja = (Pelaaja) this.kayttoliittyma.getPeli().getPelaaja();
        
        try {
            boolean osuiko = pelaaja.suoritaVuoro(this.kayttoliittyma.getPeli().getTietokone().getPelilauta(), sijainti);
            if (osuiko) {
                kayttoliittyma.osuttiinLaivaan();
            }
            else {
                kayttoliittyma.eiOsuttuLaivaan();
            }
        }
        catch (IllegalArgumentException e) {
            String virheIlmoitus = "Valitse ensin ruutu johon ampua.";
            
            if (sijainti != null) {
                virheIlmoitus = "Ruutuun on jo ammuttu. Valitse toinen ruutu.";     // palauttaa tämän myös jos sijainti ei koordinaatistossa. 
                                                                                    // "mahdoton tilanne"   
            }
            
            kayttoliittyma.paivitaKommentti(virheIlmoitus);
        }
    }
}
