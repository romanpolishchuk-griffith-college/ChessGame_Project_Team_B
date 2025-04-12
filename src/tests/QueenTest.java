package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import griffith.Board;
import griffith.Knight;
import griffith.Pawn;
import griffith.Queen;
// Represents a test for the Queen class.
public class QueenTest {
	// Test for getting the valid moves for the queen
	@Test
	void getValidMovesTest() {
		// Create a new board
        Board board = new Board();
		// Create a new queen
        Queen queen = new Queen(board);
		// Set the queen to the board
        board.setPiece(3, 3, queen);
		// Get the valid moves for the queen
        String validMoves = queen.getValidMoves();
        String correctValidMoves = "3,4 3,5 3,6 3,7 3,2 3,1 3,0 4,3 5,3 6,3 7,3 2,3 1,3 0,3 4,4 5,5 6,6 7,7 2,4 1,5 0,6 4,2 5,1 6,0 2,2 1,1 0,0";
        assertEquals(correctValidMoves, validMoves);
        
        // Create a new board
        Board board2 = new Board();
		// Create a new queen
        Queen queen2 = new Queen(board2);
		// Set the queen to the board
        board2.setPiece(3, 3, queen2);
		// Set a knight to the board
        board2.setPiece(3, 5, new Knight(board2, false));
		// Get the valid moves for the queen
        validMoves = queen2.getValidMoves();
        correctValidMoves = "3,4 3,5 3,2 3,1 3,0 4,3 5,3 6,3 7,3 2,3 1,3 0,3 4,4 5,5 6,6 7,7 2,4 1,5 0,6 4,2 5,1 6,0 2,2 1,1 0,0";
        assertEquals(correctValidMoves, validMoves);

        // Create a new board
        Board board3 = new Board();
		// Create a new queen
        Queen queen3 = new Queen(board3);
		// Set the queen to the board
        board3.setPiece(0, 0, queen3);
        validMoves = queen3.getValidMoves();
        correctValidMoves = "0,1 0,2 0,3 0,4 0,5 0,6 0,7 1,0 2,0 3,0 4,0 5,0 6,0 7,0 1,1 2,2 3,3 4,4 5,5 6,6 7,7";
        assertEquals(correctValidMoves, validMoves);
	}
	// Test for checking if a move is valid for the queen
	@Test
	void isMoveValidTest() {
		// Create a new board
        Board board = new Board();
		// Create a new queen
        Queen queen = new Queen(board);
		// Set the queen to the board
        board.setPiece(3, 3, queen);
		// Test valid moves
        assertTrue(queen.isMoveValid(3, 5));
        assertTrue(queen.isMoveValid(6, 3));
        assertTrue(queen.isMoveValid(3, 0));
        assertTrue(queen.isMoveValid(0, 3));
		// Test invalid moves
        assertTrue(queen.isMoveValid(4, 4));
        assertTrue(queen.isMoveValid(4, 2));
        assertTrue(queen.isMoveValid(2, 4));
        assertTrue(queen.isMoveValid(2, 2));
		// Test invalid moves
        assertFalse(queen.isMoveValid(7, 2));
        assertFalse(queen.isMoveValid(3, 3));
        // Set a pawn to the board
        board.setPiece(3, 5, new Pawn(board, false));
		// Test valid moves
        assertTrue(queen.isMoveValid(3, 5));
		// Test invalid moves
        assertFalse(queen.isMoveValid(3, 6));
	}
}
