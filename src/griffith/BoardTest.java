package griffith;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoardTest {

	private ChessPiece[][] testBoard = {
			{null, null, null, null, null, null, new Knight(), null},
			{null, new Rook(), null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, new Queen(), null, null},
			{null, null, new Pawn(), null, null, null, null, null},
			{new Pawn(), null, null, null, new King(), null, null, null},
	};

	@Test
	void getPieceTest() {
		Board board = new Board(testBoard);

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
		Board board = new Board(testBoard);

		board.setPiece(0, 0, new Rook());
		board.setPiece(1, 2, new Queen());
		board.setPiece(3, 4, new King());

		assertEquals(null, board.getPiece(1, 0));
		assertInstanceOf(Rook.class, board.getPiece(1, 6));
		assertInstanceOf(King.class, board.getPiece(4, 0));

		assertInstanceOf(Rook.class, board.getPiece(0, 0));
		assertInstanceOf(Queen.class, board.getPiece(1, 2));
		assertInstanceOf(King.class, board.getPiece(3, 4));
	}

}
