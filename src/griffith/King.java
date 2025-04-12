package griffith;

public class King extends ChessPiece {
	
	public King(Board board, boolean isWhite) {
		 super(board);
	        this.isWhite = isWhite;
	        
	        // Load the appropriate knight image
	        String imageName = isWhite ? "w - king.png" : "b - king.png";
	        loadImage(imageName);
	   
	}
	
	public King(Board board) {
		 super(board);
	 
	}

	@Override
	public String getValidMoves() {
		String moves = "";
        int kingX = -1;
        int kingY = -1;

        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                	kingX = x;
                	kingY = y;
                    break;
                }
            }
        }
        
        if (kingX < board.getBoard()[0].length && kingX >= 0
        		&& kingY + 1 < board.getBoard().length && kingY + 1 >= 0 &&
        		(board.getPiece(kingX, kingY + 1) == null ||
        		(board.getPiece(kingX, kingY + 1) != null && 
        		board.getPiece(kingX, kingY + 1).isWhite != isWhite)))
        		{
            moves += kingX + "," + (kingY + 1) + " ";
        }
        
        if (kingX < board.getBoard()[0].length && kingX >= 0
        		&& kingY - 1 < board.getBoard().length && kingY - 1 >= 0 &&
                		(board.getPiece(kingX, kingY - 1) == null ||
                		(board.getPiece(kingX, kingY - 1) != null && 
                		board.getPiece(kingX, kingY - 1).isWhite != isWhite))) {
            moves += kingX + "," + (kingY - 1) + " ";
        }
        
        if (kingX + 1 < board.getBoard()[0].length && kingX + 1 >= 0
        		&& kingY < board.getBoard().length && kingY >= 0 &&
                		(board.getPiece(kingX + 1, kingY) == null ||
                		(board.getPiece(kingX + 1, kingY) != null && 
                		board.getPiece(kingX + 1, kingY).isWhite != isWhite))) {
            moves += (kingX + 1) + "," + kingY + " ";
        }
        
        if (kingX - 1 < board.getBoard()[0].length && kingX - 1 >= 0
        		&& kingY < board.getBoard().length && kingY >= 0 &&
                		(board.getPiece(kingX - 1, kingY) == null ||
                		(board.getPiece(kingX - 1, kingY) != null && 
                		board.getPiece(kingX - 1, kingY).isWhite != isWhite))) {
            moves += (kingX - 1) + "," + kingY + " ";
        }
        
        if (kingX + 1 < board.getBoard()[0].length && kingX + 1 >= 0
        		&& kingY + 1 < board.getBoard().length && kingY + 1 >= 0 &&
                		(board.getPiece(kingX + 1, kingY + 1) == null ||
                		(board.getPiece(kingX + 1, kingY + 1) != null && 
                		board.getPiece(kingX + 1, kingY + 1).isWhite != isWhite))) {
            moves += (kingX + 1) + "," + (kingY + 1) + " ";
        }
        
        if (kingX - 1 < board.getBoard()[0].length && kingX - 1 >= 0
        		&& kingY + 1 < board.getBoard().length && kingY + 1>= 0 &&
                		(board.getPiece(kingX - 1, kingY + 1) == null ||
                		(board.getPiece(kingX - 1, kingY + 1) != null && 
                		board.getPiece(kingX - 1, kingY + 1).isWhite != isWhite))) {
            moves += (kingX - 1) + "," + (kingY + 1) + " ";
        }
        
        if (kingX + 1 < board.getBoard()[0].length && kingX + 1 >= 0
        		&& kingY - 1 < board.getBoard().length && kingY - 1 >= 0 &&
                		(board.getPiece(kingX + 1, kingY - 1) == null ||
                		(board.getPiece(kingX + 1, kingY - 1) != null && 
                		board.getPiece(kingX + 1, kingY - 1).isWhite != isWhite))) {
            moves += (kingX + 1) + "," + (kingY - 1) + " ";
        }
        
        if (kingX - 1 < board.getBoard()[0].length && kingX - 1 >= 0
        		&& kingY - 1 < board.getBoard().length && kingY - 1 >= 0 &&
                		(board.getPiece(kingX - 1, kingY - 1) == null ||
                		(board.getPiece(kingX - 1, kingY - 1) != null && 
                		board.getPiece(kingX - 1, kingY - 1).isWhite != isWhite))) {
            moves += (kingX - 1) + "," + (kingY - 1) + " ";
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
