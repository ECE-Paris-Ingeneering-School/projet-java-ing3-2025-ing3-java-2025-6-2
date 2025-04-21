package Model;

import java.awt.*;

/// Création du style des zones de textes
class StyledTextField extends TextField
{
    StyledTextField(int columns)
    {
        super(columns);
        setBackground(new Color(250, 250, 250));
        setForeground(new Color(60, 60, 60));
        setFont(new Font("Arial", Font.PLAIN, 14));
    }
}
