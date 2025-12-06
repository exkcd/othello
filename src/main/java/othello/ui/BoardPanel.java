package othello.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import othello.model.Game;
import othello.model.Position;
import othello.model.Piece;

public class BoardPanel extends JPanel {

    private final Game game;
    private final int BOARD_SIZE = 8;
    private final int CELL_SIZE = 70; // Size of each square in pixels
    private final JLabel statusLabel;

    public BoardPanel(Game game, JLabel statusLabel) {
        this.game = game;
        this.statusLabel = statusLabel;

        // Set the layout for the 8x8 board
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        setPreferredSize(new Dimension(BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE));

        initializeBoardCells();
    }

    private void initializeBoardCells() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // We use a custom cell to handle drawing the discs
                BoardCell cell = new BoardCell(row, col);

                // Add the Controller logic (MouseListener)
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        handleCellClick(cell.row, cell.col);
                    }
                });
                add(cell);
            }
        }
        repaint();
    }

    private void handleCellClick(int row, int col) {
        if(game.getCurrentPlayer().isHuman() == false){
            return;
        }

        Position position = new Position(row, col);

        boolean success = game.makeMove(position);

        if (success) {
            ((OthelloUI) SwingUtilities.getWindowAncestor(this)).updateStatus();
            repaint();
            if (game.isGameOver()){
                ((OthelloUI) SwingUtilities.getWindowAncestor(this)).updateStatus();
            }
        } else {
            statusLabel.setText("Invalid move at (" + row + ", " + col + "). Try again.");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        othello.model.Board board = game.getBoard();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int x = col * CELL_SIZE;
                int y = row * CELL_SIZE;

                g.setColor(new Color(0, 100, 0));
                g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

                Piece piece = board.getPiece(new Position(row, col));
                if (piece != Piece.EMPTY) {
                    if (piece == Piece.BLACK) {
                        g.setColor(Color.BLACK);
                    } else if (piece == Piece.WHITE) {
                        g.setColor(Color.WHITE);
                    }
                    int discPadding = 5;
                    g.fillOval(x + discPadding, y + discPadding,
                            CELL_SIZE - 2 * discPadding, CELL_SIZE - 2 * discPadding);
                }
            }
        }
    }

    private static class BoardCell extends JPanel {
        public final int row;
        public final int col;

        public BoardCell(int row, int col) {
            this.row = row;
            this.col = col;
            setOpaque(false);
        }
    }
}
