package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import griffith.Board;
import griffith.GameLogic;
import griffith.Multiplayer;

// Represents a test for the Multiplayer class.
class MultiplayerTest {
	// Test for initializing the game
    @Test
    void testGameInitialization() {
        Multiplayer multiplayer = new Multiplayer();
        assertNotNull(multiplayer);
    }
	// Test for the player's move
    @Test
    void testPlayerMove() {
        Multiplayer multiplayer = new Multiplayer();
        Board board = new Board();
        GameLogic gameLogic = new GameLogic();

        // Simulate a valid player move
        int x = 0, y = 1; // Example: Moving a pawn
        board.initializePieces(); // Ensure pieces are initialized
        assertFalse(gameLogic.isMoveValid(board, x, y, y, y, true));
        gameLogic.executeMove(board, x, y, y, y);
        assertTrue(multiplayer.getIsWhiteTurn()); // Turn should switch to the computer
    }
	// Test for the computer's move
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
        assertTrue(multiplayer.getIsWhiteTurn()); // Turn should switch back to the player
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