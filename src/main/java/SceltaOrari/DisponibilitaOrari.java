package SceltaOrari;

import Buttons.PrevNextButton;
import Connection.Connessione;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static java.awt.BorderLayout.*;

/*
Pagina Disponibilità Orari, in cui sono presenti (in ordine a partire dall'alto):
    - 4 JButton per spostarsi da un menu all'altro
    - 1 JLabel in cui viene spiegato il funzionamento dei pulsanti sottostanti
    - 5 colonne * 4 righe di JButton con i pulsanti dell'orario di consegna scelto dal cliente,
      alla pressione di un pulsante vengono mostrati gli ordini già presi in tale orario
      (il colore del pulsante cambia se sono già presenti ordini)
      Una colonna di orari (ES: quella delle 18) viene rimossa una volta superata l'ora
      (ES: se sono le 19 la colonna delle 18 viene tolta)
 */

public class DisponibilitaOrari extends JPanel{
    Connessione connessione;
    {
        try {
            connessione = new Connessione();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(this,"Errore connessione al database!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            throwables.printStackTrace();
        }
    }

    public static JPanel DisponibilitaOrari(){

        JPanel mainPanel=new JPanel(new BorderLayout(50,50));
        JPanel centernorth = new JPanel(new FlowLayout(FlowLayout.CENTER,50,30));
        centernorth.add(new JLabel ("Scegliere l'orario di consegna:"));
        mainPanel.add(NORTH,centernorth);
        mainPanel.add(CENTER,new OrariButton().PanelCreator());
        mainPanel.add(PAGE_END ,new PrevNextButton().PanelCreator("SceltaOrari.DisponibilitaOrari"));
        mainPanel.setVisible(true);


        return mainPanel;

    }
    /*
    Closer/Saver salva lo stato corrente della tab se viene premuto avanti, ricorda la tab aperta.

     */
    public static JPanel closer(JPanel pfinal) {
        pfinal.setVisible(false);
        return pfinal;
    }


}