package griffith;

// Represents a knight.
public class Knight extends ChessPiece {

    // Constructor with specified board and color.
    public Knight(Board board, boolean isWhite) {

        super(board, isWhite, "Knight");
        this.isWhite = isWhite;

        // Load the appropriate knight image
        String imageName = isWhite ? "w - knight.png" : "b - knight.png";
        loadImage(imageName);
    }

    // Constructor with specified board.
    public Knight(Board board) {
        super(board);
    }

    @Override
    public String getValidMoves() {

        String moves = "";
        int knightX = -1;
        int knightY = -1;

        // Loop through the board
        for (int y = 0; y < board.getBoard().length; y++) {

            // Loop through the board
            for (int x = 0; x < board.getBoard()[y].length; x++) {

                // If the piece is the knight
                if (board.getPiece(x, y) == this) {

                    // Set the x position of the knight
                    knightX = x;

                    // Set the y position of the knight
                    knightY = y;

                    // Break the loop
                    break;
                }
            }
        }
        // Check if the move is valid
        if (knightX - 2 < board.getBoard()[0].length && knightX - 2 >= 0
        		&& knightY + 1 < board.getBoard().length && knightY + 1 >= 0 &&
                		(board.getPiece(knightX - 2, knightY + 1) == null ||
                		(board.getPiece(knightX - 2, knightY + 1) != null && 
                		board.getPiece(knightX - 2, knightY + 1).isWhite != isWhite))) {
            moves += (knightX - 2) + "," + (knightY + 1) + " ";
        }

        if (knightX - 1 < board.getBoard()[0].length && knightX - 1 >= 0
        		&& knightY + 2 < board.getBoard().length && knightY + 2 >= 0 &&
                		(board.getPiece(knightX - 1, knightY + 2) == null ||
                		(board.getPiece(knightX - 1, knightY + 2) != null && 
                		board.getPiece(knightX - 1, knightY + 2).isWhite != isWhite))) {
            moves += (knightX - 1) + "," + (knightY + 2) + " ";
        }

        if (knightX + 1 < board.getBoard()[0].length && knightX + 1 >= 0
        		&& knightY + 2 < board.getBoard().length && knightY + 2 >= 0 &&
                		(board.getPiece(knightX + 1, knightY + 2) == null ||
                		(board.getPiece(knightX + 1, knightY + 2) != null && 
                		board.getPiece(knightX + 1, knightY + 2).isWhite != isWhite))) {
            moves += (knightX + 1) + "," + (knightY + 2) + " ";
        }

        if (knightX + 2 < board.getBoard()[0].length && knightX + 2 >= 0
        		&& knightY + 1 < board.getBoard().length && knightY + 1 >= 0 &&
                		(board.getPiece(knightX + 2, knightY + 1) == null ||
                		(board.getPiece(knightX + 2, knightY + 1) != null && 
                		board.getPiece(knightX + 2, knightY + 1).isWhite != isWhite))) {
            moves += (knightX + 2) + "," + (knightY + 1) + " ";
        }

        if (knightX + 2 < board.getBoard()[0].length && knightX + 2 >= 0
        		&& knightY - 1 < board.getBoard().length && knightY - 1 >= 0 &&
                		(board.getPiece(knightX + 2, knightY - 1) == null ||
                		(board.getPiece(knightX + 2, knightY - 1) != null && 
                		board.getPiece(knightX + 2, knightY - 1).isWhite != isWhite))) {
            moves += (knightX + 2) + "," + (knightY - 1) + " ";
        }

        if (knightX + 1 < board.getBoard()[0].length && knightX + 1 >= 0
        		&& knightY - 2 < board.getBoard().length && knightY - 2 >= 0 &&
                		(board.getPiece(knightX + 1, knightY - 2) == null ||
                		(board.getPiece(knightX + 1, knightY - 2) != null && 
                		board.getPiece(knightX + 1, knightY - 2).isWhite != isWhite))) {
            moves += (knightX + 1) + "," + (knightY - 2) + " ";
        }

        if (knightX - 1 < board.getBoard()[0].length && knightX - 1 >= 0
        		&& knightY - 2 < board.getBoard().length && knightY - 2 >= 0 &&
                		(board.getPiece(knightX - 1, knightY - 2) == null ||
                		(board.getPiece(knightX - 1, knightY - 2) != null && 
                		board.getPiece(knightX - 1, knightY - 2).isWhite != isWhite))) {
            moves += (knightX - 1) + "," + (knightY - 2) + " ";
        }

        if (knightX - 2 < board.getBoard()[0].length && knightX - 2 >= 0
        		&& knightY - 1 < board.getBoard().length && knightY - 1 >= 0 &&
                		(board.getPiece(knightX - 2, knightY - 1) == null ||
                		(board.getPiece(knightX - 2, knightY - 1) != null && 
                		board.getPiece(knightX - 2, knightY - 1).isWhite != isWhite))) {
            moves += (knightX - 2) + "," + (knightY - 1) + " ";
        }

        return moves.trim();
    }

    // Returns true if the move is valid for the knight.
    @Override
    public boolean isMoveValid(int x, int y) {
		if(x < 0 || y < 0 || x > board.getBoard()[0].length - 1 ||
				y > board.getBoard().length) {
			return false;
		}

        // Get the valid moves for the knight
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
    }

}
