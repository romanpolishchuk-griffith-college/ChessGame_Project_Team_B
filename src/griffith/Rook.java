package griffith;

public class Rook extends ChessPiece {
    Rook(Board board) {
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
        int rookX = -1;
        int rookY = -1;

        for (int yi = 0; yi < board.getBoard().length; yi++) {
            for (int xi = 0; xi < board.getBoard()[y].length; xi++) {
                if (board.getPiece(xi, yi) == this) {
                    rookX = xi;
                    rookY = yi;
                    break;
                }
            }
        }

        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}
