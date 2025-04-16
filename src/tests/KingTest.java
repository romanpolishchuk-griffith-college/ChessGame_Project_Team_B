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
		//Create test board
        Board board = new Board();
        //Create test piece
        King king = new King(board);

        //Add piece in the middle of the board
        board.setPiece(3, 3, king);
        //Get all valid moves
        String validMoves = king.getValidMoves();
        //Correct valid moves
        String correctValidMoves = "3,4 3,2 4,3 2,3 4,4 2,4 4,2 2,2";
        //Validate moves
        assertEquals(correctValidMoves, validMoves);
        
        //Create test board
        Board board2 = new Board();
        //Create test piece
        King king2 = new King(board2);
        
        //Check piece capturing
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
        
        //Test piece in the corner
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

        //Vertical move
        assertTrue(king.isMoveValid(3, 4));

        assertFalse(king.isMoveValid(5, 4));
        assertFalse(king.isMoveValid(7, 7));
        //The same spot
        assertFalse(king.isMoveValid(3, 3));

        // Set a pawn to the board
        board.setPiece(4, 4, new Pawn(board, false));

        //Capture
        assertTrue(king.isMoveValid(4, 4));
        //Go over capture peace
        assertFalse(king.isMoveValid(5, 5));
        
        Board board2 = new Board();
        King king2 = new King(board2);
        
        board2.setPiece(0, 0, king2);
        
        // Go out of bounds of the board left bottom corner
        assertFalse(king2.isMoveValid(0, -1));
        assertFalse(king2.isMoveValid(-1, 0));
        assertFalse(king2.isMoveValid(-1, -1));
        
        Board board3 = new Board();
        King king3 = new King(board3);
        
        board3.setPiece(7, 7, king3);
        
        // Go out of bounds of the board right up corner
        assertFalse(king3.isMoveValid(7, 8));
        assertFalse(king3.isMoveValid(8, 7));
        assertFalse(king3.isMoveValid(8, 8));
	}
}
