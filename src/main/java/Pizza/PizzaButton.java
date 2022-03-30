
package Pizza;

import Connection.DBManager;

import javax.swing.*;
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



    private List<JButton> pizzaButtonlist ;
    JButton toGreen =new JButton();

    public JPanel PanelCreator() {
        JPanel pizzecommon = new JPanel(new GridLayout(2, 2,80,170));

        pizzecommon.add(RiepilogoPizze(null));                   // (0,0) disposizione JTable in GridLayout
                            // Aggiunto JTable al Pannello principale

        pizzecommon.add(PizzaButton());                      //(0,1) disposzioe JPanel con bottoni per pizze prededefined
                                                             // Aggiunta pannello con bottoni al pannello principale

        pizzecommon.add(RiepilogoOrdine(null));                  // (1,0) Aggiunta Jtable con dati utente e orario e num pizze.
                                                             // Aggoiunto al pannello principale

        pizzecommon.add(Varianti());                         // (1,1) Aggiunto ultimo pannello con chekbox o ...
                                                             // Aggiunto al pannello principale

        pizzecommon.setVisible(true);
        return pizzecommon;
    }
    public JTable RiepilogoPizze(ResultSet rs){
        JTable Riepilogopizze=new JTable();
        if(rs==null){
        String nomepizza[]={"Pizza: ","conteggio","Ingredienti"};
        String data[][]={{"Margherita","1",null},{"Diavola","2","piselli"},{"Prosciutto e funghi","3","funghi"}};
        Riepilogopizze=new JTable(data,nomepizza);
        Riepilogopizze.setVisible(true);
            return Riepilogopizze;
        }
        Riepilogopizze.add((Component) rs);
        return Riepilogopizze;

    }
    public JTable RiepilogoOrdine(ResultSet rs){
        String s[]={"Nome","indirizzo","Orario","Telefono","tot pizze:"};
        String data[]={"Marianna","Viale Giolitti 25","3334456923","18:30","6"};
        JTable Riepilogoordine=new JTable();
        for(int i=0;i<4;++i){
            Riepilogoordine.add(new JTextArea(s[i]));
            Riepilogoordine.add(new JTextArea(data[i]));
        }
        Riepilogoordine.setVisible(true);
        Riepilogoordine.setGridColor(Color.green);
        return Riepilogoordine;
    }
    public JPanel Varianti(){
        JPanel Varianti=new JPanel(new GridLayout(3,3,20,20));
        JCheckBox Bianca=new JCheckBox("Bianca");
        Bianca.addActionListener(this);
        Bianca.setBorderPaintedFlat(true);
        Varianti.add(Bianca,0);

        JCheckBox SenzaGlutine=new JCheckBox();
        SenzaGlutine.addActionListener(this);
        SenzaGlutine.setBorderPaintedFlat(true);
        Varianti.add(SenzaGlutine,1);

        JCheckBox Rossa=new JCheckBox("Rossa");
        Rossa.addActionListener(this);
        Rossa.setBorderPaintedFlat(true);
        Varianti.add(Rossa,2);

        JCheckBox Calzone=new JCheckBox("Calzone");
        Calzone.addActionListener(this);
        Calzone.setBorderPaintedFlat(true);
        Varianti.add(Calzone,3);

        JCheckBox BenCotta=new JCheckBox("Ben cotta");
        BenCotta.addActionListener(this);
        BenCotta.setBorderPaintedFlat(true);
        Varianti.add(BenCotta,4);

        JButton AggiungiIngredienti=new JButton("+");
        AggiungiIngredienti.addActionListener(this::actionPerformed);
        AggiungiIngredienti.setBorderPainted(true);
        //Aggiungi collegamento a JList per questo bottone
        //Apertura lista ingredienti con possibilita di selezionare e
        //quindi aggiungere alla pizza anche nelle JTable;
        Varianti.add(AggiungiIngredienti,7 & 8);
        Varianti.setVisible(true);
        return Varianti;
    }
    public JPanel PizzaButton() {

        String[] buttonText = {"Margherita", "4 Stagioni", "4 Formaggi", "Prosciutto e funghi"
                , "Capricciosa", "Diavola", "Tonno e cipolla", "Bufala", "Vegetariana", "Salsiccia e friarelli","Regina Mrgherita","Acciughe"};
        pizzaButtonlist = new ArrayList<JButton>();
        for (int i = 0; i < 12; i++) {
            setBackground(Color.blue);
            JButton button = new JButton(buttonText[i]);
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
        for(int i = 0; i < 12; i++) {
            if(e.getSource() == pizzaButtonlist.get(i)) {
                nomep = String.valueOf(pizzaButtonlist.get(i));
                npizze = PizzaCounter(nomep);
                // Inserisci qua insert into update database di pizza con pressione di bottone.
                //devi prima configurare per avergli fatto salvare l' utente.

                break;
            }
        }
        //Jqquery insert update

        /*
        String[] optionsString = {"Prosegui", "Annulla"};
        int optionClicked = JOptionPane.showOptionDialog(null, new JScrollPane(ordiniPanel),
                "Ordini delle " + orario, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[0]);

        if (optionClicked == JOptionPane.YES_OPTION) { SaveOrder(orario); }
           */


        SavePizza(nomep,npizze);

    }

    public int PizzaCounter(String nomep) {
        int npizze = 0;

        try{// Controllo se nel database cè
            // gia una pizza o no e lo setto a nvolte
            // quindi nvolte si riferisce al numero di pizze dello stesso tipo SQL statement
            // Da aggiungere numero pizze totali da visualizzare su JTable.
            ResultSet rs = statement.executeQuery("SELECT nomepizza,nvolte,ingredienti  FROM ordine " +
                    "where nomepizza == valueof(String.valueof((nomep))  "); // AND ORARIO.sa = qulcosa
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

