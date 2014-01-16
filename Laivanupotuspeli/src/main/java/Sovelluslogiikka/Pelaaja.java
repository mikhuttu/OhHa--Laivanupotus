package Sovelluslogiikka;
import Ohjaus.Tulostaja;

public class Pelaaja {
    private Pelilauta pelilauta;
    
    public Pelaaja() {
        this.pelilauta = new Pelilauta();
    }
    
    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }
    
    public void lisaaLaivaPelilaudalle(Laiva laiva) {
        pelilauta.lisaaLaiva(laiva);
    }
    
    public boolean ammu(Pelilauta vastustajanLauta, Ruutu ruutu) {  // palautetaan eteenpäin tieto siitä onnistuiko ampuminen. Jos onnistui, tuhoa osa laivaa, soita äänet tms. Jos laivaan osuu, inkrementoi "osumamääriä".
        if(vastustajanLauta.onkoRuutuunAmmuttu(ruutu)) {
            ruutuunOnJoAmmuttu();
            return false;
        }
        ruutu.muutaAmmutuksi();
        return true;
    }
    
    void ruutuunOnJoAmmuttu() {
        new Tulostaja().tulostaVirheIlmoitus(1);
    }
}