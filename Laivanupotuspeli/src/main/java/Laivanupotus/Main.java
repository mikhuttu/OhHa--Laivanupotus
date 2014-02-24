package Laivanupotus;

import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        aloita();
    }
    
    /**
     * aloita() metodia kutsutaan käyttöliittymästä, kun uusi peli halutaan aloittaa manuaalisesti valintapalkin kautta.
     */
    
    public static void aloita() {
        SwingUtilities.invokeLater(new Kayttoliittyma());
    }
}