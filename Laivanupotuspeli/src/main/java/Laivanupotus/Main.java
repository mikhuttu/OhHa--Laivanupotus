package Laivanupotus;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }
}