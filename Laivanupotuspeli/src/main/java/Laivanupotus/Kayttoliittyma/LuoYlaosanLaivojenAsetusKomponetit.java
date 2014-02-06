package Laivanupotus.Kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Laivanupotus.Sovelluslogiikka.Peli;

public class LuoYlaosanLaivojenAsetusKomponetit {
    private JTextField suunta;
    private Peli peli;
    
    public LuoYlaosanLaivojenAsetusKomponetit(Peli peli) {
        this.peli = peli;
    }
    
    public JPanel luo() {
        int i = 1;
        int k = 4;
        
        JPanel ylaosa = new JPanel(new GridLayout(4,1));
        
        JPanel ylakentta = ylakentta(i, k);
        JPanel keskikentta = keskikentta();
        JPanel alakentta = alakentta();
        JPanel kommenttikentta = kommenttikentta(i);
        
        ylaosa.add(ylakentta);
        ylaosa.add(keskikentta);
        ylaosa.add(alakentta);
        ylaosa.add(kommenttikentta);
        
        return ylaosa;
    }
    
    public JPanel ylakentta(int i, int k) {
        JPanel ylakentta = new JPanel(new GridLayout(1,1));

        JLabel laivanKoko = new JLabel(" " + i + ". laivan koko: " + k);
        
        ylakentta.add(laivanKoko);
        return ylakentta;
    }
    
    public JPanel keskikentta() {
        JPanel keskikentta = new JPanel(new GridLayout(1,4));
        
        JLabel laivanSuunta = new JLabel("Laivan suunta: ");
        
        suunta = new JTextField();
        suunta.setEnabled(false);
        
        
        JButton alasNappi = new JButton("ALAS");
        JButton oikealleNappi = new JButton("OIKEALLE");

        SuuntaNappienKuuntelija suunnanKuuntelija = new SuuntaNappienKuuntelija(suunta, alasNappi, oikealleNappi);
        alasNappi.addActionListener(suunnanKuuntelija);
        oikealleNappi.addActionListener(suunnanKuuntelija);
        
        
        keskikentta.add(laivanSuunta);
        keskikentta.add(suunta);
        keskikentta.add(alasNappi);
        keskikentta.add(oikealleNappi);
        
        return keskikentta;
    }
    
    public JPanel alakentta() {
        JPanel alakentta = new JPanel(new GridLayout(1,4));

        JLabel laivanSijainti = new JLabel("Laivan sijainti: ");
        
        JTextField sijainti = new JTextField();
        sijainti.setEnabled(false);
        sijainti.addActionListener(null);
        
        JButton luoLaiva = new JButton("LUO LAIVA");
        luoLaiva.addActionListener(new LuoLaivanKuuntelija(sijainti, suunta, peli));
        
        alakentta.add(laivanSijainti);
        alakentta.add(sijainti);
        alakentta.add(new JLabel());
        alakentta.add(luoLaiva);
        
        return alakentta;
    }
    
    public JPanel kommenttikentta(int i) {
        JPanel kommenttikentta = new JPanel(new GridLayout(1,2));
        
        JTextField kommentti = new JTextField("Valitse " + i + ". laivan sijainti vas. pelilaudalta.");
        kommentti.setEnabled(false);
        kommentti.addActionListener(null);

        kommenttikentta.add(kommentti);
        kommenttikentta.add(new JLabel());
        
        return kommenttikentta;
    }
}