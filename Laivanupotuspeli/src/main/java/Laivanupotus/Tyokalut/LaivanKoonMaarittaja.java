package Laivanupotus.Tyokalut;

    /**
     * Luokan tarkoituksena on olla yleismetodina, joka voi määrittää asetettavalle laivalle koon.
     */

public class LaivanKoonMaarittaja {
   
     /**
     * Metodi saa parametrinaan tiedon siitä, kuinka mones laiva pelilaudalle ollaan asettamassa ja laskee tämän
     * perusteella asetettavan laivan koon.
     * 
     * @param laivanIndeksi "monesko laiva"
     * @return palauttaa koon
     */
    
    public int maaritaKoko(int laivanIndeksi) {
        int koko = 4;
            
        if (laivanIndeksi == 1 || laivanIndeksi == 2) {
            koko = 3;
        }
        else if (laivanIndeksi == 3) {
            koko = 2;
        }
        return koko;
    }
}
