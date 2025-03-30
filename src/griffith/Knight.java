package griffith;

public class Knight extends ChessPiece {

public Knight(Board board, boolean isWhite) {
	super(board);
    this.isWhite = isWhite;
    // Load the appropriate knight image
    String imageName = isWhite ? "w - knight.png" : "b - knight.png";
    loadImage(imageName);
    }
   
public Knight(Board board) {
	super(board);
    }

	@Override
	public String getValidMoves() {
		String moves = "";
        int knightX = -1;
        int knightY = -1;

        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                	knightX = x;
                	knightY = y;
                    break;
                }
            }
        }
        
        if (knightX - 2 < board.getBoard()[0].length && knightX - 2 >= 0
        		&& knightY + 1 < board.getBoard().length && knightY + 1 >= 0) {
            moves += (knightX - 2) + "," + (knightY + 1) + " ";
        }
        
        if (knightX - 1 < board.getBoard()[0].length && knightX - 1 >= 0
        		&& knightY + 2 < board.getBoard().length && knightY + 2 >= 0) {
            moves += (knightX - 1) + "," + (knightY + 2) + " ";
        }
        
        if (knightX + 1 < board.getBoard()[0].length && knightX + 1 >= 0
        		&& knightY + 2 < board.getBoard().length && knightY + 2 >= 0) {
            moves += (knightX + 1) + "," + (knightY + 2) + " ";
        }
        
        if (knightX + 2 < board.getBoard()[0].length && knightX + 2 >= 0
        		&& knightY + 1 < board.getBoard().length && knightY + 1 >= 0) {
            moves += (knightX + 2) + "," + (knightY + 1) + " ";
        }
        
        if (knightX + 2 < board.getBoard()[0].length && knightX + 2 >= 0
        		&& knightY - 1 < board.getBoard().length && knightY - 1 >= 0) {
            moves += (knightX + 2) + "," + (knightY - 1) + " ";
        }
        
        if (knightX + 1 < board.getBoard()[0].length && knightX + 1 >= 0
        		&& knightY - 2 < board.getBoard().length && knightY - 2 >= 0) {
            moves += (knightX + 1) + "," + (knightY - 2) + " ";
        }
        
        if (knightX - 1 < board.getBoard()[0].length && knightX - 1 >= 0
        		&& knightY - 2 < board.getBoard().length && knightY - 2 >= 0) {
            moves += (knightX - 1) + "," + (knightY - 2) + " ";
        }
        
        if (knightX - 2 < board.getBoard()[0].length && knightX - 2 >= 0
        		&& knightY - 1 < board.getBoard().length && knightY - 1 >= 0) {
            moves += (knightX - 2) + "," + (knightY - 1) + " ";
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
