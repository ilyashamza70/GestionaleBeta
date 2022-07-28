package Pizza;
import Pizza.RiepilogoPizze;
import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Varianti implements ActionListener {
    static JPanel Varianti;
    public static JCheckBox Bianca;
    public static JCheckBox SenzaGlutine;
    public static JCheckBox Rossa;
    public static JCheckBox Calzone;
    public static JCheckBox BenCotta;
    public static JCheckBox Cotto;
    public static JCheckBox Pancetta;
    public static JCheckBox Crudo;
    //public static JComboBox Aggiungi;
    static List<JCheckBox> check_list;

    public JPanel VariantiButton(){
        check_list=new ArrayList<>();
        JPanel Varianti=new JPanel(new GridLayout(4,2,20,20));
        Bianca=new JCheckBox("Bianca ");
        Bianca.addActionListener(this);
        Bianca.setBorderPaintedFlat(true);
        Varianti.add(Bianca,0);
        check_list.add(Bianca);

        SenzaGlutine=new JCheckBox("Senza glutine ");
        SenzaGlutine.addActionListener(this);
        SenzaGlutine.setBorderPaintedFlat(true);
        Varianti.add(SenzaGlutine,1);
        check_list.add(SenzaGlutine);

        Rossa=new JCheckBox("Rossa ");
        Rossa.addActionListener(this);
        Rossa.setBorderPaintedFlat(true);
        Varianti.add(Rossa,2);
        check_list.add(Rossa);

        Calzone=new JCheckBox("Calzone ");
        Calzone.addActionListener(this);
        Calzone.setBorderPaintedFlat(true);
        Varianti.add(Calzone,3);
        check_list.add(Calzone);

        BenCotta=new JCheckBox("Ben cotta ");
        BenCotta.addActionListener(this);
        BenCotta.setBorderPaintedFlat(true);
        Varianti.add(BenCotta,4);
        check_list.add(BenCotta);

        Cotto=new JCheckBox("Prosciutto cotto ");
        Cotto.addActionListener(this);
        Cotto.setBorderPaintedFlat(true);
        Varianti.add(Cotto,5);
        check_list.add(Cotto);


        Crudo=new JCheckBox("Prosciutto Crudo ");
        Crudo.addActionListener(this);
        Crudo.setBorderPaintedFlat(true);
        Crudo.add(BenCotta);
        check_list.add(Crudo);

        Pancetta=new JCheckBox("Pancetta ");
        Pancetta.addActionListener(this);
        Pancetta.setBorderPaintedFlat(true);
        Varianti.add(Pancetta);
        check_list.add(Pancetta);





        //Aggiungi = new JComboBox();
        //Aggiungi.addItem("ciao");
        //Aggiungi.addActionListener(this);
        //Aggiungi.setEnabled(true);
        //Aggiungi collegamento a JList per questo bottone
        //Apertura lista ingredienti con possibilita di selezionare e
        //quindi aggiungere alla pizza anche nelle JTable;

        Varianti.setVisible(true);
        return Varianti;
    }
    public static void uncheck(){
        Bianca.setSelected(false);
        SenzaGlutine.setSelected(false);
        Rossa.setSelected(false);
        Calzone.setSelected(false);
        BenCotta.setSelected(false);
        Crudo.setSelected(false);
        Cotto.setSelected(false);
        Pancetta.setSelected(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String variante;
        for (int i = 0; i < check_list.size(); ++i) {
            if (e.getSource() == check_list.get(i)){
                variante=String.valueOf(check_list.get(i).getText());
                if(check_list.get(i).isSelected()==true )
                    RiepilogoPizze.update_varianti(variante);
                else{
                    RiepilogoPizze.remove_variante(variante);
                }

            }

        }
    }
}
