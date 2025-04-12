package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import griffith.Board;
import griffith.Knight;
import griffith.Pawn;
// Represents a test for the Pawn class.
public class PawnTest {
	// Test for getting the valid moves for the pawn
	@Test
	void getValidMovesTest() {
		// Create a new board
        Board board = new Board();
		// Create a new pawn
        Pawn pawn = new Pawn(board);
		// Set the pawn to the board
        board.setPiece(3, 3, pawn);
		// Get the valid moves for the pawn
        String validMoves = pawn.getValidMoves();
		// The correct valid moves for the pawn
        String correctValidMoves = "3,4";
		// Assert that the valid moves are correct
        assertEquals(correctValidMoves, validMoves);
        
        // Create a new board
        Board board2 = new Board();
		// Create a new pawn
        Pawn pawn2 = new Pawn(board2);
		// Set the pawn to the board
        board2.setPiece(3, 3, pawn2);
		// Set a knight to the board
        board2.setPiece(4, 4, new Knight(board2, false));
		// Get the valid moves for the pawn
        validMoves = pawn2.getValidMoves();
		// The correct valid moves for the pawn
        correctValidMoves = "3,4 4,4";
		// Assert that the valid moves are correct
        assertEquals(correctValidMoves, validMoves);

        // Create a new board
        Board board3 = new Board();
		// Create a new pawn
        Pawn pawn3 = new Pawn(board3);
		// Set the pawn to the board
        board3.setPiece(0, 0, pawn3);
        validMoves = pawn3.getValidMoves();
        correctValidMoves = "0,1";
        assertEquals(correctValidMoves, validMoves);
	}
	// Test for checking if a move is valid for the pawn
	@Test
	void isMoveValidTest() {
		// Create a new board
        Board board = new Board();
        Pawn pawn = new Pawn(board);
		// Set the pawn to the board
        board.setPiece(3, 3, pawn);
		// Test valid moves
        assertTrue(pawn.isMoveValid(3, 4));
		// Test invalid moves
        assertFalse(pawn.isMoveValid(4, 4));
        assertFalse(pawn.isMoveValid(5, 6));
        assertFalse(pawn.isMoveValid(3, 3));

        board.setPiece(4, 4, new Pawn(board, false));
		// Test valid moves             
        assertTrue(pawn.isMoveValid(4, 4));
		// Test invalid moves
        assertFalse(pawn.isMoveValid(2, 4));
	}
}
