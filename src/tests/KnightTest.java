package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import griffith.Board;
import griffith.Knight;
// Represents a test for the Knight class.
public class KnightTest {
	// Test for getting the valid moves for the knight
	@Test
	void getValidMovesTest() {
		// Create a new board
        Board board = new Board();
		// Create a new knight
        Knight knight = new Knight(board);
		// Set the knight to the board
        board.setPiece(3, 3, knight);
		// Get the valid moves for the knight
        String validMoves = knight.getValidMoves();
		// The correct valid moves for the knight
        String correctValidMoves = "1,4 2,5 4,5 5,4 5,2 4,1 2,1 1,2";
		// Assert that the valid moves are correct
        assertEquals(correctValidMoves, validMoves);
		// Create a new board
        Board board2 = new Board();
		// Create a new knight
        Knight knight2 = new Knight(board2);
		// Set the knight to the board
        board2.setPiece(0, 0, knight2);
		// Get the valid moves for the knight
        validMoves = knight2.getValidMoves();
		// The correct valid moves for the knight
        correctValidMoves = "1,2 2,1";
		// Assert that the valid moves are correct
        assertEquals(correctValidMoves, validMoves);
	}
	
	// Test for checking if a move is valid for the knight
	@Test
	void isMoveValidTest() {
		// Create a new board
        Board board = new Board();
		// Create a new knight
        Knight knight = new Knight(board);

        board.setPiece(3, 3, knight);
		// Test valid moves
        assertTrue(knight.isMoveValid(1, 4));
        assertTrue(knight.isMoveValid(2, 5));
        assertTrue(knight.isMoveValid(5, 2));
        assertTrue(knight.isMoveValid(1, 2));
		// Test invalid moves
        assertFalse(knight.isMoveValid(6, 6));
        assertFalse(knight.isMoveValid(0, 0));
        assertFalse(knight.isMoveValid(3, 3));
	}
}
