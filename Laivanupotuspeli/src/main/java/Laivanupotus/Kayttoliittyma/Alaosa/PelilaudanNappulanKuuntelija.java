package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;

public class PelilaudanNappulanKuuntelija implements ActionListener {
    private Kayttoliittyma kayttoliittyma;
    private String sijaintiKentta;
    
    public PelilaudanNappulanKuuntelija(Kayttoliittyma kayttoliittyma, String sijaintiKentta) {
        this.kayttoliittyma = kayttoliittyma;
        this.sijaintiKentta = sijaintiKentta;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        kayttoliittyma.paivitaSijainti(sijaintiKentta);
    }
}
