package Laivanupotus.Kayttoliittyma.ValintaPalkki;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JTextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * OhjeidenKuuntelija toimii kuuntelijana valintapalkissa olevalle "Ohjeet" JMenu -painikkeelle.
 * Tämän luokan tarkoituksena on luoda uusi JFrame -olio, johon pelin käyttöohjeet tulostetaan, kun nappia painetaan.
 */

public class OhjeidenKuuntelija implements MenuListener {
    private Scanner lukija;
    private JFrame ohjeNaytto;
    private JMenu painike;
    
    public OhjeidenKuuntelija(JMenu painike) {
        luoLukija();
        this.painike = painike;
        ohjeNaytto = new JFrame("Käyttöohjeet");
    }
    
    private void luoLukija() {
        try {
            lukija = new Scanner(new File("src/Käyttöohjeet.txt"), "UTF-8");
        }
        catch (FileNotFoundException e) {}
    }

    @Override
    public void menuSelected(MenuEvent e) {
        
        if (lukija == null) {
            System.out.println("Käyttöohjeiden luku ei onnistunut.");
            return;
        }
        
        if (!ohjeNaytto.isVisible()) {
            ohjeNaytto = new JFrame("Käyttöohjeet");
            ohjeNaytto.setPreferredSize(new Dimension(1040, 220));
            ohjeNaytto.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            
            tulostaOhjeet(ohjeNaytto.getContentPane());
            ohjeNaytto.pack();
            ohjeNaytto.setVisible(true);
        }
    }
    
    private void tulostaOhjeet(Container container) {
        String ohjeet = "";
        
        while (lukija.hasNextLine()) {
            ohjeet += lukija.nextLine() + "\n";
        }
        JTextArea area = new JTextArea(ohjeet);
        
        area.setDisabledTextColor(Color.BLACK);
        area.setEnabled(false);
        
        container.add(area);
        luoLukija();
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        painike.setSelected(false);
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        painike.setSelected(false);
        tuhoa();
    }
    
    public void tuhoa() {
        this.ohjeNaytto.dispose();
    }
}
