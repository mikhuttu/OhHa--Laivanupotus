import Sovelluslogiikka.*;

public class Main {
    public static void main(String[] args) {
        Pelilauta lauta = new Pelilauta();
        Ruutu ruutu1 = new Ruutu(new Sijainti(0,9));
        
        lauta.onkoRuutuunAmmuttu(ruutu1);

    }
}