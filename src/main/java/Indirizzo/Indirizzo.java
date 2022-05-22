package Indirizzo;


import Buttons.PrevNextButton;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.PAGE_END;

public class Indirizzo extends JPanel {

    public static JPanel Indirizzo() {

        JPanel pfinal = new JPanel(new BorderLayout());
        pfinal.add(new RicercaIndirizzo().PanelCreator(), BorderLayout.CENTER);
        pfinal.add(PAGE_END ,new PrevNextButton().PanelCreator("Indirizzo"));
        pfinal.setVisible(true);

        return pfinal;

    }

    public static JPanel closer(JPanel pfinal) {
        pfinal.setVisible(false);
        return pfinal;
    }


}
