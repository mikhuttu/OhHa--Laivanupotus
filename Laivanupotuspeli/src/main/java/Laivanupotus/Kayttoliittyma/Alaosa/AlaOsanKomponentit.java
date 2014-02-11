package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.GridLayout;
import javax.swing.JPanel;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Pelaaja;
import Laivanupotus.Sovelluslogiikka.Peli;

public class AlaOsanKomponentit extends JPanel {
    private PiirtoAlusta vasen;
//    private PiirtoAlusta keski;
    private PiirtoAlusta oikea;
    
    public AlaOsanKomponentit(Peli peli) {
        this.setLayout(new GridLayout(1,3));
        this.vasen = new PiirtoAlusta(peli.getPelaaja());
//        this.keski = new PiirtoAlusta();
        this.oikea = new PiirtoAlusta(peli.getTietokone());
        
        this.add(vasen);
//        this.add(keski);
        this.add(oikea);
    }
    
    public void luo() {
        vasen.repaint();
        oikea.repaint();
    }
    
    public void piirra(Kayttaja kayttaja) {
        if (kayttaja.getClass() == Pelaaja.class) {
            vasen.repaint();
        }
        else {
            oikea.repaint();
        }
    }
}