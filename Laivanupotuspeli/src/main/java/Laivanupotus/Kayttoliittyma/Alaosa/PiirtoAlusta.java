package Laivanupotus.Kayttoliittyma.Alaosa;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import Laivanupotus.Sovelluslogiikka.Kayttaja;
import Laivanupotus.Sovelluslogiikka.Pelilauta;
import Laivanupotus.Sovelluslogiikka.Ruutu;
import Laivanupotus.Tyokalut.Sijainti;

public class PiirtoAlusta extends JPanel {
    private Kayttaja kayttaja;
    private final int koko = 6;
    
    public PiirtoAlusta(Kayttaja kayttaja) {
        this.setLayout(new GridLayout(koko, koko));
        this.kayttaja = kayttaja;
        
        super.setBackground(Color.WHITE);
    }
    
//    public PiirtoAlusta() {
//        super.setBackground(Color.GRAY);
//    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        piirra(graphics);
    }
    
    public void piirra(Graphics graphics) {
        Pelilauta pelilauta = kayttaja.getPelilauta();
        
        for (int j = 0; j < koko; j++) {
            for (int i = 0; i < koko; i++) {
                Ruutu ruutu = pelilauta.haeRuutu(new Sijainti(i,j));
                
                char merkki = haeMerkki(ruutu.getSijainti());
                piirra(graphics, palautaGraafinenRuutu(ruutu), merkki);
            }
        }
    }
        
    private void piirra(Graphics graphics, GraafinenRuutu graafRuutu, char merkki) {
        
        if (merkki == '~') {
            graphics.setColor(Color.WHITE);
        }
        else if (merkki == 'O') {
            graphics.setColor(Color.BLACK);
        }
        
        else if (merkki == 'x') {
            graphics.setColor(Color.CYAN);
        }
        else if (merkki == 'X') {
            graphics.setColor(Color.RED);
        }
        else {  // merkki == 'L'
            graphics.setColor(Color.BLUE);
        }
        graphics.fillRect(graafRuutu.getX(), graafRuutu.getY(), graafRuutu.getWidth(), graafRuutu.getHeight());
    }
    
    private char haeMerkki(Sijainti sijainti) {
        PelilaudanPiirtaja piirtaja = new PelilaudanPiirtaja(kayttaja);
        return piirtaja.palautaSeuraavaMerkki(sijainti);
    }
    
    
    private GraafinenRuutu palautaGraafinenRuutu(Ruutu ruutu) {
        Sijainti sijainti = ruutu.getSijainti();
        
        int x = (int) haeXsij(sijainti);
        int y = (int) haeYsij(sijainti);
        
        int leveys = (int) haeLeveys();
        int korkeus = (int) haeKorkeus();
        
        return new GraafinenRuutu(x, y, leveys, korkeus);
    }    
    
    private double haeXsij(Sijainti sijainti) {
        return haeLeveys() * sijainti.getX();
    }
    
    private double haeYsij(Sijainti sijainti) {
        return haeKorkeus() * sijainti.getY();
    }
    
    private double haeLeveys() {
        return 1.0 * this.getWidth() / koko;
    }
    
    private double haeKorkeus () {
        return 1.0 * this.getHeight() / koko;
    }    
}