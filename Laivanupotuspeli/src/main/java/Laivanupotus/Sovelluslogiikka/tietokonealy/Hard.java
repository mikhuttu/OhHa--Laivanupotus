package Laivanupotus.Sovelluslogiikka.tietokonealy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import Laivanupotus.Sovelluslogiikka.Laiva;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Tyokalut.Sijainti;

/**
 * Oma osaamisen taso loppui valitettavasti tekoälyn kehittelyssä kesken. Olis ehkä onnistunut jos olis säätänyt tosi
 * paljon, mutta lopullisessa pelissä ei Hard-modea ole.
 */


/**
 * Vaikea vaikeustaso
 * Hard:in ideana on toimia siten että tietokoneella olisi oikeasti jonkin sortin tekoäly, jota se käyttäisi
 * ammuttavan sijainnin määrittämiseen. Pelipuu?
 */


public class Hard implements Alykkyys {
    /**
     * Kuvaa jokaisen sijainnin luvulle 0 (ei ammuttu), 1 (ammuttu, mutta ei osunut), 2 (ammuttu ja osunut laivaan) tai
     * 3 (ammuttu ja ko. laiva tuhottu).
     */
    
    private HashMap <Sijainti, Integer> ammutut;
    private Pelilauta pelilauta;
    
    public Hard() {
        this.ammutut = new HashMap<>();
    }
    
    
    @Override
    public Sijainti maaritaSijainti(Pelilauta vastustajanLauta) {
        this.pelilauta = vastustajanLauta;
        Ruutu[][] ruudut = pelilauta.getRuudut();
        
        paivitaHajautusTaulu(ruudut);
        
        return laskeAmmuttavaSijainti();
    }
    
    
    
    
    private void paivitaHajautusTaulu(Ruutu[][] ruudut) {
        for (Ruutu[] rivi : ruudut) {
            for (Ruutu ruutu : rivi) {
                if (ruutu.onkoAmmuttu()) {
                    paivitaAmmuttuSijainti(ruutu.getSijainti());
                }
                else {
                    ammutut.put(ruutu.getSijainti(), 0);
                }
                
            }
        }
    }
    
    private void paivitaAmmuttuSijainti(Sijainti sijainti) {
        if (pelilauta.osuikoLaivaan(sijainti)) {
            paivitaAmmuttuLaiva(sijainti);
        }
        else {
            ammutut.put(sijainti, 1);
        }
    }
    
    private void paivitaAmmuttuLaiva(Sijainti sijainti) {
        for (Laiva laiva : pelilauta.getLaivat()) {
            if (laiva.osuiko(sijainti)) {
                if (pelilauta.onkoTuhottu(laiva)) {
                    ammutut.put(sijainti, 3);
                }
                else {
                    ammutut.put(sijainti, 2);
                }
            }
        }
    }
    
    
    private Sijainti laskeAmmuttavaSijainti() {
        ArrayList<Sijainti> laivojenSijainnitJohonAmmuttuMuttaEivatTuhoutuneet = new ArrayList<>();
        
        for (Sijainti sijainti : ammutut.keySet()) {
            if (ammutut.get(sijainti) == 2) {
                laivojenSijainnitJohonAmmuttuMuttaEivatTuhoutuneet.add(sijainti);
            }
        }
        
        if (! laivojenSijainnitJohonAmmuttuMuttaEivatTuhoutuneet.isEmpty()) {
            return yritaAmpuaLaivaaJohonJoOsuttuMuttaEiTuhoutunut(laivojenSijainnitJohonAmmuttuMuttaEivatTuhoutuneet);
        }
        
        return new Sijainti(0,0);
    }
    
    private Sijainti yritaAmpuaLaivaaJohonJoOsuttuMuttaEiTuhoutunut(ArrayList<Sijainti> sijainnit) {
        Sijainti eka = sijainnit.get(0);
        Sijainti toka = null;
        
        if (sijainnit.size() > 1) {
            toka = sijainnit.get(1);
        }
        
        else {
            return yritaAmpuaLaivaanJokaJatkuuSijainnistaJohonkinSuuntaan(eka);
        }
        
        if (Math.abs(eka.getX() - toka.getX()) == 1) {
            return yritaAmpuaLaivaanJokaSaattaaJatkuaXSuunnassaSijainnistaKatsoen(eka);
        }
        
        
        return new Sijainti(0,0);
    }
    
    private Sijainti yritaAmpuaLaivaanJokaJatkuuSijainnistaJohonkinSuuntaan(Sijainti sijainti) {
        Sijainti seuraava = new Sijainti(sijainti.getX(), sijainti.getY());
        
        // jos jossain sijainnissa oleva laiva ei ole tuhottu, sen vieressä on pakko olla ruutu johon ei ole ammuttu.
        
        while (true) {
            int i = new Random().nextInt(4);
            if (i == 0) {
                seuraava.kasvataY(-1);
            }
            
            else if (i == 1) {
                seuraava.kasvataY(1);
            }
            else if (i == 2) {
                seuraava.kasvataX(-1);
            }
            else {
                seuraava.kasvataX(1);
            }
            
            if (ammutut.containsKey(seuraava)) {
                if (ammutut.get(seuraava) == 0) {
                    break;
                }
            }
        }
        
        return seuraava;
    }
    
    private Sijainti yritaAmpuaLaivaanJokaSaattaaJatkuaXSuunnassaSijainnistaKatsoen(Sijainti sijainti) {
        Sijainti seuraava = new Sijainti(sijainti.getX(), sijainti.getY());
        
        while (true) {
            int i = new Random().nextInt(4);        // skaalaa i:n satunnaisesti arvoon -2, -1, 1 tai 2.
            if (i < 2) {
                i -= 1;    
            }
            i-= 1;
            
            seuraava.kasvataX(i);
            if (ammutut.containsKey(seuraava)) {
                break;
            }
            
        }
        return seuraava;
    }
}
        
    
            
//    private String palautaPelilaudanKirjainEsitys() {
//        String lauta = "";
//        PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(pelilauta);
//        
//        for (int y = 0; y < pelilauta.getKoko(); y++) {
//            for (int x = 0; x < pelilauta.getKoko(); x++) {
//                Sijainti sijainti = new Sijainti(x,y);
//                char merkki = piirtaja.palautaSeuraavaMerkki(sijainti);
//                
//                lauta += merkki;
//            }
//            
//            lauta += "\n";
//        }
//        
//        return lauta;
//    }
    

