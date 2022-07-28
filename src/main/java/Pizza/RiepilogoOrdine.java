package Pizza;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RiepilogoOrdine extends JPanel implements ActionListener {

    @Serial
    private static final long serialVersionUID = 1L;
    private static List<JLabel> Riepilogoordine;
    private static List<JTextField> Riepilogoordine_data;
    private static JTextField nome;
    private static JTextField indirizzo;
    private static JTextField numero;
    private static JTextField orario;
    private static JTextField npizze;
    public RiepilogoOrdine(){

        String[][] data ={{"Nome:","Marianna"},{"Indirizzo:","Viale Giolitti 25"},{"Numero:","3334456923"},{"Orario:","CIAO"},{"Tot. Pizze:","6"}};
        //DefaultTableModel  model = new DefaultTableModel(data,s);

        //JTable Riepilogoordine = new JTable(data,s);
        //Riepilogoordine.setRowHeight(47);
        //address.add(BorderLayout.CENTER,Riepilogoordine);
        //Riepilogoordine.setCellSelectionEnabled(false);
        //Riepilogoordine.setGridColor(Color.pink);
        //Definisco prima le label in un arraylist di JLabel chiamato RiepilogoOrdine

        String label[]= {"Nome :","Indirizzo :","Numero :", "Orario :"};
        Riepilogoordine=new ArrayList<JLabel>();
        for(int i=0;i<4;++i){
            JLabel lab =new JLabel(label[i]);

            Riepilogoordine.add(lab);


        }
        //adesso definisco i vari TextField aggiungi se il valore è modificato salvarne i valori
        Riepilogoordine_data = new ArrayList<JTextField>();
        nome = new JTextField();
        nome.addActionListener(this);
        Riepilogoordine_data.add(nome);
        indirizzo=new JTextField();
        indirizzo.addActionListener(this);
        Riepilogoordine_data.add(indirizzo);
        numero=new JTextField();
        numero.addActionListener(this);
        Riepilogoordine_data.add(numero);
        orario=new JTextField();
        orario.addActionListener(this);
        Riepilogoordine_data.add(orario);


        //Riepilogoordine_data.add(txt);


    }
    public static JPanel Table_order(){
        JPanel address =new JPanel(new GridLayout(5,3));

        address.setBorder(BorderFactory.createEmptyBorder(10,30,10,10));
        for(int i=0;i<4;++i){
            address.add(Riepilogoordine.get(i));
            address.add(Riepilogoordine_data.get(i));


        }

        address.setVisible(true);


        return address;
    }

    public static String getNome() {
        return String.valueOf(nome);
    }

    public static String getIndirizzo() {
        return String.valueOf(indirizzo);
    }

    public static String getNumero() {
        return numero.toString();
    }

    public static String getOrario() {
        return String.valueOf(orario);
    }

    //public static String getNpizze() {
    //    return String.valueOf(npizze);
    //}

    public static void setNome(String nom) {

        nome.setText(nom);
    }

    public static void setIndirizzo(String ind) {

        indirizzo.setText(ind);
    }

    public static void setNumero(String num) {

        numero.setText(num);
    }

    public static void setOrario(String ora) {
        orario.setText(ora);
    }



    public void actionPerformed(ActionEvent e) {
        for(int j=0;j< 4;j++){
            if(e.getSource() == Riepilogoordine_data.get(j)){
                //System.out.println("SBAGLIOOPOO");
            };
        }
    }
}
