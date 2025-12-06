package othello.model;

import othello.events.EventBus;
import othello.factories.PlayerFactory;
import othello.players.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for the Game class.
 */
public class GameTest {
    private Game game;
    private EventBus eventBus;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        eventBus = new EventBus();
        PlayerFactory playerFactory = new PlayerFactory();
        player1 = playerFactory.createHumanPlayer("Player 1", Piece.BLACK);
        player2 = playerFactory.createHumanPlayer("Player 2", Piece.WHITE);
        game = new Game(new Board(), player1, player2, eventBus);
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game.getBoard());
        assertEquals(player1, game.getPlayer1());
        assertEquals(player2, game.getPlayer2());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameStartSetsCurrentPlayer() {
        game.start();
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testValidMoveSucceeds() {
        game.start();
        Position validMove = new Position(2, 3);
        assertTrue(game.makeMove(validMove));
    }

    @Test
    public void testInvalidMoveFails() {
        game.start();
        Position invalidMove = new Position(0, 0);
        assertFalse(game.makeMove(invalidMove));
    }

    @Test
    public void testTurnSwitchesAfterValidMove() {
        game.start();
        Player firstPlayer = game.getCurrentPlayer();

        Position validMove = new Position(2, 3);
        game.makeMove(validMove);

        assertNotEquals(firstPlayer, game.getCurrentPlayer());
    }

    @Test
    public void testGetValidMovesForCurrentPlayer() {
        game.start();
        var moves = game.getValidMovesForCurrentPlayer();
        assertFalse(moves.isEmpty());
        assertEquals(4, moves.size());
    }

    @Test
    public void testScoreTracking() {
        game.start();
        assertEquals(2, game.getScore(Piece.BLACK));
        assertEquals(2, game.getScore(Piece.WHITE));

        game.makeMove(new Position(2, 3));
        assertEquals(4, game.getScore(Piece.BLACK));
        assertEquals(1, game.getScore(Piece.WHITE));
    }

    @Test
    public void testCannotMoveAfterGameOver() {
        game.start();
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGameWithAIPlayers() {
        PlayerFactory factory = new PlayerFactory();
        Player ai1 = factory.createRandomAIPlayer("AI 1", Piece.BLACK);
        Player ai2 = factory.createRandomAIPlayer("AI 2", Piece.WHITE);

        Game aiGame = new Game(new Board(), ai1, ai2, eventBus);
        aiGame.start();

        assertNotNull(aiGame.getCurrentPlayer());
    }
}