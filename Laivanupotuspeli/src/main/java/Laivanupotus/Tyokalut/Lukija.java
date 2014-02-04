package Laivanupotus.Tyokalut;

import java.util.Scanner;

public class Lukija {
    private Scanner lukija;
    
    public Lukija() {
        this.lukija = new Scanner(System.in);
    }
    
    public Lukija(String teksti) {
        this.lukija = new Scanner(teksti);
    }
    
    public String seuraavaRivi() {
        return this.lukija.nextLine();
    }
    
    public int seuraavaRiviKokonaislukuna() throws IllegalArgumentException {
        return Integer.parseInt(seuraavaRivi());
    }
}
