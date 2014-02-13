package Laivanupotus.Sovelluslogiikka;

import java.util.Random;
import Laivanupotus.Tyokalut.LaivanKoonMaarittaja;
import Laivanupotus.Tyokalut.Suunta;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Käyttöliittymä pyytää tätä luokkaa asettamaan laivat pelin alussa pelaajan ja tietokoneen pelilaudoille.
 */

public class LaivojenLuoja {
    private final Peli peli;
    
    public LaivojenLuoja(Peli peli) {
        this.peli = peli;
    }
    
    /**
     * Metodi sijoittaa tietokoneen laivat pelilaudalle sattumanvaraisesti.
     * Mikäli asettaminen ei onnistu, sama laiva asetetaan uudelleen (catch -haaran alla).
     */
    
    public void asetaTietokoneenLaivat() {
        
        LaivanKoonMaarittaja maarittaja = new LaivanKoonMaarittaja();
        Random random = new Random();
        
        for (int i = 1; i <= 4; i++) {
            
            int koko = maarittaja.maaritaKoko(i);
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
    /**
     * Selvittää käyttäjän sekä asetettavan laivan koon ja kutsuu näillä tiedoilla metodia luoKayttajanLaudalleLaiva.
     * 
     * @param suunta
     * @param sijainti
     * @throws IllegalArgumentException 
     */
    
    public void asetaPelaajanLaudalleLaiva(Suunta suunta, Sijainti sijainti) throws IllegalArgumentException {
        Kayttaja kayttaja = this.peli.getPelaaja();
        int koko = new LaivanKoonMaarittaja().maaritaKoko(kayttaja.getPelilauta().getLaivat().size());
        luoKayttajanLaudalleLaiva(kayttaja, suunta, sijainti, koko);
    }
}