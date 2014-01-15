import Sovelluslogiikka.*;

public class Main {
    public static void main(String[] args) {
        Sijainti sijainti = new Sijainti (3, 8);
        Suunta suunta = Suunta.OIKEALLE;
        Laiva laiva = new Laiva(sijainti, suunta, 3);
        
        if (laiva.osuiko(new Sijainti (4,8))) {
            System.out.println("Laivan osa on koordinaatissa (4, 8)");
        }
        
        if(!laiva.osuiko(new Sijainti (3, 10))) {
            System.out.println("Laivan osa ei koordinaatissa (3, 10)");
        }
        
    }
}