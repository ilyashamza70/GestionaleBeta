package Pizza;

import Buttons.TabbedMenu;
import Connection.DBManager;
import Indirizzo.RicercaIndirizzo;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOError;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Locale;

import static Indirizzo.RicercaIndirizzo.*;

public class RiepilogoPizze extends JTable implements ActionListener, FocusListener{
    public static int orders;
    public static JTable Riepilogopizze;
    public static JPanel pizza_tab;
    public static JScrollPane pizza_scroll;
    public static JButton delete_pizza;
    public static JButton delete_ingredients;

    public static JPanel buttons;

    public static DefaultTableModel model;
    public RiepilogoPizze (){

        String[] columnNames={"Pizza: ","conteggio","Toppings"};
        //String[][] data ={{"Margherita","1",null},{"Diavola","2","piselli"},{"Prosciutto e funghi","3","funghi"}};
        delete_pizza=new JButton("Delete Pizza");
        delete_pizza.addActionListener(this);
        delete_pizza.setBackground(Color.yellow);

        delete_ingredients=new JButton("Delete Ingredients");
        delete_ingredients.addActionListener(this);
        delete_ingredients.setBackground(Color.yellow);

        buttons=new JPanel();
        model = new DefaultTableModel(null,columnNames);
        Riepilogopizze = new JTable(model);
        Riepilogopizze.getModel().addTableModelListener(this);
        Riepilogopizze.setRowHeight(30);

        //Riepilogopizze.add((Component) rs);
        //Riepilogopizze.setAutoResizeMode(JTable.HEIGHT);


    }
    public static JPanel Table_Pizza(){
        //JPanel pizza_table=new JPanel(new BorderLayout());
        pizza_tab= new JPanel(new BorderLayout());
        pizza_scroll=new JScrollPane(Riepilogopizze);
        Riepilogopizze.setFillsViewportHeight(true);
        pizza_tab.add(pizza_scroll,BorderLayout.CENTER);
        buttons.setLayout(new BoxLayout(buttons,BoxLayout.LINE_AXIS));
        buttons.add(delete_pizza);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(delete_ingredients);

        pizza_tab.add(buttons,BorderLayout.PAGE_END);
        //pizza_table.add(BorderLayout.CENTER,pizza_table_panel);
        return pizza_tab;
    }
    public static void pizza_button_pressed(String nome){
        //inserire qua add row da db con ricerca  e quindi  inzializzazione di JTable con pizze di specific order
        //Ho il nome cerco ID_utente del nome per poi fare un JOIN a ricerca sul database degli ordini a suo
        //nome in quel determinato orario
        //if pizza exists incrementa

        int i=0;
        int npizze = 0;




        while(i < model.getRowCount()){
            if(nome.equals(model.getValueAt(i,0)) && String.valueOf( model.getValueAt(i,2))== " "){
                npizze = (int)model.getValueAt(i,1);
                ++npizze;
                model.setValueAt(npizze,i,1);
                model.fireTableCellUpdated(i,1);
                break;
            }
            ++i;
        }
        if(npizze > 1){
            String ingredienti=new String( " ");
            model.setValueAt( npizze,i,1);
            model.fireTableRowsInserted(1,1);
            model.setRowCount(model.getRowCount());
            return;
        }
        //String ingredienti=new String(" ");
        model.addRow(new Object[]{nome,1," "});
        model.fireTableRowsInserted(1,1);
        model.setRowCount(model.getRowCount());
    }
    public static void SelectedOrder(ResultSet rs) throws SQLException {
        if(rs == null)
            return;
        int i = 0;
        int j=0;
        for(;i < model.getRowCount() ;++j){
            model.removeRow(i);


        }

        model.fireTableRowsDeleted(0,j);


        i=0;
        while (rs.next()) {
            model.addRow(new Object[]{rs.getString("nomepizza"),rs.getString("nvoltep"),rs.getString("ingredienti")});

            ++i;
            //model.setValueAt(rs.getString("nomepizza").toLowerCase(Locale.ROOT),i,0);
            //model.setValueAt(rs.getString("nvoltep").toLowerCase(Locale.ROOT),i,0);
            //model.setValueAt(rs.getString("ingredienti").toLowerCase(Locale.ROOT),i,0);
            //indirizziList.add(rs.getString("indirizzo").toLowerCase(Locale.ROOT));

        }


        model.fireTableRowsInserted(0,i);
        model.fireTableRowsUpdated(0,i);

    }
    public static void update_varianti(String variante){
        if(model.getRowCount() == 0){
            return;
        }
        model.setValueAt(String.valueOf(model.getValueAt(model.getRowCount()-1,2)).concat(variante),model.getRowCount()-1,2);
        //model.fireTableCellUpdated();
        model.fireTableRowsUpdated(model.getRowCount()-1,model.getRowCount()-1 );
    }

