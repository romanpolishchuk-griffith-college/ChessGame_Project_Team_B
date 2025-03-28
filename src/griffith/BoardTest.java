package griffith;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

	private Board board; 
	 
	Knight whiteKnight;
	Knight blackKnight;
	Rook whiteRook;
	Rook blackRook;
	
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
	  void testKnightInitialization() {
		  
		Knight whiteKnight = new Knight(board, 1, 0, true);
		Knight blackKnight = new Knight(board, 6, 7, false);

	    // Test white knight initialization
	    assertEquals(1, whiteKnight.col);		 	// Test position
	    assertEquals(0, whiteKnight.row); 			// Test position
	    assertTrue(whiteKnight.isWhite); 			// Test color
	    assertEquals("Knight", whiteKnight.name); 	// Test name

	    // Test black knight initialization
	    assertEquals(6, blackKnight.col); 			// Test position
	    assertEquals(7, blackKnight.row);			// Test position
	    assertFalse(blackKnight.isWhite); 			// Test color
	    assertEquals("Knight", blackKnight.name); 	// Test name
	  }
	  
	  @Test
	  void testKnightPositionCalculation() {
		  
		Knight whiteKnight = new Knight(board, 1, 0, true);
		Knight blackKnight = new Knight(board, 6, 7, false);
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
	  public void testRookInitialization() {
		Rook whiteRook = new Rook(board, 0, 0, true);
		Rook blackRook = new Rook(board, 0, 7, false);
		
		  
		  // Test white knight initialization
		  assertEquals(0, whiteRook.col);  	 	 // Test position
		  assertEquals(0, whiteRook.row);  	 	 // Test position
		  assertTrue(whiteRook.isWhite);	 	 // Test color
		  assertEquals("Rook", whiteRook.name);  // Test name
		  
		// Test black knight initialization
		  assertEquals(0, blackRook.col);  	 	 // Test position
		  assertEquals(7, blackRook.row);  	 	 // Test position
		  assertFalse(blackRook.isWhite);	 	 // Test color
		  assertEquals("Rook", blackRook.name);  // Test name
	  }
	  
	  @Test
	  public void testBishopInitialization() {
	        Bishop whiteBishop = new Bishop(board, 2, 0, true);
	        Bishop blackBishop = new Bishop(board, 2, 7, false);
	        
	        
	        assertEquals(2, whiteBishop.col);			// Test position
	        assertEquals(0, whiteBishop.row);			// Test position
	        assertTrue(whiteBishop.isWhite);			// Test color
	        assertEquals("Bishop", blackBishop.name);  	// Test name
	        
	        assertEquals(2, blackBishop.col);			// Test position
	        assertEquals(7, blackBishop.row);			// Test position
	        assertTrue(blackBishop.isWhite);			// Test color
	        assertEquals("Bishop", blackBishop.name);  	// Test name
	    }
	
	  @Test
	  public void testQueenInitialization() {
		  	Queen whiteQueen = new Queen(board, 3, 0, true);
		  	Queen blackQueen = new Queen(board, 3, 7, false);
	        
	        
	        assertEquals(3, whiteQueen.col);			// Test position
	        assertEquals(0, whiteQueen.row);			// Test position
	        assertTrue(whiteQueen.isWhite);				// Test color
	        assertEquals("Queen", whiteQueen.name);  	// Test name
	        
	        assertEquals(3, blackQueen.col);			// Test position
	        assertEquals(7, blackQueen.row);			// Test position
	        assertTrue(blackQueen.isWhite);				// Test color
	        assertEquals("Queen", blackQueen.name);  	// Test name
	    }
	  
	  @Test
	  public void testKingInitialization() {
	        King whiteKing = new King(board, 4, 0, true);
	        King blackKing = new King(board, 4, 7, false);
	        
	        assertEquals(4, whiteKing.col);				// Test position
	        assertEquals(0, whiteKing.row);				// Test position
	        assertTrue(whiteKing.isWhite);				// Test color
	        assertEquals("Queen", whiteKing.name);		// Test name
	        
	        assertEquals(4, blackKing.col);				// Test position
	        assertEquals(0, blackKing.row);				// Test position
	        assertTrue(blackKing.isWhite);				// Test color
	        assertEquals("King", blackKing.name);		// Test name
	    }
}
