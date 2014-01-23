package Ohjaus;
import Sovelluslogiikka.Pelaaja;

public class Peli {
    private Pelaaja kayttaja;
    private Pelaaja tietokone;
    private Lukija lukija;
    
    private int kayttajaanOsuneet;
    private int tietokoneeseenOsuneet;
    
    public Peli(Lukija lukija) {
        this.kayttaja = new Pelaaja();
        this.tietokone = new Pelaaja();
        this.lukija = lukija;
        
        this.kayttajaanOsuneet = 0;
        this.tietokoneeseenOsuneet = 0;
    }
    
    public Pelaaja getKayttaja() {
        return this.kayttaja;
    }
    
    public void asetaKayttajanLaivat() {
        
//            System.out.println("Pysty- vai vaakasuuntaan? (pysty = 0, vaaka = 1)");
//            Suunta suunta = Suunta.ALAS;
//            
//            int syote = Integer.parseInt(lukija.seuraavaRivi());
//            
//            if (syote == 1) {
//                suunta = Suunta.OIKEALLE;
//            }
//            
////            while (true) {
////                String virheilmoitus = "Et syöttänyt nollaa etkä ykköstä!";
////                
////                
////              try {
////                  if (syote == 0 || syote == 1) {
////                      if (syote == 1) {
////                          suunta = Suunta.OIKEALLE;
////                      }     
////                      break;
////                      }
////                      else {
////                          System.out.println(virheilmoitus);
////                      }
////                  }
////              }
////              catch (NumberFormatException e) {
////                    System.out.println(virheilmoitus);
////              }
////            }
//            
//            int Xsij = Integer.parseInt(lukija.seuraavaRivi());
//            int Ysij = Integer.parseInt(lukija.seuraavaRivi());    
//            this.kayttaja.lisaaLaivaPelilaudalle(new Sijainti(Xsij, Ysij), suunta);
//         
//            try {
//             
//            }
//            catch (IllegalArgumentException e) {
//                i--;
//            }
    }
}
