package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import griffith.Bishop;
import griffith.Board;
import griffith.Knight;
import griffith.Pawn;
import griffith.Queen;

// Test class for the Bishop class
public class BishopTest {
    // Test for getting valid moves for a bishop
	@Test
	void getValidMovesTest() {
		Board board = new Board();
		Bishop bishop = new Bishop(board);
        // Set the bishop on the board
        board.setPiece(3, 3, bishop);
        String validMoves = bishop.getValidMoves();
        String correctValidMoves = "4,4 5,5 6,6 7,7 2,4 1,5 0,6 4,2 5,1 6,0 2,2 1,1 0,0";
        assertEquals(correctValidMoves, validMoves);
        
        Board board2 = new Board();
        Bishop bishop2 = new Bishop(board2);
        // Set the bishop on the board
        board2.setPiece(3, 3, bishop2);
        board2.setPiece(5, 5, new Knight(board2, false));
        validMoves = bishop2.getValidMoves();
        correctValidMoves = "4,4 5,5 2,4 1,5 0,6 4,2 5,1 6,0 2,2 1,1 0,0";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
        Bishop bishop3 = new Bishop(board3);
        // Set the bishop on the board
        board3.setPiece(0, 0, bishop3);
        validMoves = bishop3.getValidMoves();
        correctValidMoves = "1,1 2,2 3,3 4,4 5,5 6,6 7,7";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {
		Board board = new Board();
        Queen queen = new Queen(board);

        board.setPiece(3, 3, queen);
        // Test valid moves
        assertTrue(queen.isMoveValid(4, 4));
        assertTrue(queen.isMoveValid(4, 2));
        assertTrue(queen.isMoveValid(2, 4));
        assertTrue(queen.isMoveValid(2, 2));
        // Test invalid moves
        assertFalse(queen.isMoveValid(7, 2));
        assertFalse(queen.isMoveValid(3, 3));
        // Test capturing a piece
        board.setPiece(5, 5, new Pawn(board, false));
        // Test valid moves
        assertTrue(queen.isMoveValid(5, 5));
        assertFalse(queen.isMoveValid(6, 6));
	}
}
