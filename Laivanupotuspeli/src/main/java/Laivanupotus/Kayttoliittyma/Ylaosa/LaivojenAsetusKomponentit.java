package Laivanupotus.Kayttoliittyma.Ylaosa;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Laivanupotus.Kayttoliittyma.Kayttoliittyma;
import Laivanupotus.Tyokalut.LaivanKoonMaarittaja;

public class LaivojenAsetusKomponentit extends YlaOsanKomponentit {
    private LuoLaivanKuuntelija kuuntelija;
    private JLabel laivanKoko;
    
    public LaivojenAsetusKomponentit(Kayttoliittyma kayttoliittyma) {
        this.kuuntelija = new LuoLaivanKuuntelija(kayttoliittyma);
        this.setLayout(new GridLayout(4,1));
    }    
    
    public JPanel seuraava(int laivanIndeksi) {
        
        laivanKoko.setText(" " + laivanIndeksi + ". laivan koko: " + haeKoko(laivanIndeksi));
        kuuntelija.getSuuntaKentta().setText("");
        kuuntelija.getSijaintiKentta().setText("");
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

        laivanKoko = new JLabel(" " + laivanIndeksi + ". laivan koko: " + haeKoko(laivanIndeksi));
        
        ylakentta.add(laivanKoko);
        return ylakentta;
    }
    
    private JPanel keskikentta() {
        JPanel keskikentta = new JPanel(new GridLayout(1,4));
        
        JLabel laivanSuunta = new JLabel("Laivan suunta: ");
        
        JTextField suunta = new JTextField();
        suunta.setEnabled(false);
        this.kuuntelija.tuoSuuntaKentta(suunta);
        
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
        
        JTextField sijainti = new JTextField();
        sijainti.setEnabled(true);              // testauksen takia true

        kuuntelija.tuoSijaintiKentta(sijainti);
        
        JButton luoLaiva = new JButton("LUO LAIVA");
        luoLaiva.addActionListener(kuuntelija);
        
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
}