    public static void remove_variante(String variante) {
        if(model.getRowCount() == 0){
            return;
        }
        String new_val=new String();

        new_val = String.valueOf(model.getValueAt(model.getRowCount()-1,2)).replace(variante,"");
        model.setValueAt(new_val, model.getRowCount() - 1, 2);
        model.fireTableRowsInserted(model.getRowCount()-1, model.getRowCount()-1 );
        model.fireTableRowsUpdated(model.getRowCount()-1, model.getRowCount()-1 );
        //model.fireTableCellUpdated();
        //new_val.replace(variante," ");

    }
    /*class PizzaTableModel extends AbstractTableModel implements TableModelListener {
        String[] columnNames={"Pizza: ","conteggio","Toppings"};
        String[][] data ={{"Margherita","1",null},{"Diavola","2","piselli"},{"Prosciutto e funghi","3","funghi"}};



        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        public void setData(String[][] data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            super.setValueAt(aValue, rowIndex, columnIndex);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == 0 || columnIndex==1){
                return false;
            }
            return true;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public void tableChanged(TableModelEvent e) {

        }
    }*/
    public static void db_update(){
        //RicercaIndirizzo.SaveProfile(String.valueOf(RiepilogoOrdine.getNumero()),true);
        orders = model.getRowCount();
        Statement statement;
        int id_utente = -1;



        {
            try {
                statement = DBManager.getConnection().createStatement();
                ResultSet rs = statement.executeQuery("SELECT ID_utente as id,telefono as num FROM utente ");
                while (rs.next())
                if(RiepilogoOrdine.getNumero().compareTo(String.valueOf(rs.getString("num")))==0){
                    id_utente = rs.getInt("id");



                }
                int size= model.getRowCount();
                if(id_utente==-1){
                    //error_db();
                    return;
                }
                int i=0;
                while (i < size ) {
                    if(model.getValueAt(i,2)==null){
                        model.setValueAt(" ",i,2);
                    }
                    //orderList.add(rs.getString("indirizzo").toLowerCase(Locale.ROOT));
                    int res=statement.executeUpdate("INSERT INTO ordine (id_utente, orario, nomepizza, nvoltep,ingredienti)" +
                            " VALUES '"+ id_utente + "' '" +
                            RiepilogoOrdine.getOrario().trim() + "' '"+
                            model.getValueAt(i,0).toString() + "' '" +
                            model.getValueAt(i,1) + "' '" +

                            model.getValueAt(i,2).toString() + "'");
                    ++i;
                    if(res == 0){
                        error_db();
                    }


                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private static void error_db() {

        JOptionPane.showMessageDialog(TabbedMenu.tab3, "Errore nel salvataggio dati!",
                "Errore", JOptionPane.ERROR_MESSAGE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== delete_pizza){
            if(Riepilogopizze.getSelectedRow()!=-1){
                model.removeRow(Riepilogopizze.getSelectedRow());
            }
        }
        if(e.getSource()==delete_ingredients){
            if(Riepilogopizze.getSelectedRow()!= -1){
                model.setValueAt("",Riepilogopizze.getSelectedRow(),2);
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}

