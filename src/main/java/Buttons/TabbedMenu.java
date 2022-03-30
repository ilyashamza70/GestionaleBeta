package Buttons;

import Connection.Connessione;
import Indirizzo.Indirizzo;
import Pizza.Pizza;
import SceltaOrari.DisponibilitaOrari;

import javax.swing.*;
import java.io.Serial;
import java.sql.SQLException;

public class TabbedMenu extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private static JTabbedPane MenuBar;
    private static JPanel Panel;
    private static JPanel tab1;
    private static JPanel tab2;
    private static JPanel tab3;

    public static void main (String[] Args) {
        TabbedMenu tm=new TabbedMenu();
        Connessione connessione;
        {
            try {
                connessione = new Connessione();
            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(tm,"Errore connessione al database!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                throwables.printStackTrace();
            }
        }

    }
    public TabbedMenu(){
        JFrame frame = new JFrame("GestionalePizzeria interface");




        MenuBar = new JTabbedPane();
        MenuBar.addTab("Disponibilita Orari",null, new DisponibilitaOrari().DisponibilitaOrari(),
                "Scegliere l' orario desiderato");
        MenuBar.addTab("Indirizzo",null, new Indirizzo().Indirizzo(),
                "Cerca o crea l' indirizzo con telefono");
        MenuBar.addTab("Pizza",null, new Pizza().Pizza(),"Scegli o Crea la pizza " +
                "aggiungendo ingredienti ");


        frame.add(MenuBar); // Aggiungo TabbedPane direttamente al Frame onde evitare di gestire un altro
                            // Pannello in cui aggiungo il Jtabbed Pane perchè tanto le chiamate avvengono
                            // per ogni tab del JTabbedMenu
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(800,650);
        frame.setResizable(true);


    }



}
