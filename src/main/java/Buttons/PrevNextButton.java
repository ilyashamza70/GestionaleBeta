package Buttons;

import SceltaOrari.DisponibilitaOrari;
import Indirizzo.Indirizzo;
import SceltaOrari.OrariButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParsePosition;

/*
    Creo 2 buttoni Prev e Next che vanno inseriti all' interno di un Jpanel, in PanelCreator, allineati.
    Da inserie nella tab pizza un bottone fine al posto di Next e magari anche nella tab disponibilita orari
    al posto di prev metto bottone carica ordine, in caso si vogliano apportare modifiche all' orario o alle
    pizze.
 */
public class PrevNextButton extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JButton PrevButton;
    private final JButton NextButton;
    private final JButton Fine;
    private final JButton Carica_Ordine;

    String interfaccia = null;

    public PrevNextButton() {
        PrevButton = new JButton("Indietro");
        PrevButton.addActionListener(this);
        NextButton = new JButton("Avanti");
        NextButton.addActionListener(this);
        Fine =new JButton("Fine");//o stampa?
        Fine.addActionListener(this);
        Carica_Ordine = new JButton("Ordini");
        Carica_Ordine.addActionListener(this);

    }

    public JPanel PanelCreator(String posizione) {
        JPanel pprevnext = new JPanel();
        if (posizione.equals("SceltaOrari.DisponibilitaOrari")) {
            pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
            pprevnext.add(Carica_Ordine);
            pprevnext.add(Box.createHorizontalGlue());
            pprevnext.add(NextButton);
        }
        if (posizione.equals("Indirizzo")) {
            pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
            pprevnext.add(PrevButton);
            pprevnext.add(Box.createHorizontalGlue());
            pprevnext.add(NextButton);
        }
        if (posizione.equals("Pizza")) {
            pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
            pprevnext.add(PrevButton);
            pprevnext.add(Box.createHorizontalGlue());
            pprevnext.add(Fine);
        }
/*
        switch (posizione){
            case "SceltaOrari.DisponibilitaOrari" :
                pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
                pprevnext.add(Carica_Ordine);
                pprevnext.add(Box.createHorizontalGlue());
                pprevnext.add(NextButton);
                break;
            case "Indirizzo" :
                pprevnext.removeAll();
                //pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
                //pprevnext.add(PrevButton);

            case "Pizza" :
                pprevnext.removeAll();
                pprevnext.setLayout(new BoxLayout(pprevnext, BoxLayout.LINE_AXIS));
                pprevnext.add(PrevButton);
                pprevnext.add(Box.createHorizontalGlue());
                pprevnext.add(Fine);
                break;

        }

 */


        //  else if (posizione == ultimo pannello) { NextButton.setEnabled(false); }
        // oppure verrà sostituito con un pulsante per stampare la comanda
        interfaccia = posizione;
        pprevnext.setVisible(true);
        return pprevnext;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == PrevButton) {
            switch (interfaccia) {
                case "Indirizzo":
                    TabbedMenu.Prevbutton(0);
                    break;
                case "Pizza" :
                    TabbedMenu.Prevbutton(1);
                    break;
            }
        }
        if(e.getSource() == NextButton) {
            switch (interfaccia) {
                case "SceltaOrari.DisponibilitaOrari":
                    TabbedMenu.Nextbutton(1);
                    break;
                case "Indirizzo":
                    TabbedMenu.Nextbutton(2);
                    break;

            }
        }
        if(e.getSource() == Fine){
            //Salvo tutto e mostro tutte le pizze prima di salvare e mandare comando per database durata 2 secondi.

            TabbedMenu.Finebutton();
            //salvare l'ordine e tornare alla pagina disponibilita orari
        }
        if(e.getSource() == Carica_Ordine){
            //Apre tutti gli ordini salvati della giornata;
            OrariButton.ShowOrder(0,null);
        }
    }
}
