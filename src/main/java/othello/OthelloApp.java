package othello;

import javax.swing.SwingUtilities;

import othello.ui.GameConfigUI;

public class OthelloApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameConfigUI();
        });
    }
}
