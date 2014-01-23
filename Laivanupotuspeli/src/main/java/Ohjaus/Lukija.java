package Ohjaus;
import java.util.Scanner;

public class Lukija {
    private Scanner lukija;
    
    public Lukija() {
        this.lukija = new Scanner(System.in);
    }
    
    public String seuraavaRivi() {
        return this.lukija.nextLine();
    }
    
    public int seuraavaRiviKokonaislukuna() throws IllegalArgumentException {
        return Integer.parseInt(seuraavaRivi());
    }
}
