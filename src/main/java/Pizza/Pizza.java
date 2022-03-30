package Pizza;

import javax.swing.*;
import java.awt.*;

public class Pizza extends JPanel {

    public static JPanel Pizza(){

        JPanel pizza = new JPanel(new BorderLayout());
        pizza.add(new PizzaButton().PanelCreator(), BorderLayout.NORTH);
        JPanel pizzab = new JPanel(new BorderLayout());

        return pizza;

    }

    public static JPanel closer(JPanel pizza) {
        pizza.setVisible(false);
        return pizza;
    }
}
