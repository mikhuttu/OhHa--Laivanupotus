package Laivanupotus.Kayttoliittyma.ValintaPalkki;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;

/**
 * Tämä luokka (yhdessä kolmen siihen kuuluvan kuuntelijan kanssa) toteuttaa käyttöliittymässä olevan valintapalkin toiminnan.
 */

public class ValintaPalkki extends JMenuBar {
    private OhjeidenKuuntelija ohjeidenKuuntelija;
    
    public ValintaPalkki(Kayttoliittyma kayttoliittyma) {
        JMenu tiedosto = new JMenu("Tiedosto");
        luoTiedostonAliKomponentit(tiedosto, kayttoliittyma);
        this.add(tiedosto);
        
        JMenu ohjeet = new JMenu("Ohjeet");
        
        this.ohjeidenKuuntelija = new OhjeidenKuuntelija(ohjeet);
        ohjeet.addMenuListener(ohjeidenKuuntelija);
        this.add(ohjeet);
    }
    
    private void luoTiedostonAliKomponentit(JMenu tiedosto, Kayttoliittyma kayttoliittyma) {
        JMenuItem aloitaUusi = new JMenuItem("Aloita uusi peli");
        aloitaUusi.addActionListener(new AloitaUusiPeliKuuntelija(kayttoliittyma));
        
        JMenuItem lopeta = new JMenuItem("Lopeta");
        lopeta.addActionListener(new LopetaPeliKuuntelija(kayttoliittyma));
        
        tiedosto.add(aloitaUusi);
        tiedosto.add(lopeta);
    }
    
    public void tuhoaOhjeidenKuuntelija() {
        ohjeidenKuuntelija.tuhoa();
    }
}
