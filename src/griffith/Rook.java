package griffith;

public class Rook extends ChessPiece {
    public Rook(Board board, boolean isWhite) {
		super(board);
	        
	    // Load the appropriate knight image
	    String imageName = isWhite ? "w - rook.png" : "b - rook.png";
	    loadImage(imageName);
	  
	}
    
    public Rook(Board board) {
  		super(board);  	   
  	}


	@Override
	public String getValidMoves() {
		String moves = "";
        int rookX = -1;
        int rookY = -1;

        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                    rookX = x;
                    rookY = y;
                    break;
                }
            }
        }

        // Move Up
        for (int y = rookY + 1; y < board.getBoard().length; y++) {
            if (board.getPiece(rookX, y) == null) {
                moves += rookX + "," + y + " ";
            } else {
                moves += rookX + "," + y + " ";
                // Stop if can capture a piece
                break;
            }
        }

        // Move Down
        for (int y = rookY - 1; y >= 0; y--) {
            if (board.getPiece(rookX, y) == null) {
            	moves += rookX + "," + y + " ";
            } else {
            	moves += rookX + "," + y + " ";
                // Stop if can capture a piece
                break;
            }
        }

        // Move Right
        for (int x = rookX + 1; x < board.getBoard()[0].length; x++) {
            if (board.getPiece(x, rookY) == null) {
                moves += x + "," + rookY + " ";
            } else {
                moves += x + "," + rookY + " ";
                // Stop if can capture a piece
                break;
            }
        }

        // Move Left
        for (int x = rookX - 1; x >= 0; x--) {
            if (board.getPiece(x, rookY) == null) {
            	moves += x + "," + rookY + " ";
            } else {
            	moves += x + "," + rookY + " ";
            	// Stop if can capture a piece
                break;
            }
        }

        return moves.trim();
	}

	@Override
	public boolean isMoveValid(int x, int y) {
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
	}

}
