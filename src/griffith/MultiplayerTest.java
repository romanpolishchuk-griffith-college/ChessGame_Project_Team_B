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
        ChessBoard chessBoard = new ChessBoard();
        GameLogic gameLogic = new GameLogic();

        // Simulate a valid player move
        int x = 0, y = 1; // Example: Moving a pawn
        chessBoard.initializePiecePositions(); // Ensure pieces are initialized
        assertTrue(gameLogic.isMoveValid(chessBoard, x, y, true));
        gameLogic.executeMove(chessBoard, x, y, y, y);
        assertFalse(multiplayer.isWhiteTurn); // Turn should switch to the computer
    }

    @Test
    void testComputerMove() {
        Multiplayer multiplayer = new Multiplayer();
        ChessBoard chessBoard = new ChessBoard();
        GameLogic gameLogic = new GameLogic();

        // Simulate a computer move
        chessBoard.initializePiecePositions(); // Ensure pieces are initialized
        int[] move = gameLogic.getComputerMove(chessBoard);
        assertNotNull(move);
        gameLogic.executeMove(chessBoard, move[0], move[1], move[2], move[3]);
        assertTrue(multiplayer.isWhiteTurn); // Turn should switch back to the player
    }

    @Test
    void testGameOver() {
        Multiplayer multiplayer = new Multiplayer();
        ChessBoard chessBoard = new ChessBoard();
        GameLogic gameLogic = new GameLogic();

        // Simulate a game over scenario
        chessBoard.initializePiecePositions(); // Ensure pieces are initialized
        assertFalse(gameLogic.isGameOver(chessBoard)); // Game should not be over initially

        // Simulate a game over condition (e.g., checkmate)
        // Add logic to set up a checkmate scenario if needed
        // For now, assume gameLogic.isGameOver() works correctly
    }

}