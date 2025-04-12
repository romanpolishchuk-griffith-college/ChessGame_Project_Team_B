package griffith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

//In all tests it is implied that pawn is white
public class PawnTest {
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
        
        Board board2 = new Board();
        Pawn pawn2 = new Pawn(board2);
        
        //Check piece capturing
        board2.setPiece(3, 3, pawn2);
        board2.setPiece(4, 4, new Knight(board2, false));
        validMoves = pawn2.getValidMoves();
        correctValidMoves = "3,4 4,4";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
        Pawn pawn3 = new Pawn(board3);
        
        //Test piece in the corner
        board3.setPiece(0, 0, pawn3);
        validMoves = pawn3.getValidMoves();
        correctValidMoves = "0,1";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {
		Board board = new Board();
        Pawn pawn = new Pawn(board);

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
        assertFalse(pawn.isMoveValid(2, 4));
        
        //Out of bounds of the board
        Board board2 = new Board();
        Pawn pawn2 = new Pawn(board2);
        board.setPiece(3, 0, pawn2);
        assertFalse(pawn2.isMoveValid(3, -1));
	}
}
