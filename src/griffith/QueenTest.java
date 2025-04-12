package griffith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QueenTest {
	@Test
	void getValidMovesTest() {
		//Create test board
		Board board = new Board();
		//Create test piece
		Queen queen = new Queen(board);

		//Add piece in the middle of the board
        board.setPiece(3, 3, queen);
        //Get all valid moves
        String validMoves = queen.getValidMoves();
        //Correct valid moves
        String correctValidMoves = "3,4 3,5 3,6 3,7 3,2 3,1 3,0 4,3 5,3 6,3 7,3 2,3 1,3 0,3 4,4 5,5 6,6 7,7 2,4 1,5 0,6 4,2 5,1 6,0 2,2 1,1 0,0";
        //Validate moves
        assertEquals(correctValidMoves, validMoves);
        
        Board board2 = new Board();
        Queen queen2 = new Queen(board2);
        
        //Check piece capturing
        board2.setPiece(3, 3, queen2);
        board2.setPiece(3, 5, new Knight(board2, false));
        validMoves = queen2.getValidMoves();
        correctValidMoves = "3,4 3,5 3,2 3,1 3,0 4,3 5,3 6,3 7,3 2,3 1,3 0,3 4,4 5,5 6,6 7,7 2,4 1,5 0,6 4,2 5,1 6,0 2,2 1,1 0,0";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
        Queen queen3 = new Queen(board3);
        
        //Test piece in the corner
        board3.setPiece(0, 0, queen3);
        validMoves = queen3.getValidMoves();
        correctValidMoves = "0,1 0,2 0,3 0,4 0,5 0,6 0,7 1,0 2,0 3,0 4,0 5,0 6,0 7,0 1,1 2,2 3,3 4,4 5,5 6,6 7,7";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {
		Board board = new Board();
        Queen queen = new Queen(board);

        board.setPiece(3, 3, queen);

        //Diagonal moves
        assertTrue(queen.isMoveValid(3, 5));
        assertTrue(queen.isMoveValid(6, 3));
        assertTrue(queen.isMoveValid(3, 0));
        assertTrue(queen.isMoveValid(0, 3));

        //Vertical moves
        assertTrue(queen.isMoveValid(4, 4));
        assertTrue(queen.isMoveValid(4, 2));
        //Horizontal moves
        assertTrue(queen.isMoveValid(2, 4));
        assertTrue(queen.isMoveValid(2, 2));
        
        assertFalse(queen.isMoveValid(7, 2));
        //Same spot
        assertFalse(queen.isMoveValid(3, 3));

        board.setPiece(3, 5, new Pawn(board, false));

        //Capture
        assertTrue(queen.isMoveValid(3, 5));
        assertFalse(queen.isMoveValid(3, 6));
        
        Board board2 = new Board();
        Queen queen2 = new Queen(board2);

        board2.setPiece(3, 3, queen2);
        
        //Out of bounds of the board
        assertFalse(queen2.isMoveValid(-1, -1));
        assertFalse(queen2.isMoveValid(8, 8));
        assertFalse(queen2.isMoveValid(-1, 8));
        assertFalse(queen2.isMoveValid(8, -1));
        assertFalse(queen2.isMoveValid(3, -1));
        assertFalse(queen2.isMoveValid(-1, 3));
        assertFalse(queen2.isMoveValid(3, 8));
        assertFalse(queen2.isMoveValid(8, 3));
	}
}
