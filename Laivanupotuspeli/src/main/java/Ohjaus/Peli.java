package Ohjaus;
import Sovelluslogiikka.Pelaaja;
import Sovelluslogiikka.Sijainti;
import Sovelluslogiikka.Suunta;

public class Peli {
    private Pelaaja kayttaja;
    private Pelaaja tietokone;
    private Lukija lukija;
    
    private int kayttajaanOsuneet;
    private int tietokoneeseenOsuneet;
    
    public Peli() {
        this.kayttaja = new Pelaaja();
        this.tietokone = new Pelaaja();
        this.lukija = new Lukija();
        
        this.kayttajaanOsuneet = 0;
        this.tietokoneeseenOsuneet = 0;
    }
    
    public void asetaKayttajanLaivat() {
        for (int i = 0; i < 4; i++) {
            System.out.println("Pysty- vai vaakasuuntaan? (pysty = 0, vaaka = 1)");
            Suunta suunta = Suunta.ALAS;
            
//            while (true) {
//                String virheilmoitus = "Et syöttänyt nollaa etkä ykköstä!";
//                int syote = Integer.parseInt(lukija.seuraavaRivi());
//                
//                try {
//                    if (syote == 0 || syote == 1) {
//                        if (syote == 1) {
//                            suunta = Suunta.OIKEALLE;
//                        }
//                        break;
//                    }
//                    else {
//                        System.out.println(virheilmoitus);
//                    }
//                }
//                catch (NumberFormatException e) {
//                    System.out.println(virheilmoitus);
//                }
//            }
            
            int Xsij = Integer.parseInt(lukija.seuraavaRivi());
            int Ysij = Integer.parseInt(lukija.seuraavaRivi());    
            this.kayttaja.lisaaLaivaPelilaudalle(new Sijainti(Xsij, Ysij));
            
            try {
                
            }       
            catch (IllegalArgumentException e) {
                i--;
            }
        }
    }
}
