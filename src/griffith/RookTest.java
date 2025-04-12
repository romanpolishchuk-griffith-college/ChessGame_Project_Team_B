package griffith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class RookTest {
	@Test
	void getValidMovesTest() {
		//Create test board
        Board board = new Board();
        //Create test piece
        Rook rook = new Rook(board);

        //Add piece in the middle of the board
        board.setPiece(3, 3, rook);
        //Get all valid moves
        String validMoves = rook.getValidMoves();
        //Correct valid moves
        String correctValidMoves = "3,4 3,5 3,6 3,7 3,2 3,1 3,0 4,3 5,3 6,3 7,3 2,3 1,3 0,3";
        //Validate moves
        assertEquals(correctValidMoves, validMoves);
        
        Board board2 = new Board();
        Rook rook2 = new Rook(board2);
        
        //Check piece capturing
        board2.setPiece(3, 3, rook2);
        board2.setPiece(3, 5, new Knight(board2, false));
        validMoves = rook2.getValidMoves();
        correctValidMoves = "3,4 3,5 3,2 3,1 3,0 4,3 5,3 6,3 7,3 2,3 1,3 0,3";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
        Rook rook3 = new Rook(board3);
        
        //Test piece in the corner
        board3.setPiece(0, 0, rook3);
        validMoves = rook3.getValidMoves();
        correctValidMoves = "0,1 0,2 0,3 0,4 0,5 0,6 0,7 1,0 2,0 3,0 4,0 5,0 6,0 7,0";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {
		Board board = new Board();
        Rook rook = new Rook(board);

        board.setPiece(3, 3, rook);

        //Test valid moves
        assertTrue(rook.isMoveValid(3, 5));
        assertTrue(rook.isMoveValid(6, 3));
        assertTrue(rook.isMoveValid(3, 0));
        assertTrue(rook.isMoveValid(0, 3));

        //Test invalid moves
        assertFalse(rook.isMoveValid(4, 4));
        assertFalse(rook.isMoveValid(5, 6));
        assertFalse(rook.isMoveValid(3, 3));

        board.setPiece(3, 5, new Pawn(board, false));

        //Capture
        assertTrue(rook.isMoveValid(3, 5));
        assertFalse(rook.isMoveValid(3, 6));
        
        Board board2 = new Board();
        Rook rook2 = new Rook(board2);

        board2.setPiece(3, 3, rook2);
        
        assertFalse(rook2.isMoveValid(3, -1));
        assertFalse(rook2.isMoveValid(-1, 3));
        assertFalse(rook2.isMoveValid(3, 8));
        assertFalse(rook2.isMoveValid(8, 3));
	}
}
