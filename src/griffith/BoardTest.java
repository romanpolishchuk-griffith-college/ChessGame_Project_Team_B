package griffith;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

class BoardTest {

	@Test
	void getPieceTest() {
		Board board = new Board();

		ChessPiece[][] testBoardPlacement = {
				{null, null, null, null, null, null, new Knight(board), null},
				{null, new Rook(board), null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, new Queen(board), null, null},
				{null, null, new Pawn(board), null, null, null, null, null},
				{new Pawn(board), null, null, null, new King(board), null, null, null},
		};

		board.setBoard(testBoardPlacement);

		assertInstanceOf(Pawn.class, board.getPiece(0, 0));
		assertEquals(null, board.getPiece(1, 0));
		assertInstanceOf(Rook.class, board.getPiece(1, 6));
		assertInstanceOf(King.class, board.getPiece(4, 0));

		assertEquals(null, board.getPiece(-10, 0));
		assertEquals(null, board.getPiece(0, -10));
		assertEquals(null, board.getPiece(-10, -10));
	}

	@Test
	void setPieceTest() {
		Board board = new Board(false);

		board.setPiece(0, 0, new Rook(board));
		board.setPiece(1, 2, new Queen(board));
		board.setPiece(3, 4, new King(board));

		assertEquals(null, board.getPiece(1, 0));

		assertInstanceOf(Rook.class, board.getPiece(0, 0));
		assertInstanceOf(Queen.class, board.getPiece(1, 2));
		assertInstanceOf(King.class, board.getPiece(3, 4));
	}

	@Test
	void getBoardTest() {
		Board board = new Board(false);
		Board board2 = new Board(false);

		ChessPiece[][] testBoardPlacement = {
				{null, null, null, null, null, null, new Knight(board), null},
				{null, new Rook(board), null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, new Queen(board), null, null},
				{null, null, new Pawn(board), null, null, null, null, null},
				{new Pawn(board), null, null, null, new King(board), null, null, null},
		};
		
		ChessPiece[][] testBoardPlacement2 = {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
		};

		board.setBoard(testBoardPlacement);
		
		assertArrayEquals(testBoardPlacement, board.getBoard());

		assertArrayEquals(testBoardPlacement2, board2.getBoard());
	}

	@Test
    public void testGetSquareColor() {
		Board board = new Board();
		// Test color of squares
		assertEquals(Color.WHITE, board.getSquareColor(0, 0));
		assertEquals(Color.BLACK, board.getSquareColor(0, 1));
	    assertEquals(Color.BLACK, board.getSquareColor(1, 0));
	    assertEquals(Color.WHITE, board.getSquareColor(1, 1));
	}
	  
}
