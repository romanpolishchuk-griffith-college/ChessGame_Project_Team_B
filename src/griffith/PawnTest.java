package griffith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PawnTest {
	@Test
	void getValidMovesTest() {
        Board board = new Board();
        Pawn pawn = new Pawn(board);

        board.setPiece(3, 3, pawn);
        String validMoves = pawn.getValidMoves();
        String correctValidMoves = "3,4";
        assertEquals(correctValidMoves, validMoves);
        
        Board board2 = new Board();
        Pawn pawn2 = new Pawn(board2);
        
        board2.setPiece(3, 3, pawn2);
        board2.setPiece(4, 4, new Knight(board2, false));
        validMoves = pawn2.getValidMoves();
        correctValidMoves = "3,4 4,4";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
        Pawn pawn3 = new Pawn(board3);
        
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

        assertTrue(pawn.isMoveValid(3, 4));

        assertFalse(pawn.isMoveValid(4, 4));
        assertFalse(pawn.isMoveValid(5, 6));
        assertFalse(pawn.isMoveValid(3, 3));

        board.setPiece(4, 4, new Pawn(board, false));

        assertTrue(pawn.isMoveValid(4, 4));
        assertFalse(pawn.isMoveValid(2, 4));
	}
}
