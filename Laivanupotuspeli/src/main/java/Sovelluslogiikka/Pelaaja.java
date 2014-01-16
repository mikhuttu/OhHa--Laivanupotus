package Sovelluslogiikka;

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
    
    public void ammu(Pelilauta vastustajanLauta, Sijainti sijainti) {
    }
}