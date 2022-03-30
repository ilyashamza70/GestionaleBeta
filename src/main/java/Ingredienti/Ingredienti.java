package Ingredienti;

import Indirizzo.RicercaIndirizzo;

import javax.swing.*;
import java.awt.*;


public class Ingredienti extends JFrame{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Ingredienti::new);
    }
    public Ingredienti(){
        super("Aggiungi Ingredienti");

        JPanel pfinal = new JPanel(new BorderLayout());
        pfinal.add(new RicercaIndirizzo().PanelCreator(), BorderLayout.NORTH);


        /* JFrame methods called */
        setContentPane(pfinal);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 250);
        setVisible(true);
    }

    public static void closer(String notVisible) {
    }
}
