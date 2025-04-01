package griffith;

public class Pawn extends ChessPiece {
    
        @Override
        public String getValidMoves() {
            return "Forward 1";
        }
    
        @Override
        public boolean isMoveValid() {
            return true;
        }
    
        @Override
        public void draw() {
            System.out.println("Drawing Pawn");
        }
	
	public Pawn(Board board, boolean isWhite) {
		super(board);
	    this.isWhite = isWhite;
	    // Load the appropriate knight image
	    String imageName = isWhite ? "w - pawn.png" : "b - pawn.png";
	    loadImage(imageName);
	   
	}
	public Pawn(Board board) {
		super(board);
	
	}

	@Override
	public String getValidMoves() {
		String moves = "";
        int pawnX = -1;
        int pawnY = -1;

        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                	pawnX = x;
                	pawnY = y;
                    break;
                }
            }
        }
        
        int pawnOffset;
        if(isWhite) {
        	pawnOffset = 1;
        }
        else {
        	pawnOffset = -1;
        }
        
        if (board.getPiece(pawnX, pawnY + pawnOffset) == null) {
            moves += pawnX + "," + (pawnY + pawnOffset) + " ";
        }
        
        if (isWhite && 
        		pawnY == 1 &&
        		board.getPiece(pawnX, pawnY + pawnOffset) == null && 
        		board.getPiece(pawnX, pawnY + pawnOffset*2) == null) {
            moves += pawnX + "," + (pawnY + pawnOffset*2) + " ";
        }
        
        if (!isWhite && 
        		pawnY == 6 &&
        		board.getPiece(pawnX, pawnY + pawnOffset) == null && 
        		board.getPiece(pawnX, pawnY + pawnOffset*2) == null) {
            moves += pawnX + "," + (pawnY + pawnOffset*2) + " ";
        }
        
        if (board.getPiece(pawnX + 1, pawnY + pawnOffset) != null &&
        		board.getPiece(pawnX + 1, pawnY + pawnOffset).isWhite != isWhite) {
            moves += (pawnX + 1) + "," + (pawnY + pawnOffset) + " ";
        }
        
        if (board.getPiece(pawnX - 1, pawnY + pawnOffset) != null &&
        		board.getPiece(pawnX - 1, pawnY + pawnOffset).isWhite != isWhite) {
            moves += (pawnX - 1) + "," + (pawnY + pawnOffset) + " ";
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
