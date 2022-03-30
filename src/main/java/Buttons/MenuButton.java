/*package Buttons;

import Indirizzo.Indirizzo;
import Pizza.Pizza;
import SceltaOrari.DisponibilitaOrari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class MenuButton extends JTabbedPane implements ActionListener {
    private static final long serialVersionUID = 1L;

    private final JButton DisponibilitaOrariButton;
    private final JButton IndirizzoButton;
    private final JButton PizzaButton;



    String notVisible = null;

    public static void main(String[] args) { SwingUtilities.invokeLater(MenuButton::new); }

    public MenuButton() {
        DisponibilitaOrariButton = new JButton("Disponibilita Orari");
        DisponibilitaOrariButton.addActionListener(this);
        IndirizzoButton = new JButton("Indirizzo");
        IndirizzoButton.addActionListener(this);
        PizzaButton = new JButton("Pizza");
        PizzaButton.addActionListener(this);

    }

    public JMenuBar PanelCreator(String posizione) {
        JMenuBar pmenu = new JMenuBar();
        pmenu.add(DisponibilitaOrariButton);
        pmenu.add(IndirizzoButton);
        pmenu.add(PizzaButton);


        return pmenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == DisponibilitaOrariButton)
        getTabComponentAt(DisponibilitaOrari().setVisible(true));
        if(e.getSource() == IndirizzoButton)
            new Indirizzo().setVisible(true);
        if(e.getSource()==PizzaButton)
            new Pizza().setVisible(true);
        switch (notVisible) {
            case "SceltaOrari.DisponibilitaOrari":
                DisponibilitaOrari.closer(notVisible);
                break;
            case "Indirizzo":
                Indirizzo.closer((notVisible));
                break;
            case "Pizza":
                Pizza.closer(notVisible);
                break;
        }
    }
}*/