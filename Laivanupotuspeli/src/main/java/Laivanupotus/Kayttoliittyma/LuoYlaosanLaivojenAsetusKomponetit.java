package Laivanupotus.Kayttoliittyma;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LuoYlaosanLaivojenAsetusKomponetit {
    private Kayttoliittyma kayttoliittyma;
    private JTextField kommentti;
    private JTextField sijainti;
    private JTextField suunta;
    private JPanel ylaosa;
    
    public LuoYlaosanLaivojenAsetusKomponetit(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        this.ylaosa = new JPanel(new GridLayout(4,1));
    }
    
    public JPanel paivita(int laivanIndeksi) {
        suunta.setText("");
        sijainti.setText("");
        kommentti.setText("Valitse" + laivanIndeksi + ". laivan sijainti vas. pelilaudalta.");
        return ylaosa;
    }
    
    public JPanel luo(int laivanIndeksi) {
        ylaosa = new JPanel(new GridLayout(4,1));
        
        JPanel ylakentta = ylakentta(laivanIndeksi);
        JPanel keskikentta = keskikentta();
        JPanel alakentta = alakentta();
        JPanel kommenttikentta = kommenttikentta(laivanIndeksi);
        
        ylaosa.add(ylakentta);
        ylaosa.add(keskikentta);
        ylaosa.add(alakentta);
        ylaosa.add(kommenttikentta);
        
        return ylaosa;
    }
    
    private int haeKoko(int laivanIndeksi) {
        int koko = 4;
            
        if (laivanIndeksi > 1 && laivanIndeksi < 4) {
            koko = 3;
        }
        else if (laivanIndeksi == 4) {
            koko = 2;
        }
        return koko;
    }    
    

    private JPanel ylakentta(int laivanIndeksi) {
        JPanel ylakentta = new JPanel(new GridLayout(1,1));

        JLabel laivanKoko = new JLabel(" " + laivanIndeksi + ". laivan koko: " + haeKoko(laivanIndeksi));
        
        ylakentta.add(laivanKoko);
        return ylakentta;
    }
    
    private JPanel keskikentta() {
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
    
    private JPanel alakentta() {
        JPanel alakentta = new JPanel(new GridLayout(1,4));

        JLabel laivanSijainti = new JLabel("Laivan sijainti: ");
        
        sijainti = new JTextField();
        sijainti.setEnabled(false);
        sijainti.addActionListener(null);
        
        JButton luoLaiva = new JButton("LUO LAIVA");
        luoLaiva.addActionListener(new LuoLaivanKuuntelija(sijainti, suunta, kayttoliittyma));
        
        alakentta.add(laivanSijainti);
        alakentta.add(sijainti);
        alakentta.add(new JLabel());
        alakentta.add(luoLaiva);
        
        return alakentta;
    }
    
    private JPanel kommenttikentta(int laivanIndeksi) {
        JPanel kommenttikentta = new JPanel(new GridLayout(1,2));
        
        kommentti = new JTextField("Valitse " + laivanIndeksi + ". laivan sijainti vas. pelilaudalta.");
        kommentti.setEnabled(false);
        kommentti.addActionListener(null);

        kommenttikentta.add(kommentti);
        kommenttikentta.add(new JLabel());
        
        return kommenttikentta;
    }
}