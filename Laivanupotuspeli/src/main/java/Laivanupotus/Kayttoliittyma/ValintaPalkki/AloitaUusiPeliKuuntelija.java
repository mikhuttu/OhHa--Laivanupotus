package Laivanupotus.Kayttoliittyma.ValintaPalkki;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AloitaUusiPeliKuuntelija implements ActionListener {
    private final Kayttoliittyma kayttoliittyma;
    
    public AloitaUusiPeliKuuntelija(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        kayttoliittyma.aloitaUusiPeli();
    }
}
