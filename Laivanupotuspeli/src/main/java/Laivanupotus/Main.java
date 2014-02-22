package Laivanupotus;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        aloita();
    }
    
    public static void aloita() {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }
}