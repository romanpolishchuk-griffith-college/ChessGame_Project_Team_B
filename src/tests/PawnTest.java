package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import griffith.Board;
import griffith.Knight;
import griffith.Pawn;

//In all tests it is implied that pawn is white
public class PawnTest {
	// Test for getting the valid moves for the pawn
	@Test
	void getValidMovesTest() {
		//Create test board
        Board board = new Board();
        //Create test piece
        Pawn pawn = new Pawn(board);

        //Add piece in the middle of the board
        board.setPiece(3, 3, pawn);
        //Get all valid moves
        String validMoves = pawn.getValidMoves();
        //Correct valid moves
        String correctValidMoves = "3,4";
        //Validate moves
        assertEquals(correctValidMoves, validMoves);
        
        // Create a new board
        Board board2 = new Board();
		// Create a new pawn
        Pawn pawn2 = new Pawn(board2);
        
        //Check piece capturing
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
        
        //Test piece in the corner
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

        //Test valid move
        assertTrue(pawn.isMoveValid(3, 4));

        //Test valid move
        assertFalse(pawn.isMoveValid(4, 4));
        assertFalse(pawn.isMoveValid(5, 6));
        assertFalse(pawn.isMoveValid(3, 3));

        board.setPiece(4, 4, new Pawn(board, false));

        //Capture
        assertTrue(pawn.isMoveValid(4, 4));
		// Test invalid moves
        assertFalse(pawn.isMoveValid(2, 4));
        
        //Out of bounds of the board
        Board board2 = new Board();
        Pawn pawn2 = new Pawn(board2);
        board.setPiece(3, 0, pawn2);
        assertFalse(pawn2.isMoveValid(3, -1));
	}
}
