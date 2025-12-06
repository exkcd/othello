package othello.ui;

import javax.swing.*;
import java.awt.*;

import othello.factories.PlayerFactory;
import othello.model.Board;
import othello.model.Game;
import othello.events.EventBus;
import othello.players.*;

public class GameConfigUI extends JFrame {

    public GameConfigUI() {
        setTitle("Othello Setup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JLabel title = new JLabel("Choose Your Opponent:");
        title.setFont(new Font("Time New Roman", Font.BOLD, 18));

        JButton humanButton = new JButton("Play against Human");
        humanButton.setPreferredSize(new Dimension(200, 50));

        JButton aiButton = new JButton("Play against AI");
        aiButton.setPreferredSize(new Dimension(200, 50));

        humanButton.addActionListener(e -> startGame(false));
        aiButton.addActionListener(e -> startGame(true));

        add(title);
        add(humanButton);
        add(aiButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startGame(boolean againstAI) {
        dispose();

        Board board = new othello.model.Board();
        PlayerFactory playerFactory = new PlayerFactory();

        HumanPlayer player1 = playerFactory.createHumanPlayer("Player 1", othello.model.Piece.BLACK);

        othello.players.Player player2;
        if (againstAI) {
            player2 = playerFactory.createRandomAIPlayer("Random AI Opponent", othello.model.Piece.WHITE);
        } else {
            player2 = playerFactory.createHumanPlayer("Player 2", othello.model.Piece.WHITE);
        }

        Game game = new Game(board, player1, player2, EventBus.getInstance());

        new OthelloUI(game);
    }
}
