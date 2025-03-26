package griffith;

public abstract class ChessPiece {

	protected Board board;

	ChessPiece(Board board) {
		this.board = board;
	}

	abstract public String getValidMoves();
	
	abstract public boolean isMoveValid();
	
	abstract public void draw();
	
}
