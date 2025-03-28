package griffith;

public class King extends ChessPiece {

	public King(Board board, int col, int row, boolean isWhite) {
		 super(board);
	        this.col = col;
	        this.row = row;
	        this.isWhite = isWhite;
	        this.name = "King";
	        
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - king.png" : "b - king.png";
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
