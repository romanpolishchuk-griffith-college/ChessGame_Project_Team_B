package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import griffith.Bishop;
import griffith.Board;
import griffith.ChessPiece;
import griffith.King;
import griffith.Knight;
import griffith.Pawn;
import griffith.Queen;
import griffith.Rook;

class BoardTest {
	// Test for getting a piece from the board
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

		// Set the board
		board.setBoard(testBoardPlacement);

		// Test the piece at the specified coordinates
		assertInstanceOf(Pawn.class, board.getPiece(0, 0));
		assertEquals(null, board.getPiece(1, 0));
		assertInstanceOf(Rook.class, board.getPiece(1, 6));
		assertInstanceOf(King.class, board.getPiece(4, 0));

		// Test invalid coordinates
		assertEquals(null, board.getPiece(-10, 0));
		assertEquals(null, board.getPiece(0, -10));
		assertEquals(null, board.getPiece(-10, -10));
	}

	// Test for setting a piece on the board
	@Test
	void setPieceTest() {
		Board board = new Board(false);

		// Set the piece on the board
		board.setPiece(0, 0, new Rook(board));
		board.setPiece(1, 2, new Queen(board));
		board.setPiece(3, 4, new King(board));
		// Test the piece at the specified coordinates
		assertEquals(null, board.getPiece(1, 0));
		// Test the piece at the specified coordinates
		assertInstanceOf(Rook.class, board.getPiece(0, 0));
		assertInstanceOf(Queen.class, board.getPiece(1, 2));
		assertInstanceOf(King.class, board.getPiece(3, 4));
	}

	// Test for getting the board
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

		// Set the board
		board.setBoard(testBoardPlacement);
		// Test the board
		assertArrayEquals(testBoardPlacement, board.getBoard());

		assertArrayEquals(testBoardPlacement2, board2.getBoard());
	}
	// Test for getting the color of a square
	@Test
    public void testGetSquareColor() {
		Board board = new Board();
		// Test color of squares
		assertEquals(Color.WHITE, board.getSquareColor(0, 0));
		assertEquals(Color.BLACK, board.getSquareColor(0, 1));
	    assertEquals(Color.BLACK, board.getSquareColor(1, 0));
	    assertEquals(Color.WHITE, board.getSquareColor(1, 1));
	}
	  
	@Test
	public void testIsWhiteWon() {
		Board board = new Board();
		
		//Black pawn
		Pawn pawn = new Pawn(board, false);
		//Black pawn
		Pawn pawn2 = new Pawn(board, false);
		//Black king
		King king = new King(board, false);
		
		//White rook
		Rook rook = new Rook(board);
		//White bishop
		Bishop bishop = new Bishop(board);
		//White king
		King king2 = new King(board);
		
		board.setPiece(5, 6, pawn);
		board.setPiece(7, 6, pawn2);
		board.setPiece(7, 7, king);
		
		board.setPiece(7, 0, king2);
		board.setPiece(6, 0, rook);
		board.setPiece(5, 5, bishop);
		
		assertTrue(board.isWhiteWon());
		assertFalse(board.isBlackWon());
		
	}
	
	@Test
	public void testIsBlackWon() {
		Board board = new Board();
		
		//Black king
		King king = new King(board, false);
		//Black rook
		Rook rook = new Rook(board, false);
		
		//White pawn
		Pawn pawn = new Pawn(board);
		//White pawn
		Pawn pawn2 = new Pawn(board);
		//White pawn
		Pawn pawn3 = new Pawn(board);
		//White king
		King king2 = new King(board);
	
		
		board.setPiece(0, 0, king2);
		board.setPiece(0, 1, pawn);
		board.setPiece(1, 1, pawn2);
		board.setPiece(2, 1, pawn3);
		
		board.setPiece(7, 7, king);
		board.setPiece(7, 0, rook);
		
		assertTrue(board.isBlackWon());
		assertFalse(board.isWhiteWon());
	}
	@Test
    void testGameEndsWhenKingCaptured() {
        Board board = new Board(true);

        // Place a white king and a black rook
        King whiteKing = new King(board, true);
        Rook blackRook = new Rook(board, false);
        board.setPiece(4, 4, whiteKing);
        board.setPiece(4, 5, blackRook);

        // Capture the white king
        board.movePiece(blackRook, 4, 4);

        // Verify the king is captured and game is over
        assertTrue(board.isGameOver(), "Game should be marked as over when king is captured.");
    }
}
