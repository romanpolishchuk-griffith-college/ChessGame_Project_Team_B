package griffith;

public class King extends ChessPiece {
    King(Board board) {
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
        		&& kingY + 1 < board.getBoard().length && kingY + 1 >= 0) {
            moves += kingX + "," + (kingY + 1) + " ";
        }
        
        if (kingX < board.getBoard()[0].length && kingX >= 0
        		&& kingY - 1 < board.getBoard().length && kingY - 1 >= 0) {
            moves += kingX + "," + (kingY - 1) + " ";
        }
        
        if (kingX + 1 < board.getBoard()[0].length && kingX + 1 >= 0
        		&& kingY < board.getBoard().length && kingY >= 0) {
            moves += (kingX + 1) + "," + kingY + " ";
        }
        
        if (kingX - 1 < board.getBoard()[0].length && kingX - 1 >= 0
        		&& kingY < board.getBoard().length && kingY >= 0) {
            moves += (kingX - 1) + "," + kingY + " ";
        }
        
        if (kingX + 1 < board.getBoard()[0].length && kingX + 1 >= 0
        		&& kingY + 1 < board.getBoard().length && kingY + 1 >= 0) {
            moves += (kingX + 1) + "," + (kingY + 1) + " ";
        }
        
        if (kingX - 1 < board.getBoard()[0].length && kingX - 1 >= 0
        		&& kingY + 1 < board.getBoard().length && kingY + 1>= 0) {
            moves += (kingX - 1) + "," + (kingY + 1) + " ";
        }
        
        if (kingX + 1 < board.getBoard()[0].length && kingX + 1 >= 0
        		&& kingY - 1 < board.getBoard().length && kingY - 1 >= 0) {
            moves += (kingX + 1) + "," + (kingY - 1) + " ";
        }
        
        if (kingX - 1 < board.getBoard()[0].length && kingX - 1 >= 0
        		&& kingY - 1 < board.getBoard().length && kingY - 1 >= 0) {
            moves += (kingX - 1) + "," + (kingY - 1) + " ";
        }
        
        return moves.trim();
	}

	@Override
	public boolean isMoveValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
