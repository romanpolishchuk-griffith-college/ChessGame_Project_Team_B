package griffith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class KnightTest {
	@Test
	void getValidMovesTest() {
		//Create test board
        Board board = new Board();
        //Create test piece
        Knight knight = new Knight(board);

        //Add piece in the middle of the board
        board.setPiece(3, 3, knight);
        //Get all valid moves
        String validMoves = knight.getValidMoves();
        //Correct valid moves
        String correctValidMoves = "1,4 2,5 4,5 5,4 5,2 4,1 2,1 1,2";
        //Validate moves
        assertEquals(correctValidMoves, validMoves);

        Board board2 = new Board();
        Knight knight2 = new Knight(board2);
        
        //Test piece in the corner
        board2.setPiece(0, 0, knight2);
        validMoves = knight2.getValidMoves();
        correctValidMoves = "1,2 2,1";
        assertEquals(correctValidMoves, validMoves);
	}
	
	@Test
	void isMoveValidTest() {
		Board board = new Board();
        Knight knight = new Knight(board);

        board.setPiece(3, 3, knight);

        //Valid moves
        assertTrue(knight.isMoveValid(1, 4));
        assertTrue(knight.isMoveValid(2, 5));
        assertTrue(knight.isMoveValid(5, 2));
        assertTrue(knight.isMoveValid(1, 2));

        //Invalid moves
        assertFalse(knight.isMoveValid(6, 6));
        assertFalse(knight.isMoveValid(0, 0));
        assertFalse(knight.isMoveValid(3, 3));
        
        Board board2 = new Board();
        Knight knight2 = new Knight(board2);

        board.setPiece(0, 0, knight2);
        //Go out of bounds out the board
        assertFalse(knight.isMoveValid(-1, 2));
        assertFalse(knight.isMoveValid(-1, -2));
	}
}
