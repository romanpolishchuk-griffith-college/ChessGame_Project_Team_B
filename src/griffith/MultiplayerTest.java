package griffith;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MultiplayerTest {

    @Test
    void testGameInitialization() {
        Multiplayer multiplayer = new Multiplayer();
        assertNotNull(multiplayer);
    }

    @Test
    void testPlayerMove() {
        Multiplayer multiplayer = new Multiplayer();
        Board board = new Board();
        GameLogic gameLogic = new GameLogic();

        // Simulate a valid player move
        int x = 0, y = 1; // Example: Moving a pawn
        board.initializePieces(); // Ensure pieces are initialized
        assertFalse(gameLogic.isMoveValid(board, x, y, true));
        gameLogic.executeMove(board, x, y, y, y);
        assertTrue(multiplayer.isWhiteTurn); // Turn should switch to the computer
    }

    @Test
    void testComputerMove() {
        Multiplayer multiplayer = new Multiplayer();
        Board board = new Board();
        GameLogic gameLogic = new GameLogic();

        // Simulate a computer move
        board.initializePieces(); // Ensure pieces are initialized
        int[] move = gameLogic.getComputerMove(board);
        assertNotNull(move);
        gameLogic.executeMove(board, move[0], move[1], move[2], move[3]);
        assertTrue(multiplayer.isWhiteTurn); // Turn should switch back to the player
    }

    @Test
    void testGameOver() {
        Multiplayer multiplayer = new Multiplayer();
        Board board = new Board();
        GameLogic gameLogic = new GameLogic();

        // Simulate a game over scenario
        board.initializePieces(); // Ensure pieces are initialized
        assertFalse(gameLogic.isGameOver(board)); // Game should not be over initially

    }

}