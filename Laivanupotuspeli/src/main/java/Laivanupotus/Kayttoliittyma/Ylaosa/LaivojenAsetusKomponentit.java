package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Tyokalut.LaivanKoonMaarittaja;

/**
 * LaivanAsetusKomponentit perii JPanel -luokan perivän YlaOsanKomponentit -luokan.
 * Nimensä mukaisesti luokka sisältää Käyttöliittymä -luokan yläosaan asetettavat komponentit, kun pelaaja
 * voi asettaa pelilaudalle laivoja.
 * 
 * "LUO LAIVA" napille asetetaan LuoLaivanKuuntelija, ja suuntanapeille "SuuntaNappienKuuntelija".
 */

public class LaivojenAsetusKomponentit extends YlaOsanKomponentit {
    private LuoLaivanKuuntelija luoLaivanK;
    private SuuntaNappienKuuntelija suuntaNappienK;
    private JLabel laivanKoko;
    
    public LaivojenAsetusKomponentit(Kayttoliittyma kayttoliittyma) {
        this.luoLaivanK = new LuoLaivanKuuntelija(kayttoliittyma);
        this.suuntaNappienK = new SuuntaNappienKuuntelija(kayttoliittyma);
        this.setLayout(new GridLayout(4,1));
    }
    
    public JPanel seuraava(int laivanIndeksi) {
        
        laivanKoko.setText(" " + laivanIndeksi + ". laivan koko: " + haeKoko(laivanIndeksi - 1));
        luoLaivanK.getSuuntaKentta().setText("");
        luoLaivanK.getSijaintiKentta().setText("");
        kommentti.setText("Valitse " + laivanIndeksi + ". laivan sijainti vas. pelilaudalta.");
        return this;
    }
    
    public void luo(int laivanIndeksi) {
        this.setLayout(new GridLayout(4,1));
        
        JPanel ylakentta = ylakentta(laivanIndeksi);
        JPanel keskikentta = keskikentta();
        JPanel alakentta = alakentta();
        JPanel kommenttikentta = kommenttikentta(laivanIndeksi);
        
        this.add(ylakentta);
        this.add(keskikentta);
        this.add(alakentta);
        this.add(kommenttikentta);
    }
    

    private JPanel ylakentta(int laivanIndeksi) {
        JPanel ylakentta = new JPanel(new GridLayout(1,1));

        laivanKoko = new JLabel(" " + laivanIndeksi + ". laivan koko: " + haeKoko(laivanIndeksi - 1));
        
        ylakentta.add(laivanKoko);
        return ylakentta;
    }
    
    private JPanel keskikentta() {
        JPanel keskikentta = new JPanel(new GridLayout(1,4));
        
        JLabel laivanSuunta = new JLabel("Laivan suunta: ");
        
        JTextField suunta = new JTextField();
        suunta.setEnabled(false);
        this.luoLaivanK.tuoSuuntaKentta(suunta);
        
        JButton alasNappi = new JButton("ALAS");
        JButton oikealleNappi = new JButton("OIKEALLE");

        suuntaNappienK.tuoKomponentit(suunta, alasNappi);
        alasNappi.addActionListener(suuntaNappienK);
        oikealleNappi.addActionListener(suuntaNappienK);
        
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

        luoLaivanK.tuoSijaintiKentta(sijainti);
        
        JButton luoLaiva = new JButton("LUO LAIVA");
        luoLaiva.addActionListener(luoLaivanK);
        
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

        kommenttikentta.add(kommentti);
        kommenttikentta.add(new JPanel());
        
        return kommenttikentta;
    }
    
    private int haeKoko(int laivanIndeksi) {
        return new LaivanKoonMaarittaja().maaritaKoko(laivanIndeksi);
    }
    
    public SuuntaNappienKuuntelija getSuuntaNappienKuuntelija() {
        return this.suuntaNappienK;
    }
}