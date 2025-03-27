package griffith;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

	private Board board;
	private Knight whiteKnight;
	private Knight blackKnight;
	
	@BeforeEach
	public void setUp() {
		board = new Board();
	}
	
	@Test
	public void testBoardSize() {
		// Testing if chess board is 8x8 
		assertEquals(8,board.getBoredSize());
	}
	
	@Test
    public void testGetSquareColor() {
		// Test color of squares
		assertEquals(Color.WHITE, board.getSquareColor(0, 0));
		assertEquals(Color.BLACK, board.getSquareColor(0, 1));
	    assertEquals(Color.BLACK, board.getSquareColor(1, 0));
	    assertEquals(Color.WHITE, board.getSquareColor(1, 1));
	}
	
	@Test
	  void testKnightMovementValidation() {
	    // Test that isMoveValid() returns false (since it's not implemented yet)
	    assertFalse(whiteKnight.isMoveValid());
	    assertFalse(blackKnight.isMoveValid());
	  }

	  @Test
	  void testKnightValidMoves() {
	    // Test that getValidMoves() returns empty string (since it's not implemented yet)
	    assertEquals("", whiteKnight.getValidMoves());
	    assertEquals("", blackKnight.getValidMoves());
	  }
	
}
