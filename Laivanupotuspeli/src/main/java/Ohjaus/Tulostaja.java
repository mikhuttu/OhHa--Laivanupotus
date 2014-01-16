package Ohjaus;

public class Tulostaja {
    
    public void tulostaVirheIlmoitus(int numero) {
        if (numero == 0) {
            System.out.println("Virhe: Valitsemasi ruutu ei ole koordinaatistossa!");
        }
        else if (numero == 1) {
            System.out.println("Ruutuun on jo ammuttu.\nValitse toinen ruutu.");
        }
    }
}