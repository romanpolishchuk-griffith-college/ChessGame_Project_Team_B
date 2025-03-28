package griffith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KnightTest {
	@Test
	void getValidMovesTest() {
        Board board = new Board();
        Knight knight = new Knight(board);

        board.setPiece(3, 3, knight);
        String validMoves = knight.getValidMoves();
        String correctValidMoves = "1,4 2,5 4,5 5,4 5,2 4,1 2,1 1,2";
        assertEquals(correctValidMoves, validMoves);

        Board board2 = new Board();
        Knight knight2 = new Knight(board2);
        
        board2.setPiece(0, 0, knight2);
        validMoves = knight2.getValidMoves();
        correctValidMoves = "2,1 1,2";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {

	}
}
