package griffith;

public class Bishop extends ChessPiece {

    // Constructor with specified board position and color.
    public Bishop(Board board, boolean isWhite) {
		 super(board, isWhite);
	        this.isWhite = isWhite;
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - bishop.png" : "b - bishop.png";
	        loadImage(imageName);
	   
	}
    
    // Constructor with specified board.
    public Bishop(Board board) {
		 super(board); 
	}
    
    //Calculates and returns valid moves for this bishop as a space-separated string.
    @Override
	public String getValidMoves() {
		String moves = "";
        int bishopX = -1;
        int bishopY = -1;

        // Find the bishop's position on the board
        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                	bishopX = x;
                	bishopY = y;
                    break;
                }
            }
        }

        // Move diagonally up-right
        for (int x = bishopX + 1, y = bishopY + 1; x < board.getBoard()[0].length && y < board.getBoard().length; x++, y++) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else if (board.getPiece(x, y).isWhite != isWhite){
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move diagonally up-left
        for (int x = bishopX - 1, y = bishopY + 1; x >= 0 && y < board.getBoard().length; x--, y++) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else if (board.getPiece(x, y).isWhite != isWhite){
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move diagonally down-right
        for (int x = bishopX + 1, y = bishopY - 1; x < board.getBoard()[0].length && y >= 0; x++, y--) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else if (board.getPiece(x, y).isWhite != isWhite){
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move diagonally down-left
        for (int x = bishopX - 1, y = bishopY - 1; x >= 0 && y >= 0; x--, y--) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else if (board.getPiece(x, y).isWhite != isWhite){
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Return the valid moves as a space-separated string
        return moves.trim();
	}
    
    // Checks if a move to the specified coordinates is valid for this bishop.
	@Override
	public boolean isMoveValid(int x, int y) {
        // Get the valid moves for this bishop
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
	}

	
}
