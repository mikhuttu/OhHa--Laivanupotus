package Laivanupotus.Ohjaus;

import Laivanupotus.Sovelluslogiikka.Peli;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelaaja;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Sijainti;
import Laivanupotus.Tyokalut.Lukija;
import Laivanupotus.Tyokalut.Suunta;
import java.util.Random;

/**
 * Käyttöliittymä pyytää tätä luokkaa asettamaan laivat pelin alussa pelaajan ja tietokoneen pelilaudoille.
 */

public class LaivojenLuoja {
    private Lukija lukija;
    private Peli peli;
    
    public LaivojenLuoja(Peli peli) {
        Pelaaja pelaaja = (Pelaaja) peli.getPelaaja();
        this.lukija = pelaaja.getLukija();
        this.peli = peli;
    }
    
    /**
     * Metodi pyytää pelaajaa valitsemaan jokaiselle laivalleen sijainnin (x, y) ja asettaa laivan tähän sijaintiin.
     * Mikäli asettaminen ei onnistu, sama laiva asetetaan uudelleen (catch -haaran alla).
     */
    
    public void asetaPelaajanLaivat() {
        for (int i = 1; i <= 4; i++) {
            
            int koko = haeKoko(i);
            System.out.println(i + ". laivan koko: " + koko + "\n");
            
            Suunta suunta = valitseSuunta(i);
            System.out.println();
            int Xsij = valitseKoordinaatti('X');
            System.out.println();
            int Ysij = valitseKoordinaatti('Y');
            System.out.println();
            
            Sijainti sijainti = new Sijainti(Xsij, Ysij);

            try {
                luoKayttajanLaudalleLaiva(this.peli.getPelaaja(), suunta, sijainti, koko);
            }
            catch (IllegalArgumentException e) {
                System.out.println("Tähän paikkaan ei voi luoda laivaa.\n");
                i--;
            }
            
            PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(this.peli.getPelaaja());
            piirtaja.piirraPelilauta();
        }
    }
    
    /**
     * Metodi sijoittaa tietokoneen laivat pelilaudalle sattumanvaraisesti.
     * Mikäli asettaminen ei onnistu, sama laiva asetetaan uudelleen (catch -haaran alla).
     */
    
    public void asetaTietokoneenLaivat() {
        Random random = new Random();
        
        for (int i = 1; i <= 4; i++) {
            
            int koko = haeKoko(i);
            Suunta suunta = Suunta.ALAS;
            
            if (random.nextInt(2) == 1) {
                suunta = Suunta.OIKEALLE;
            }
            
            int laudanKoko = this.peli.getTietokone().getPelilauta().getKoko();
            
            int Xsij = random.nextInt(laudanKoko - 1);
            int Ysij = random.nextInt(laudanKoko - 1);
            Sijainti sijainti = new Sijainti(Xsij, Ysij);
            
            try {
                luoKayttajanLaudalleLaiva(this.peli.getTietokone(), suunta, sijainti, koko);
            }
            catch (IllegalArgumentException e) {
                i--;
            }
        }
    }
    /**
     * Metodi pyytää pelaajaa valitsemaan asetettavalle laivalle suunnan (joka alas tai oikealle).
     * 
     * @param i
     * @return Palauttaa valitun suunnan.
     */
    
    private Suunta valitseSuunta(int i) {
        
        System.out.println("Valitse " + i + ". laivan suunta.\n0 = Alas\n1 = Oikealle\n");
        Suunta suunta = Suunta.ALAS;
        
        while (true) {
            System.out.print("Suunta: ");
            
            try {
                int s = this.lukija.seuraavaRiviKokonaislukuna();
                if (s == 0 || s == 1) {
                    if (s == 1) {
                        suunta = Suunta.OIKEALLE;
                    }
                    break;
                }
                System.out.println("Valitse suunnaksi 0 (Alas) tai 1 (Oikealle)");     
            }
            
            catch (IllegalArgumentException e) {
                System.out.println("Valitse suunnaksi 0 (Alas) tai 1 (Oikealle)");
            }
        }

        return suunta;
    }
    
    /**
     * Metodi pyytää pelaajaa valitsemaan X tai Y koordinaatin ja palauttaa sen.
     * 
     * @param XtaiY
     * @return 
     */
    
    private int valitseKoordinaatti(char XtaiY) {
        System.out.println("Valitse laivan " + XtaiY + " koordinaatin sijainti.");
        
        KoordinaatinValitsin valitsin = new KoordinaatinValitsin(this.lukija, this.peli.getPelaaja().getPelilauta());
        return valitsin.valitseKoordinaatti(XtaiY);
    }
    
    /**
     * Metodi saa parametrinaan tiedon siitä, kuinka mones laiva pelilaudalle ollaan asettamassa ja "päättää" tämän
     * perusteella asetettavan laivan koon.
     * 
     * @param i "monesko laiva"
     * @return palauttaa ko. laivalle koon.
     */
    
    private int haeKoko(int i) {
        int koko = 4;
            
        if (i == 1 || i == 2) {
            koko = 3;
        }
        else if (i == 3) {
            koko = 2;
        }
        return koko;
    }
    
    /**
     * Metodi asettaa käyttäjän pelilaudalle uuden laivan.
     * Mikäli asettaminen ei onnistu, palauttaa metodi IllegalArgumentException -virheen, joka käsitellään yllä
     * olevissa metodeissa catch -haarassa.
     * 
     * @param kayttaja Pelaaja tai Tietokone
     * @param suunta Laivan suunta (alas tai oikealle)
     * @param Xsij
     * @param Ysij
     * @param koko
     * @throws IllegalArgumentException asettaminen ei onnistunut
     */
    
    private void luoKayttajanLaudalleLaiva(Kayttaja kayttaja, Suunta suunta, Sijainti sijainti, int koko) throws IllegalArgumentException {
        if (kayttaja == null || suunta == null || sijainti == null) {
            throw new IllegalArgumentException();
        }
        
        Laiva laiva = new Laiva(sijainti, suunta, koko);
        Pelilauta pelilauta = kayttaja.getPelilauta();  
        pelilauta.asetaLaiva(laiva);
    }
    
    public void asetaPelaajanLaudalleLaiva(Suunta suunta, Sijainti sijainti) throws IllegalArgumentException {
        Kayttaja kayttaja = this.peli.getPelaaja();
        int koko = haeKoko(kayttaja.getPelilauta().getLaivat().size());
        luoKayttajanLaudalleLaiva(kayttaja, suunta, sijainti, koko);
    }
}