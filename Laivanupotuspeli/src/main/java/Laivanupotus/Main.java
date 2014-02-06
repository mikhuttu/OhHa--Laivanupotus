package Laivanupotus;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Kayttoliittyma.TekstiKayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        Kayttoliittyma liittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(liittyma);
    }
}