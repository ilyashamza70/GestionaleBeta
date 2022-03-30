package SceltaOrari;

import Connection.DBManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrariButton extends JPanel implements ActionListener {
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

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
    LocalDateTime now = LocalDateTime.now();
    int time = Integer.parseInt(dtf.format(now));

    List<JButton> buttonList = new ArrayList<JButton>();
    JButton toRed = new JButton();
    JButton toOrange = new JButton();

    public OrariButton() {

        String[] buttonText = {"18:00", "18:15", "18:30", "18:45",
                "19:00", "19:15", "19:30", "19:45",
                "20:00", "20:15", "20:30", "20:45",
                "21:00", "21:15", "21:30", "21:45",
                "22:00", "22:15", "22:30", "22:45"};

        for (int i = 0; i < 20; i++) {
            JButton button = new JButton(buttonText[i]);
            button.addActionListener(this);

            switch (OrderCounter(buttonText[i])) {
                case 0:
                    break;
                case 1,2,3:
                    button.setBackground(Color.orange);
                    break;
                default:
                    button.setBackground(Color.red);
                    break;
            }
            buttonList.add(button);
        }
    }

    public JPanel PanelCreator() {
        JPanel porari = new JPanel(new GridLayout(1, 5));

        if (time <= 18) {
            JPanel p18 = new JPanel(new GridLayout(4, 1));
            for (int i = 0; i < 4; i++) { p18.add(buttonList.get(i)); }
            porari.add(p18);
        }
        if (time <= 19) {
            JPanel p19 = new JPanel(new GridLayout(4, 1));
            for (int i = 4; i < 8; i++) { p19.add(buttonList.get(i)); }
            porari.add(p19);
        }
        if (time <= 20) {
            JPanel p20 = new JPanel(new GridLayout(4, 1));
            for (int i = 8; i <12; i++) { p20.add(buttonList.get(i)); }
            porari.add(p20);
        }
        if (time <= 21) {
            JPanel p21 = new JPanel(new GridLayout(4, 1));
            for (int i = 12; i < 16; i++) { p21.add(buttonList.get(i)); }
            porari.add(p21);
        }
        if (time <= 22) {
            JPanel p22 = new JPanel(new GridLayout(4, 1));
            for (int i = 16; i < 20; i++) { p22.add(buttonList.get(i)); }
            porari.add(p22);
        }
        return porari;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int nordini = 0;
        String orario = null;

        for(int i = 0; i < 20; i++) {
            if(e.getSource() == buttonList.get(i)) {
                orario = buttonList.get(i).getText();
                nordini = OrderCounter(orario);
                break;
            }
        }
        if (nordini > 0) { ShowOrder(nordini, orario); }
        else SaveOrder(orario);
    }

    public int OrderCounter(String orario) {
        int nordini = 0;

        try {
            //Meglio selezionare il numero ordini -> COUNT(ID)
            // oppure il numero di pizze -> COUNT(*)    ???
            ResultSet rs = statement.executeQuery("SELECT COUNT(*) AS nordini FROM ordine WHERE orario = '"
                    + orario.trim() + "'");
            nordini = rs.getInt("nordini");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return nordini;
    }

    public void ShowOrder(int nordini, String orario) {
        List<JPanel> panelList = new ArrayList<JPanel>();
        try {
            ResultSet rs = statement.executeQuery("SELECT ingredienti FROM ordine o JOIN pizza p" +
                    " ON (o.codpizza = p.codpizza) WHERE orario = '" + orario.trim() + "'");

            while (rs.next()) {
                JPanel panel = new JPanel();
                panel.add(new JTextArea(rs.getString("ingredienti")));
                panelList.add(panel);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        JPanel ordiniPanel = new JPanel(new GridLayout(1,nordini));
        for (int i = 0; i < nordini; i++) { ordiniPanel.add(panelList.get(i)); }

        String[] optionsString = {"Prosegui", "Annulla"};
        int optionClicked = JOptionPane.showOptionDialog(null, new JScrollPane(ordiniPanel),
                "Ordini delle " + orario, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, optionsString, optionsString[0]);

        if (optionClicked == JOptionPane.YES_OPTION) { SaveOrder(orario); }
    }

    public void SaveOrder(String orario) {

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
        }
    }
}