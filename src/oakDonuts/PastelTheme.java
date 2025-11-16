package oakDonuts;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PastelTheme {

    public static void apply() {
        UIManager.put("Panel.background", Color.decode("#FFF7FA"));
        UIManager.put("Button.background", Color.decode("#FFDDE5"));
        UIManager.put("Button.foreground", Color.decode("#5A5A5A"));
        UIManager.put("Button.border", new LineBorder(Color.decode("#FFB6C9"), 2, true));

        UIManager.put("TextField.background", Color.decode("#FFFFFF"));
        UIManager.put("TextField.border", new LineBorder(Color.decode("#E2E2E2"), 2, true));

        UIManager.put("Label.foreground", Color.decode("#5A5A5A"));
        UIManager.put("Table.background", Color.decode("#FFFFFF"));
        UIManager.put("Table.selectionBackground", Color.decode("#CDE7FF"));
        UIManager.put("Table.selectionForeground", Color.decode("#5A5A5A"));

        UIManager.put("ScrollPane.border", new LineBorder(Color.decode("#FFDDE5"), 2, true));

        UIManager.put("OptionPane.background", Color.decode("#FFF7FA"));
        UIManager.put("OptionPane.messageForeground", Color.decode("#5A5A5A"));
        UIManager.put("OptionPane.border", new LineBorder(Color.decode("#FFDDE5"), 2, true));
    }
}
