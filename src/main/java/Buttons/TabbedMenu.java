package Buttons;

import Connection.Connessione;
import Indirizzo.Indirizzo;
import Pizza.Pizza;
import SceltaOrari.DisponibilitaOrari;

import javax.swing.*;

import java.io.Serial;

import java.sql.SQLException;

/*
    TabbedMenu crea e gestisce Il frame principale a cui attacca un JTabbedPane Menubar,
    a cui poi sono collegate tre tab tra cui ci si può muovere usando Prev/Nextbutton, con uso di relativi index
    delle tab oppure usare Show_OrderButton e Fine_SaveOrder in rispettivamente DisponibilitaOrari
    e Pizza_Tab.
    Inoltre qui è presente il main per avviare l' applicazione.
    Da controllare come rendere disponibile come pacchetto scaricabile da github o APK.
 */


public class TabbedMenu extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private static JTabbedPane MenuBar;

    private static JPanel tab1;
    private static JPanel tab2;
    public static JPanel tab3;

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
        tab1 = new DisponibilitaOrari().DisponibilitaOrari();
        MenuBar.addTab("Disponibilita Orari",null, tab1,
                "Scegliere l' orario desiderato");
        tab2 = new Indirizzo().Indirizzo();
        MenuBar.addTab("Indirizzo",null, tab2,
                "Cerca o crea l' indirizzo con telefono");
        tab3 = new Pizza().Pizza();
        MenuBar.addTab("Pizza",null, tab3,"Scegli o Crea la pizza " +
                "aggiungendo ingredienti ");


        frame.add(MenuBar); // Aggiungo TabbedPane direttamente al Frame onde evitare di gestire un altro
                            // Pannello in cui aggiungo il Jtabbed Pane perchè tanto le chiamate avvengono
                            // per ogni tab del JTabbedMenu
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000,750);
        frame.setResizable(true);

    }

    public static void Nextbutton(int num){
        MenuBar.setSelectedIndex(num);

    }
    public static void Prevbutton(int num){
        MenuBar.setSelectedIndex(num);

    }
    public static void Finebutton(){
        MenuBar.setSelectedIndex(0);

    }

    public static void Carica_Ordine(){


    }
}
