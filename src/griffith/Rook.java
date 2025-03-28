package griffith;

public class Rook extends ChessPiece {

	public Rook(Board board, int col, int row, boolean isWhite) {
		 super(board);
	        this.col = col;
	        this.row = row;
	        this.isWhite = isWhite;
	        this.name = "Rook";
	        
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - rook.png" : "b - rook.png";
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
