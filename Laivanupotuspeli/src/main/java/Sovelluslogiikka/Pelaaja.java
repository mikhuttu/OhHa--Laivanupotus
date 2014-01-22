package Sovelluslogiikka;

public class Pelaaja {
    private Pelilauta pelilauta;
    
    public Pelaaja() {
        this.pelilauta = new Pelilauta();
    }
    
    public Pelilauta getPelilauta() {
        return this.pelilauta;
    }
    
    public void lisaaLaivaPelilaudalle(Sijainti sijainti) throws IllegalArgumentException {
        if (this.pelilauta.onkoRuudussaJoLaiva(sijainti)) {
            throw new IllegalArgumentException("Ruudussa on jo laiva.\nValitse toinen ruutu.");
        }
    }
    
    public void ammu(Pelilauta vastustajanLauta, Sijainti sijainti) throws IllegalArgumentException {
        // palautetaan eteenpäin tieto siitä onnistuiko ampuminen. Jos onnistui, tuhoa osa laivaa, soita äänet tms. 
        // Jos laivaan osuu, inkrementoi "osumamääriä".
        
        if(vastustajanLauta.onkoRuutuunAmmuttu(sijainti)) {
            throw new IllegalArgumentException("Ruutuun on jo ammuttu.\nValitse toinen ruutu.");
        }
        vastustajanLauta.muutaAmmutuksi(sijainti);
    }
}