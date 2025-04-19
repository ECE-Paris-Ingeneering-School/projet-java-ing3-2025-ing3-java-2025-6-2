package Model;

import javax.swing.*;
import java.awt.*;

// Custom button style
public class StyledButton extends JButton
{
    public StyledButton(String text, boolean isPrimary) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 14));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(true);
        if (isPrimary) {
            setBackground(new Color(51, 122, 183));
            setForeground(Color.WHITE);
        } else {
            setBackground(new Color(245, 245, 245));
            setForeground(new Color(70, 70, 70));
        }
        setPreferredSize(new Dimension(130, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(darken(getBackground()));
        } else if (getModel().isRollover()) {
            g.setColor(brighten(getBackground()));
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        super.paintComponent(g);
    }

    private Color darken(Color color) {
        return new Color(
                Math.max((int)(color.getRed() * 0.9), 0),
                Math.max((int)(color.getGreen() * 0.9), 0),
                Math.max((int)(color.getBlue() * 0.9), 0)
        );
    }

    private Color brighten(Color color) {
        return new Color(
                Math.min((int)(color.getRed() * 1.1), 255),
                Math.min((int)(color.getGreen() * 1.1), 255),
                Math.min((int)(color.getBlue() * 1.1), 255)
        );
    }
}
