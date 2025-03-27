package griffith;

public class Knight extends ChessPiece {

	public Knight(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.name = "Knight";
   
    }
	
	@Override
	public String getValidMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMoveValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
