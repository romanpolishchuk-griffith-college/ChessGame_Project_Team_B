package tests;

import griffith.Board;
import griffith.GameLogic;
import griffith.Pawn;
import griffith.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {

    @Test
    public void testSetPlayerColor() {
        // Set player color to white
        GameLogic.setPlayerColor(true);
        assertTrue(GameLogic.isPlayerWhite());
        assertFalse(GameLogic.isComputerWhite());

        // Set player color to black
        GameLogic.setPlayerColor(false);
        assertFalse(GameLogic.isPlayerWhite());
        assertTrue(GameLogic.isComputerWhite());
    }

    @Test
    public void testGetComputerMove() {
        // Create a mock board
        Board board = new Board(false);

        // Place a black pawn on the board
        Pawn blackPawn = new Pawn(board, false);
        board.setPiece(4, 6, blackPawn);

        // Place a white rook on the board
        Rook whiteRook = new Rook(board, true);
        board.setPiece(0, 0, whiteRook);

        // Set player color to white (computer is black)
        GameLogic.setPlayerColor(true);

        // Ensure the computer can make a valid move
        int[] move = GameLogic.getComputerMove(board);
        assertNotNull(move, "Computer should make a valid move");
        assertEquals(4, move[0], "Computer's move should start from the correct row");
        assertEquals(6, move[1], "Computer's move should start from the correct column");
    }

    @Test
    public void testIsMoveValid() {
        // Create a mock board
        Board board = new Board(false);

        // Place a white rook on the board
        Rook whiteRook = new Rook(board, true);
        board.setPiece(0, 0, whiteRook);

        GameLogic gameLogic = new GameLogic();

        // Test valid moves for the rook
        assertTrue(gameLogic.isMoveValid(board, 0, 0, 0, 1, true), "Rook should be able to move from (0,0) to (0,1)");
        assertTrue(gameLogic.isMoveValid(board, 0, 0, 1, 0, true), "Rook should be able to move from (0,0) to (1,0)");

        // Test invalid moves for the rook
        assertFalse(gameLogic.isMoveValid(board, 0, 0, 1, 1, true), "Rook should not be able to move diagonally");
        assertFalse(gameLogic.isMoveValid(board, 0, 0, 2, 2, true), "Rook should not be able to move to an invalid position");
    }
}
