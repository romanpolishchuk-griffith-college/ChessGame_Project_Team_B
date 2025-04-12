package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import griffith.Board;
import griffith.King;
import griffith.Knight;
import griffith.Pawn;

// Represents a test for the King class.
public class KingTest {
	// Test for getting the valid moves for the king
	@Test
	void getValidMovesTest() {
		// Create a new board
        Board board = new Board();
		// Create a new king
        King king = new King(board);
		// Set the king to the board
        board.setPiece(3, 3, king);
		// Get the valid moves for the king
        String validMoves = king.getValidMoves();
		// The correct valid moves for the king
        String correctValidMoves = "3,4 3,2 4,3 2,3 4,4 2,4 4,2 2,2";
		// Assert that the valid moves are correct
        assertEquals(correctValidMoves, validMoves);
        
		// Create a new board
        Board board2 = new Board();
		// Create a new king
        King king2 = new King(board2);
		// Set the king to the board
        board2.setPiece(3, 3, king2);
		// Set a knight to the board
        board2.setPiece(4, 4, new Knight(board2, false));
		// Get the valid moves for the king
        validMoves = king2.getValidMoves();
        correctValidMoves = "3,4 3,2 4,3 2,3 4,4 2,4 4,2 2,2";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
		// Create a new king
        King king3 = new King(board3);
		// Set the king to the board
        board3.setPiece(0, 0, king3);
        validMoves = king3.getValidMoves();
        correctValidMoves = "0,1 1,0 1,1";
        assertEquals(correctValidMoves, validMoves);
	}
	
	// Test for checking if a move is valid for the king
	@Test
	void isMoveValidTest() {
		// Create a new board
        Board board = new Board();
		// Create a new king
        King king = new King(board);
		// Set the king to the board
        board.setPiece(3, 3, king);
		// Test valid moves
        assertTrue(king.isMoveValid(3, 4));

        assertFalse(king.isMoveValid(5, 4));
        assertFalse(king.isMoveValid(7, 7));
        assertFalse(king.isMoveValid(3, 3));

        // Set a pawn to the board
        board.setPiece(4, 4, new Pawn(board, false));
		// Test valid moves
        assertTrue(king.isMoveValid(4, 4));
        assertFalse(king.isMoveValid(5, 5));
	}
}
