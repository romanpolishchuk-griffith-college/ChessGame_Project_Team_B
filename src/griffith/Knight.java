package griffith;

public class Knight extends ChessPiece {

	public Knight(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.isWhite = isWhite;
        this.name = "Knight";
        
        // Load the appropriate knight image
        String imageName = isWhite ? "w - knight.png" : "b - knight.png";
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
