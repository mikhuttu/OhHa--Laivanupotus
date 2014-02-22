package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Sovelluslogiikka.Kayttaja;

public class PelilaudanNappulanKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private Kayttaja kayttaja;
    private String sijainti;
    
    public PelilaudanNappulanKuuntelija(Kayttoliittyma kayttoliittyma, Kayttaja kayttaja, String sijainti) {
        this.kayttoliittyma = kayttoliittyma;
        this.kayttaja = kayttaja;
        this.sijainti = sijainti;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        kayttoliittyma.paivitaSijainti(sijainti);
        
        if (kayttaja.getClass() != Kayttaja.class) {
            kayttoliittyma.ruutuValittu(sijainti);    
        }
    }
}
