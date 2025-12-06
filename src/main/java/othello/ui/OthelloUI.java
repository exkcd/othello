package othello.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import othello.model.Game;
import othello.model.Piece;
import othello.model.Position;
import othello.players.Player;

public class OthelloUI extends JFrame {

    private final Game game;
    private final BoardPanel boardPanel;
    private final JLabel statusLabel;
    private final JButton resetButton;

    public OthelloUI(Game game) {
        this.game = game;
        this.statusLabel = new JLabel("Othello");
        this.resetButton = new JButton("Reset Game");

        setTitle("Othello");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.boardPanel = new BoardPanel(game, this.statusLabel);
        add(boardPanel, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.add(statusLabel);
        statusPanel.add(resetButton);
        add(statusPanel, BorderLayout.SOUTH);


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.resetGame();

                updateStatus();
                boardPanel.repaint();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        game.start();
        updateStatus();
    }

    public void updateStatus() {
        if (!game.isGameOver()) {
            startNextTurn();
        } else {
            statusLabel.setText("Game Over! Winner: " + game.getWinner() + ". Score: Black (" + game.getScore(Piece.BLACK) + "), White (" + game.getScore(Piece.WHITE) + ")");
        }
    }

    private void startNextTurn() {
        Player currentPlayer = game.getCurrentPlayer();

        statusLabel.setText("Current Player: "+ currentPlayer.getName() + " (" + currentPlayer.getPiece() + ")");

        if (!currentPlayer.isHuman()){
            boardPanel.setEnabled(false);
            new RandomAIPlayerMove(currentPlayer).execute();
        }
        else{
            boardPanel.setEnabled(true);
        }
    }

    private class RandomAIPlayerMove extends SwingWorker<Position, Void> {

        private final Player randomAIPlayer;

        public RandomAIPlayerMove(Player randomAIPlayer) {
            this.randomAIPlayer = randomAIPlayer;
        }

        @Override
        protected Position doInBackground() throws Exception{
            return randomAIPlayer.makeMove(game.getBoard());
        }

        @Override
        protected void done(){
            try{
                Position move = get();
                if (move != null){
                    boolean success = game.makeMove(move);

                    if (success) {
                        boardPanel.repaint();
                    }
                    else{
                        statusLabel.setText("this shouldn't happen lol");
                    }
                }
                else{
                    statusLabel.setText("RandomAIPlayer has no moves.");
                }

                updateStatus();
            } catch (ExecutionException | InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}