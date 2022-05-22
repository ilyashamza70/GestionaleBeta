package Pizza;

import Buttons.PrevNextButton;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.PAGE_END;

public class Pizza extends JPanel {

    public static JPanel Pizza(){

        JPanel pizza = new JPanel(new BorderLayout());
        pizza.add(new PizzaButton().PanelCreator(), BorderLayout.NORTH);
        pizza.add(PAGE_END ,new PrevNextButton().PanelCreator("Pizza"));
        JPanel pizzab = new JPanel(new BorderLayout());

        return pizza;

    }

    public static JPanel closer(JPanel pizza) {
        pizza.setVisible(false);
        return pizza;
    }
}
