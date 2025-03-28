package griffith;

public class Pawn extends ChessPiece {
    Pawn(Board board) {
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
        
        if (board.getPiece(pawnX, pawnY + 1) == null) {
            moves += pawnX + "," + (pawnY + 1) + " ";
        }
        
        if (board.getPiece(pawnX + 1, pawnY + 1) != null) {
            moves += (pawnX + 1) + "," + (pawnY + 1) + " ";
        }
        
        if (board.getPiece(pawnX - 1, pawnY + 1) != null) {
            moves += (pawnX - 1) + "," + (pawnY + 1) + " ";
        }
        
        return moves.trim();
	}

	@Override
	public boolean isMoveValid(int x, int y) {
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
