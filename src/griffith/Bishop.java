package griffith;

public class Bishop extends ChessPiece {

	public Bishop(Board board, int col, int row, boolean isWhite) {
		 super(board);
	        this.col = col;
	        this.row = row;
	        this.isWhite = isWhite;
	        this.name = "Bishop";
	        
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - bishop.png" : "b - bishop.png";
	        loadImage(imageName);
	   
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

}
