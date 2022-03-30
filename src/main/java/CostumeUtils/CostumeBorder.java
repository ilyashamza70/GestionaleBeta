package CostumeUtils;

import javax.swing.border.AbstractBorder;
import java.awt.*;

public class CostumeBorder extends AbstractBorder {
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        // TODO Auto-generated method stubs
        super.paintBorder(c, g, x, y, width, height);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(new Color(68,184,224));
        g2d.drawRoundRect(x, y, width - 1, height - 1, 20, 20);
    }
}
