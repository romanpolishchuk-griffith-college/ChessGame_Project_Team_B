package griffith;

public class Queen extends ChessPiece {

public Queen(Board board, boolean isWhite) {
	
	super(board,isWhite);
	        
	// Load the appropriate knight image
	String imageName = isWhite ? "w - queen.png" : "b - queen.png";
	loadImage(imageName);
	   
	}

public Queen(Board board) {	
	super(board);
	}

	@Override
	public String getValidMoves() {
		String moves = "";
        int queenX = -1;
        int queenY = -1;

        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                    queenX = x;
                    queenY = y;
                    break;
                }
            }
        }

        // Move Up
        for (int y = queenY + 1; y < board.getBoard().length; y++) {
            if (board.getPiece(queenX, y) == null) {
                moves += queenX + "," + y + " ";
            } else if (board.getPiece(queenX, y).isWhite != isWhite){
            	moves += queenX + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move Down
        for (int y = queenY - 1; y >= 0; y--) {
            if (board.getPiece(queenX, y) == null) {
                moves += queenX + "," + y + " ";
            } else if (board.getPiece(queenX, y).isWhite != isWhite){
            	moves += queenX + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move Right
        for (int x = queenX + 1; x < board.getBoard()[0].length; x++) {
            if (board.getPiece(x, queenY) == null) {
                moves += x + "," + queenY + " ";
            } else if (board.getPiece(x, queenY).isWhite != isWhite){
            	moves += x + "," + queenY + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move Left
        for (int x = queenX - 1; x >= 0; x--) {
            if (board.getPiece(x, queenY) == null) {
                moves += x + "," + queenY + " ";
            } else if (board.getPiece(x, queenY).isWhite != isWhite){
            	moves += x + "," + queenY + " ";
            	// Stop if can capture a piece
                break;
            }
            else {
            	// The same color of a piece
            	break;
            }
        }

        // Move diagonally up-right
        for (int x = queenX + 1, y = queenY + 1; x < board.getBoard()[0].length && y < board.getBoard().length; x++, y++) {
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
        for (int x = queenX - 1, y = queenY + 1; x >= 0 && y < board.getBoard().length; x--, y++) {
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
        for (int x = queenX + 1, y = queenY - 1; x < board.getBoard()[0].length && y >= 0; x++, y--) {
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
        for (int x = queenX - 1, y = queenY - 1; x >= 0 && y >= 0; x--, y--) {
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

        return moves.trim();
	}

	@Override
	public boolean isMoveValid(int x, int y) {
		if(x < 0 || y < 0 || x > board.getBoard()[0].length - 1 ||
				y > board.getBoard().length) {
			return false;
		}
		
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
	}


}
