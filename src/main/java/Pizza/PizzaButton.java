
package Pizza;

import Connection.DBManager;
import Ingredienti.IngredientiButton;
import SceltaOrari.OrariButton;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PizzaButton extends JPanel implements ActionListener, KeyListener {

   Statement statement;
    {
        try {
            statement = DBManager.getConnection().createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

     @Serial
    private static final long serialVersionUID = 1L;

    //public static DefaultTableModel Riepilogoordine;//Va tolto uso label e tefields



    //public static DefaultTableModel Riepilogopizze;

    private List<JButton> pizzaButtonlist ;
    JButton toGreen =new JButton();

    public JPanel PanelCreator() {
        JPanel pizzecommon = new JPanel(new GridLayout(2, 2,80,80));

        pizzecommon.add(new RiepilogoPizze().Table_Pizza());                   // (0,0) disposizione JTable in GridLayout
                            // Aggiunto JTable al Pannello principale

        pizzecommon.add(PizzaButton());                      //(0,1) disposzioe JPanel con bottoni per pizze prededefined
                                                             // Aggiunta pannello con bottoni al pannello principale

        pizzecommon.add(new RiepilogoOrdine().Table_order());                  // (1,0) Aggiunta Jtable con dati utente e orario .
                                                             // Aggoiunto al pannello principale

        pizzecommon.add(new Varianti().VariantiButton());                         // (1,1) Aggiunto ultimo pannello con chekbox o ...
                                                             // Aggiunto al pannello principale

        pizzecommon.setVisible(true);
        return pizzecommon;
    }



    public JPanel PizzaButton() {

        String[] buttonText = {"Margherita", "4 Stagioni", "4 Formaggi", "Prosciutto e funghi"
                , "Capricciosa", "Diavola", "Tonno e cipolla", "Bufala", "Vegetariana", "Salsiccia e friarelli","Regina Mrgherita","Acciughe"};
        pizzaButtonlist = new ArrayList<JButton>();
        for (int i = 0; i < 12; i++) {

            JButton button = new JButton(buttonText[i]);
            button.setBackground(Color.blue);
            button.addActionListener(this);
            pizzaButtonlist.add(button);
        }
        JPanel buttons =new JPanel(new GridLayout(3,4));   //(0,1) disposzioe JPanel con bottoni
        for(int i=0;i<12;++i){                                       // di pizze principali
            buttons.add(pizzaButtonlist.get(i));                     // Adding the buttons to the ButtonList
                                                                     // with relative name.

        }
        buttons.setVisible(true);
        return buttons;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Pressing PizzaButtons Action
        // if a button from the (0,1) GridLayout is pressed it adds a pizza after
        // checking if there are already others with the same name in that order;
        int npizze = 0;
        String nomep = null;
        String ingredienti;
        for(int i = 0; i < 12; i++) {
            if(e.getSource() == pizzaButtonlist.get(i)) {
                nomep = String.valueOf(pizzaButtonlist.get(i).getText());
                npizze = PizzaCounter(nomep);
                // Inserisci qua insert into update database di pizza con pressione di bottone.
                //devi prima configurare per avergli fatto salvare l' utente.
                //need to uncheck all variant and ingredients
                Varianti.uncheck();
                RiepilogoPizze.model.fireTableRowsInserted(0,1);
                break;
            }
        }
        //controllo prima se esiste gia tale pizza e se esiste aumento il copunter

        RiepilogoPizze.pizza_button_pressed(nomep);
        //Riepilogopizze.addRow(new Object[]{nomep,npizze++,null});

        //Jqquery insert update

        /*
        String[] optionsString = {"Prosegui", "Annulla"};
        int optionClicked = JOptionPane.showOptionDialog(null, new JScrollPane(ordiniPanel),
                "Ordini delle " + orario, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[0]);

        if (optionClicked == JOptionPane.YES_OPTION) { SaveOrder(orario); }
           */


        //SavePizza(nomep,npizze);

    }

    public int PizzaCounter(String nomep) {
        int npizze = 0;

        /*try{// Controllo se nel database cè
            // gia una pizza o no e lo setto a nvolte
            // quindi nvolte si riferisce al numero di pizze dello stesso tipo SQL statement
            // Da aggiungere numero pizze totali da visualizzare su JTable.
            ResultSet rs = statement.executeQuery("SELECT nomepizza,nvolte,ingredienti  FROM ordine " +
                    "where nomepizza == valueof(String.valueof((nomep)))  "); // AND ORARIO.sa = qulcosa
                                                                            // da aggiungere per avere tabella
                                                                            // con tutte le pizze per un utente
                                                                            // ad un determinato orario
            RiepilogoPizze(rs);

            if(rs.getInt("nvoltep")>=1){
                npizze = rs.getInt("nvolte");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        */

        return ++npizze;
    }


    public void SavePizza(String nomep,int npizze) {
        // Inserire qui update per il databas
        //Da nsert into update con nomep e npizze e utnte e orario
        /*
        for(JButton i : buttonList) {
            if(orario.equals(i.getText())) {
                if (i.getBackground() == Color.orange) { toOrange = i; }
                else if (i.getBackground() == Color.red) { toRed = i; }

                i.setBackground(Color.green);
            }
            else if(i.getBackground() == Color.green) {
                if (i == toRed) { i.setBackground(Color.red); }
                else if (i == toOrange) { i.setBackground(Color.orange); }
                else
                    i.setBackground(null);
            }
        }*/
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }


    @Override
    public void keyReleased(KeyEvent e) { }

}

