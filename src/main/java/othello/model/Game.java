package othello.model;

import othello.command.MoveCommand;
import othello.events.EventBus;
import othello.events.EventType;
import othello.players.Player;

import java.util.List;

public class Game {

    private final Board board;
    private final Player player1;
    private final Player player2;
    private final EventBus eventBus;

    private Player currentPlayer;
    private boolean isOver;
    private String winner;

    public Game(Board board, Player player1, Player player2, EventBus eventBus) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.eventBus = eventBus;
        this.currentPlayer = player1;
        this.isOver = false;
    }

    public void start() {
        isOver = false;
        winner = null;
        eventBus.postMessage(EventType.GAME_STARTED, "Game started: " + player1.getName() + " vs " + player2.getName());
    }

    public boolean makeMove(Position position) {
        if (isOver) {
            return false;
        }

        MoveCommand command = new MoveCommand(board, position,
                currentPlayer.getPiece(), eventBus);

        if (command.execute()) {
            checkAndSwitchTurn();
            return true;
        }

        return false;
    }

    private void checkAndSwitchTurn() {
        Player nextPlayer = (currentPlayer == player1) ? player2 : player1;
        List<Position> nextMoves = board.getValidMoves(nextPlayer.getPiece());

        if (!nextMoves.isEmpty()) {
            currentPlayer = nextPlayer;
            eventBus.postMessage(EventType.TURN_CHANGED, "Now it's " + currentPlayer.getName() + "'s turn");
            return;
        }
        List<Position> currentMoves = board.getValidMoves(currentPlayer.getPiece());
        if (!currentMoves.isEmpty()) {
            eventBus.postMessage(EventType.PLAYER_SKIP, nextPlayer.getName() + " has no valid moves. Skipping turn.");
            return;
        }
        endGame();
    }

    private void endGame() {
        isOver = true;

        int blackCount = board.countPieces(Piece.BLACK);
        int whiteCount = board.countPieces(Piece.WHITE);

        if (blackCount > whiteCount) {
            winner = player1.getName() + " (Black)";
        } else if (whiteCount > blackCount) {
            winner = player2.getName() + " (White)";
        } else {
            winner = "Tie";
        }

        eventBus.postMessage(EventType.GAME_OVER, "Game over! Winner: " + winner + " (Black: " + blackCount
        + ", White: " + whiteCount + ")");
    }

    public List<Position> getValidMovesForCurrentPlayer() {
        return board.getValidMoves(currentPlayer.getPiece());
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean isGameOver() {
        return isOver;
    }

    public String getWinner() {
        return winner;
    }

    public int getScore(Piece piece) {
        return board.countPieces(piece);
    }

    public void resetGame(){
        board.resetBoard();

        currentPlayer = player1;
        isOver = false;
        winner = null;

        eventBus.postMessage(EventType.GAME_STARTED, "Game reset.");
    }
}
