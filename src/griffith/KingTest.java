package griffith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KingTest {
	@Test
	void getValidMovesTest() {
        Board board = new Board();
        King king = new King(board);

        board.setPiece(3, 3, king);
        String validMoves = king.getValidMoves();
        String correctValidMoves = "3,4 3,2 4,3 2,3 4,4 2,4 4,2 2,2";
        assertEquals(correctValidMoves, validMoves);
        
        Board board2 = new Board();
        King king2 = new King(board2);
        
        board2.setPiece(3, 3, king2);
        board2.setPiece(4, 4, new Knight(board2, false));
        validMoves = king2.getValidMoves();
        correctValidMoves = "3,4 3,2 4,3 2,3 4,4 2,4 4,2 2,2";
        assertEquals(correctValidMoves, validMoves);

        Board board3 = new Board();
        King king3 = new King(board3);
        
        board3.setPiece(0, 0, king3);
        validMoves = king3.getValidMoves();
        correctValidMoves = "0,1 1,0 1,1";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {
		Board board = new Board();
        King king = new King(board);

        board.setPiece(3, 3, king);

        assertTrue(king.isMoveValid(3, 4));

        assertFalse(king.isMoveValid(5, 4));
        assertFalse(king.isMoveValid(7, 7));
        assertFalse(king.isMoveValid(3, 3));

        board.setPiece(4, 4, new Pawn(board, false));

        assertTrue(king.isMoveValid(4, 4));
        assertFalse(king.isMoveValid(5, 5));
	}
}
