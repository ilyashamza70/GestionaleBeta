package Indirizzo;

import Connection.DBManager;
import CostumeUtils.CostumeBorder;
import Pizza.RiepilogoOrdine;
import Pizza.RiepilogoPizze;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.Serial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RicercaIndirizzo extends JPanel implements ActionListener, FocusListener, KeyListener {
    List<String> indirizziList = new ArrayList<String>();
    public static Statement statement;
    {
        try {
            statement = DBManager.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT indirizzo FROM utente");

            while (rs.next()) {
                indirizziList.add(rs.getString("indirizzo").toLowerCase(Locale.ROOT));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Serial
    private static final long serialVersionUID = 1L;

    private final JTextField searchbarText;
    private final JTable resultTable;
    /*
    private final JButton searchButton;
    private final JButton temporaryProfileButton;
    private final JButton modifyProfileButton;

     */

    private final JTextArea citofonoTextArea;
    private final JTextField citofonoTextField;
    private final JTextArea indirizzoTextArea;
    private final JTextField indirizzoTextField;
    private final JTextArea telefonoTextArea;
    private final JTextField telefonoTextField;

    String telephoneNumber = "";
    private static final int TEXTFIELD_HEIGHT = 40;
    private static final int TEXTFIELD_WIDTH = 20;

    public RicercaIndirizzo() {

        searchbarText = new JTextField();
        /*
        searchButton = new JButton("Cerca");
        searchButton.addActionListener(this);
       temporaryProfileButton = new JButton("Salva profilo temporaneo");
        temporaryProfileButton.addActionListener(this);
        modifyProfileButton = new JButton("Modifica profilo esistente");
        modifyProfileButton.addActionListener(this);

     */

        citofonoTextArea = new JTextArea("Citofono: ");
        citofonoTextField = new JTextField();
        indirizzoTextArea = new JTextArea("Indirizzo: ");
        indirizzoTextField = new JTextField();
        telefonoTextArea = new JTextArea("Telefono: ");
        telefonoTextField = new JTextField();



        String[] columnNames = {"Citofono", "Indirizzo", "Telefono"};
        Object[][] data = {
                {"","",""}, {"","",""}, {"","",""}, {"","",""}, {"","",""},
                {"","",""}, {"","",""}, {"","",""}, {"","",""}, {"","",""}
        };
        resultTable = new JTable(data, columnNames);
        resultTable.addFocusListener(this);
        //resultTable.getSelectionModel().isSelectionEmpty();   Add selection madel to detect changes and if it is selected
        resultTable.addNotify();
        resultTable.removeEditor();
    }

    public JPanel PanelCreator() {
        JPanel pricerca = new JPanel(new GridLayout(1,3));
        searchbarText.addFocusListener(this);
        searchbarText.addKeyListener(this);
        searchbarText.setText("Aggiungere numero di telefono qui");
        searchbarText.setHorizontalAlignment(JTextField.CENTER);

        Dimension textDimension = new Dimension(TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
        searchbarText.setPreferredSize(textDimension);
        searchbarText.setMinimumSize(textDimension);
        searchbarText.setMaximumSize(textDimension);
        searchbarText.setVisible(true);

        searchbarText.setBorder(BorderFactory.createCompoundBorder(new CostumeBorder(),
                new EmptyBorder(new Insets(10, 20, 10, 20))));

        pricerca.add(searchbarText, BorderLayout.CENTER);

        /*
        searchButton.setSize(100,100);
        pricerca.add(searchButton);

        JPanel pprofile = new JPanel(new GridLayout(2,1));
        temporaryProfileButton.setSize(100,100);
        pprofile.add(temporaryProfileButton);
        pprofile.add(modifyProfileButton);

         */

        JPanel pupper = new JPanel(new BorderLayout());
        pupper.add(pricerca);
        //pupper.add(pprofile);

        /*
        JPanel presult = new JPanel(new GridLayout(2,3));
        presult.add(citofonoTextArea);
        presult.add(indirizzoTextArea);
        presult.add(telefonoTextArea);
        presult.add(citofonoTextField);
        presult.add(indirizzoTextField);
        presult.add(telefonoTextField);

         */

        JScrollPane prisultati = new JScrollPane(resultTable);
        resultTable.setFillsViewportHeight(true);

        JPanel pfinal = new JPanel(new GridLayout(2,1));
        pfinal.add(pupper, BorderLayout.NORTH);
        pfinal.add(prisultati, BorderLayout.SOUTH);
        //pfinal.add(presult, BorderLayout.SOUTH);

        return pfinal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String telephoneNumber = searchbarText.getText().trim();

       /* if (e.getSource() == searchButton) {
            try {
                ResultSet rs = statement.executeQuery("SELECT * FROM utente" +
                        " WHERE telefono = '" + telephoneNumber + "'");

                if(!rs.next()) {
                    JPanel savePanel = new JPanel();
                    savePanel.add(new JTextArea("Creare un nuovo profilo con il numero: "
                            + telephoneNumber + "?"));

                    String[] optionsString = {"Salva", "Annulla"};
                    int optionClicked = JOptionPane.showOptionDialog(null, savePanel,
                            "Profilo non esistente", JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[0]);

                    if (optionClicked == JOptionPane.YES_OPTION) { SaveProfile(telephoneNumber, true); }
                }
                else {
                    citofonoTextField.setText(rs.getString("citofono"));
                    indirizzoTextField.setText(rs.getString("indirizzo"));
                    telefonoTextField.setText(rs.getString("telefono"));
                }
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        else if (e.getSource() == temporaryProfileButton) {
            JPanel savePanel = new JPanel();
            savePanel.add(new JTextArea("Creare un profilo temporaneo?\n" +
                    "I dati non verranno salvati nel database"));

            String[] optionsString = {"Crea profilo temporaneo", "Annulla"};
            int optionClicked = JOptionPane.showOptionDialog(null, savePanel,
                    "Nuovo profilo utente temporaneo", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[1]);

            if (optionClicked == JOptionPane.YES_OPTION) { SaveProfile(telephoneNumber, false); }
        }
        else if (e.getSource() == modifyProfileButton) {
            JPanel savePanel = new JPanel();
            savePanel.add(new JTextArea("Modificare profilo utente esistente?\n" +
                    "I dati esistenti verranno sovrascritti"));

            String[] optionsString = {"Modifica profilo esistente", "Annulla"};
            int optionClicked = JOptionPane.showOptionDialog(null, savePanel,
                    "Nuovo profilo utente", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[1]);

            if (optionClicked == JOptionPane.YES_OPTION) { SaveProfile(telephoneNumber, true); }
        }
        */
    }

    public void SaveProfile(String telephoneNumber, Boolean saveInDB) {
        JTextField telephoneTextField = new JTextField(telephoneNumber);
        JTextField citofonoTextField = new JTextField();
        JTextField indirizzoTextField = new JTextField();

        JPanel savePanel = new JPanel(new GridLayout(3,2));
        savePanel.add(new JTextArea("Telefono: "));
        savePanel.add(telephoneTextField);
        savePanel.add(new JTextArea("Citofono: "));
        savePanel.add(citofonoTextField);
        savePanel.add(new JTextArea("Indirizzo: "));
        savePanel.add(indirizzoTextField);

        String[] optionsString = {"Salva", "Annulla"};
        int optionClicked = JOptionPane.showOptionDialog(null, savePanel,
                "Inserire dati utente", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[1]);

        if (optionClicked == JOptionPane.YES_OPTION) {
            if (saveInDB) {
                try {
                    ResultSet rs = statement.executeQuery("INSERT INTO utente" +
                            " VALUES '" + citofonoTextField.getText().trim() + "' '" +
                            indirizzoTextField.getText().trim() + "' '" +
                            telephoneTextField.getText().trim() + "'");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this,"Errore nel salvataggio dati!",
                            "Errore", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }


            JOptionPane.showMessageDialog(this,"Profilo creato correttamente",
                    "", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == searchbarText &&
                searchbarText.getText().equals("Aggiungere numero di telefono qui")) {
            searchbarText.setText("");
        }
        else if (e.getSource() == resultTable) {
            int row = resultTable.getSelectedRow();

            String citofono = String.valueOf(resultTable.getValueAt(row, 0));
            String indirizzo = String.valueOf(resultTable.getValueAt(row, 1));
            String telefono = String.valueOf(resultTable.getValueAt(row, 2));
            RiepilogoOrdine.setNome(citofono);
            RiepilogoOrdine.setIndirizzo(indirizzo);
            RiepilogoOrdine.setNumero(telefono);

            {
                try {
                    statement = DBManager.getConnection().createStatement();
                    ResultSet rs = statement.executeQuery("SELECT nomepizza AS nomepizza,nvoltep AS nvoltep,ingredienti AS ingredienti " +
                            " FROM ordine JOIN utente u on ordine.ID_utente = u.ID_utente " +
                            "WHERE citofono = '" + citofono.trim() + "'"  );

                    RiepilogoPizze.SelectedOrder(rs);   //manda il resultset al JTable di RiepilogoPizza

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            //RiepilogoPizze.update_riepilogo_pizza(citofono);

        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        telephoneNumber = searchbarText.getText();
        if(e.getKeyChar() != '\b') { telephoneNumber += e.getKeyChar(); }
        if  (!telephoneNumber.equals("")) {
            try {
                ResultSet rs = statement.executeQuery("SELECT * FROM utente" +
                        " WHERE telefono LIKE '" + telephoneNumber + "%'");

                for (int i = 0; i < 10; i++) {
                    if (rs.next()) {
                        resultTable.setValueAt(rs.getString("citofono"), i, 0);
                        resultTable.setValueAt(rs.getString("indirizzo"), i, 1);
                        resultTable.setValueAt(rs.getString("telefono"), i, 2);
                    } else {
                        resultTable.setValueAt("", i, 0);
                        resultTable.setValueAt("", i, 1);
                        resultTable.setValueAt("", i, 2);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}