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
		whiteKnight = new Knight(board, 1, 0, true);  // b1 position
	    blackKnight = new Knight(board, 6, 7, false); // g8 position
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
		// TODO
	    // Test that isMoveValid() returns false (since it's not implemented yet)
	    assertFalse(whiteKnight.isMoveValid());
	    assertFalse(blackKnight.isMoveValid());
	  }

	  @Test
	  void testKnightValidMoves() {
		// TODO
	    // Test that getValidMoves() returns empty string (since it's not implemented yet)
	    assertEquals("", whiteKnight.getValidMoves());
	    assertEquals("", blackKnight.getValidMoves());
	  }
	  
	  @Test
	  void testKnightInitialization() {
	    // Test white knight initialization
	    assertEquals(1, whiteKnight.col);
	    assertEquals(0, whiteKnight.row);
	    assertTrue(whiteKnight.isWhite);
	    assertEquals("Knight", whiteKnight.name);

	    // Test black knight initialization
	    assertEquals(6, blackKnight.col);
	    assertEquals(7, blackKnight.row);
	    assertFalse(blackKnight.isWhite);
	    assertEquals("Knight", blackKnight.name);
	  }
	  
	  @Test
	  void testKnightPositionCalculation() {
	    // Test white knight position calculation
	    whiteKnight.updatePosition();
	    assertEquals(80, whiteKnight.xPos);  // 1 * SQUARE_SIZE
	    assertEquals(0, whiteKnight.yPos);   // 0 * SQUARE_SIZE

	    // Test black knight position calculation
	    blackKnight.updatePosition();
	    assertEquals(480, blackKnight.xPos); // 6 * SQUARE_SIZE
	    assertEquals(560, blackKnight.yPos); // 7 * SQUARE_SIZE
	  }
	 
	  @Test
	  void testKnightImageLoading() {
	    // Test that sprites are loaded (not null)
	    assertNotNull(whiteKnight.sprite);
	    assertNotNull(blackKnight.sprite);
	  }
}
