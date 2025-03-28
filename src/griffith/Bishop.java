package griffith;

public class Bishop extends ChessPiece {
    Bishop(Board board) {
        super(board);
    }
    
    @Override
	public String getValidMoves() {
		String moves = "";
        int bishopX = -1;
        int bishopY = -1;

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
            } else {
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
        }

        // Move diagonally up-left
        for (int x = bishopX - 1, y = bishopY + 1; x >= 0 && y < board.getBoard().length; x--, y++) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else {
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
        }

        // Move diagonally down-right
        for (int x = bishopX + 1, y = bishopY - 1; x < board.getBoard()[0].length && y >= 0; x++, y--) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else {
            	moves += x + "," + y + " ";
            	// Stop if can capture a piece
                break;
            }
        }

        // Move diagonally down-left
        for (int x = bishopX - 1, y = bishopY - 1; x >= 0 && y >= 0; x--, y--) {
            if (board.getPiece(x, y) == null) {
            	moves += x + "," + y + " ";
            } else {
            	moves += x + "," + y + " ";
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

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
