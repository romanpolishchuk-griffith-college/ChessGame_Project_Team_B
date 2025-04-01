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
        assertTrue(gameLogic.isMoveValid(chessBoard, x, y, true));
        gameLogic.executeMove(chessBoard, x, y);
        assertFalse(multiplayer.isWhiteTurn); // Turn should switch to the computer
    }

    @Test
    void testComputerMove() {
        Multiplayer multiplayer = new Multiplayer();
        ChessBoard chessBoard = new ChessBoard();
        GameLogic gameLogic = new GameLogic();

        // Simulate a computer move
        int[] move = gameLogic.getComputerMove(chessBoard);
        assertNotNull(move);
        gameLogic.executeMove(chessBoard, move[0], move[1], move[2], move[3]);
        assertTrue(multiplayer.isWhiteTurn); // Turn should switch back to the player
    }

    @Test
    void testGameOver() {
        Multiplayer multiplayer = new Multiplayer();
        GameLogic gameLogic = new GameLogic();

        // Simulate a game over scenario
        assertTrue(gameLogic.isGameOver());
    }
    
}