package oakDonuts;

import javax.swing.*;

public class ActualMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // 1. Create tables if they don't exist
            DatabaseSetup.main(null);

            // 2. Seed the menu if empty
            MenuItemsSeeder.seedMenu();

            // 3. Launch the GUI
            new OakDonutsUI().setVisible(true);
        });
    }
}