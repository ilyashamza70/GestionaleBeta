package Indirizzo;


import javax.swing.*;
import java.awt.*;

public class Indirizzo extends JPanel {

    public static JPanel Indirizzo() {

        JPanel pfinal = new JPanel(new BorderLayout());
        pfinal.add(new RicercaIndirizzo().PanelCreator(), BorderLayout.CENTER);
        pfinal.setVisible(true);

        return pfinal;

    }

    public static JPanel closer(JPanel pfinal) {
        pfinal.setVisible(false);
        return pfinal;
    }


}
