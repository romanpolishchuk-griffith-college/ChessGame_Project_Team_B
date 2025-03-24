package griffith;

public abstract class ChessPiece {

	private	Board board;

	ChessPiece(Board board) {
		this.board = board;
	}

	abstract public String getValidMoves();
	
	abstract public boolean isMoveValid();
	
	abstract public void draw();
	
}
