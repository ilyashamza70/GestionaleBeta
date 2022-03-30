package Buttons;

import SceltaOrari.DisponibilitaOrari;
import Indirizzo.Indirizzo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrevNextButton extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JButton PrevButton;
    private final JButton NextButton;

    String interfaccia = null;

    public PrevNextButton() {
        PrevButton = new JButton("Indietro");
        PrevButton.addActionListener(this);
        NextButton = new JButton("Avanti");
        NextButton.addActionListener(this);
    }

    public JPanel PanelCreator(String posizione) {
        JPanel pprevnext = new JPanel();
        pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
        pprevnext.add(PrevButton);
        pprevnext.add(Box.createHorizontalGlue());
        pprevnext.add(NextButton);

        if (posizione.equals("SceltaOrari.DisponibilitaOrari")) { PrevButton.setEnabled(false); }
      //  else if (posizione == ultimo pannello) { NextButton.setEnabled(false); }
        // oppure verrà sostituito con un pulsante per stampare la comanda
        interfaccia = posizione;

        return pprevnext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == PrevButton) {
            switch (interfaccia) {
                case "Indirizzo":
                    new DisponibilitaOrari();
                    break;
            }
        }
        if(e.getSource() == NextButton) {
            switch (interfaccia) {
                case "SceltaOrari.DisponibilitaOrari":
                    new Indirizzo();
                    break;
            }
        }
    }
}
