package griffith;

public class Queen extends ChessPiece {

	public Queen(Board board, int col, int row, boolean isWhite) {
		 super(board);
	        this.col = col;
	        this.row = row;
	        this.isWhite = isWhite;
	        this.name = "Queen";
	        
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - queen.png" : "b - queen.png";
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
