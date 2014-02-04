package Laivanupotus.Sovelluslogiikka;

import Laivanupotus.Tyokalut.Suunta;
import java.util.ArrayList;
import java.util.List;

/**
 * Pelilaudalla on tällä hetkellä suuri määrä eri toiminnallisuuksia, sillä se on käsitteenä aika laaja.
 * 
 * Sekä Pelaajalla että Tietokoneella on oma pelilautansa, joka pitää kirjaa siitä, missä pelilaudalla olevat
 * laivat ovat. Lisäksi pelilauta tietää sillä olevat ruudut. (Eli ts. 2 vastuuta)
 */

public class Pelilauta {
    private int koko;
    private List<Laiva> laivat;
    private List<Ruutu> ruudut;
    
    public Pelilauta() {
        this.koko = 6;
        this.laivat = new ArrayList<>();
        this.ruudut = new ArrayList<>();
        asetaRuudutPelilaudalle();
    }
    
    /**
     * Ruudut asetetaan pelilaudalle vaakasuuntaan, joten niihin on helppo jatkossa viitata suoraan kun tietyn ruudun
     * indeksi on tiedossa. Mm. kohdassa (2,3) on 3*6 + 2 = 20. ruutu.
     */
    
    private void asetaRuudutPelilaudalle() {
        for (int y = 0; y < koko; y++) {
            for (int x = 0; x < koko; x++) {
                Ruutu ruutu = new Ruutu(new Sijainti(x,y));
                this.ruudut.add(ruutu);
            }
        }
    }
    
    public int getKoko() {
        return this.koko;
    }
    
    public List<Ruutu> getRuudut() {
        return this.ruudut;
    }
    
    public List<Laiva> getLaivat() {
        return this.laivat;
    }
    
    /**
     * asetaLaiva on "yleismetodi", joka lisää pelilaudalle laivan mikäli se voidaan pelilaudalle asettaa.
     * 
     * @param laiva
     * @throws IllegalArgumentException 
     */
    
    public void asetaLaiva(Laiva laiva) throws IllegalArgumentException {
        if (voidaankoAsettaa(laiva)) {
            lisaaLaiva(laiva);
        }
        else {
            throw new IllegalArgumentException ();
        } 
    }
    
    private void lisaaLaiva(Laiva laiva) {
        this.laivat.add(laiva);
    }    
    
    /**
     * Mikäli laiva voidaan asettaa pelilaudalle, palauttaa true.
     * @param laiva
     * @return 
     */
    
    private boolean voidaankoAsettaa(Laiva laiva) {
        if (!laivatMenevatPaallekain(laiva) && !laivaMeneeRuudukonYli(laiva)) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kysyy jokaiselta pelilaudalla jo olevalta laivalta erikseen, meneekö se
     * päällekäin asetettavan laivan kanssa. Mikäli jokin menee, palauttaa true.
     * @param verrattava
     * @return 
     */
    
    private boolean laivatMenevatPaallekain(Laiva verrattava) {
        for (Laiva laiva : this.laivat) {
            if (laiva.menevatkoPaallekain(verrattava)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Mikäli asetettavan laivan "häntä" olisi pelilaudan ulkopuolella, palautetaan true.
     * Muuten palautetaan false, ja laiva voidaan tältä osin pelilaudalle lisätä.
     * @param laiva
     * @return 
     */
    private boolean laivaMeneeRuudukonYli(Laiva laiva) {
        int laivanKoko = laiva.getKoko();
        Sijainti sijainti = new Sijainti(laiva.getSijainti().getX(), laiva.getSijainti().getY());
        
        if (laiva.getSuunta() == Suunta.ALAS) {
            sijainti.kasvataY(laivanKoko);
        }
        else {
            sijainti.kasvataX(laivanKoko);
        }

        if (Math.max(sijainti.getX(), sijainti.getY()) > this.koko) {
            return true;
        }

        return false;  
    }
    
    /**
     * Metodi palauttaa true mikäli johonkin pelilaudalla olevaan laivaan osuttiin.
     * @param sijainti
     * @return 
     */
    
    public boolean osuikoLaivaan(Sijainti sijainti) {
        for (Laiva laiva : this.laivat) {
            if (laiva.osuiko(sijainti)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Metodi kysyy, onko Sijaintia verrattava vastaavaan ruutuun ammuttu ja palauttaa siitä tiedon.
     * @param verrattava
     * @return
     * @throws IllegalArgumentException 
     */
    
    public boolean onkoRuutuunAmmuttu(Sijainti verrattava) throws IllegalArgumentException {
        Ruutu ruutu = haeRuutu(verrattava);
        return ruutu.onkoAmmuttu();
    }
    
    /**
     * Metodi muuttaa sijaintia vastaavan pelilaudan ruudun ammutuksi.
     * 
     * @param sijainti 
     */
    
    public void muutaAmmutuksi(Sijainti sijainti) {
        haeRuutu(sijainti).muutaAmmutuksi();
    }
    
    /**
     * Metodi palauttaa sijaintia vastaavan ruudun (toimii näin koska ruudut asetettu alussa vaakasuuntaan).
     * Ei pitäisi koskaan palauttaa virheilmoitusta, mutta siihen silti varauduttu.
     * 
     * @param sijainti
     * @return
     * @throws IllegalArgumentException 
     */
    
    public Ruutu haeRuutu(Sijainti sijainti) throws IllegalArgumentException {
        int x = sijainti.getX();
        int y = sijainti.getY();
        
        if (Math.max(x, y) < this.koko) {
            return this.ruudut.get(y * this.koko + x);
        }
        
        throw new IllegalArgumentException("Virhe: Valitsemasi ruutu ei ole koordinaatistossa!");
    }
    
    /**
     * PelilaudanPiirtaja kysyy pelilaudan tältä metodilta, onko ko. laiva tuhottu.
     * Metodi käy läpi laivan osien sijainteja ja mikäli kaikki sijainnit on tuhottu, metodi palauttaa true.
     * 
     * @param laiva
     * @return 
     */
    
    public boolean onkoTuhottu(Laiva laiva) {
        for (int i = 0; i < laiva.getKoko(); i++) {
            Sijainti sijainti = laiva.haeLaivanOsanSijainti(i);
            Ruutu ruutu = haeRuutu(sijainti);
            
            if (!ruutu.onkoAmmuttu()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Metodi palauttaa listana kaikki pelilaudalla olevien laivojen osien sijainnit.
     * @return 
     */
    
    public ArrayList<Sijainti> haeLaivojenOsienSijainnit() {
        ArrayList<Sijainti> laivojenSijainnit = new ArrayList<>();
        
        for (int i = 0; i < this.laivat.size(); i++) {
            Laiva laiva = this.laivat.get(i);
            
            for (int j = 0; j < laiva.getKoko(); j++) {     
                laivojenSijainnit.add(laiva.haeLaivanOsanSijainti(j));
            }
        }
        return laivojenSijainnit;
    }
    
}