package griffith;

public class Pawn extends ChessPiece {

	public Pawn(Board board, int col, int row, boolean isWhite) {
		 super(board);
	        this.col = col;
	        this.row = row;
	        this.isWhite = isWhite;
	        this.name = "Pawn";
	        
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - pawn.png" : "b - pawn.png";
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
