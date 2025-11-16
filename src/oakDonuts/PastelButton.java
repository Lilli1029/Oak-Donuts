package oakDonuts;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PastelButton extends JButton {
    public PastelButton(String text) {
        super(text);
        setBackground(Color.decode("#FFDDE5"));
        setForeground(Color.decode("#5A5A5A"));
        setFont(new Font("SansSerif", Font.BOLD, 14));
        setBorder(new LineBorder(Color.decode("#FFB6C9"), 2, true));
        setFocusPainted(false);
        setOpaque(true);
        setPreferredSize(new Dimension(110, 36));
    }
}
