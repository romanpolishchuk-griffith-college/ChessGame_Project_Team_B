package griffith;

// Represents a pawn.
public class Pawn extends ChessPiece {

    // Constructor with specified board and color.
    public Pawn(Board board, boolean isWhite) {

        super(board, isWhite, "Pawn");
        this.isWhite = isWhite;

        // Load the appropriate knight image
        String imageName = isWhite ? "w - pawn.png" : "b - pawn.png";
        loadImage(imageName);

    }

    // Constructor with specified board.
    public Pawn(Board board) {
        super(board);
    }

    @Override
    public String getValidMoves() {

        String moves = "";
        int pawnX = -1;
        int pawnY = -1;

        // Loop through the board
        for (int y = 0; y < board.getBoard().length; y++) {

            // Loop through the board
            for (int x = 0; x < board.getBoard()[y].length; x++) {

                // If the piece is the pawn
                if (board.getPiece(x, y) == this) {
                    pawnX = x;
                    pawnY = y;
                    break;
                }
            }
        }
        // The offset of the pawn
        int pawnOffset;

        // If the pawn is white
        if (isWhite) {
            // Set the offset to 1
            pawnOffset = 1;
        }
        // If the pawn is black
        else {
            pawnOffset = -1;
        }

        // If the pawn can move forward
        if (board.getPiece(pawnX, pawnY + pawnOffset) == null &&
        		pawnY + pawnOffset < board.getBoard().length &&
        		pawnY + pawnOffset > 0) {
            moves += pawnX + "," + (pawnY + pawnOffset) + " ";
        }
        // If the pawn is white and can move forward two squares
        if (isWhite &&
                pawnY == 1 &&
                board.getPiece(pawnX, pawnY + pawnOffset) == null &&
                board.getPiece(pawnX, pawnY + pawnOffset * 2) == null) {
            // Add the move to the moves string
            moves += pawnX + "," + (pawnY + pawnOffset * 2) + " ";
        }
        // If the pawn is black and can move forward two squares
        if (!isWhite &&
                pawnY == 6 &&
                board.getPiece(pawnX, pawnY + pawnOffset) == null &&
                board.getPiece(pawnX, pawnY + pawnOffset * 2) == null) {
            // Add the move to the moves string
            moves += pawnX + "," + (pawnY + pawnOffset * 2) + " ";
        }
        // If the pawn can capture a piece to the right
        if (board.getPiece(pawnX + 1, pawnY + pawnOffset) != null &&
                board.getPiece(pawnX + 1, pawnY + pawnOffset).isWhite != isWhite) {
            // Add the move to the moves string
            moves += (pawnX + 1) + "," + (pawnY + pawnOffset) + " ";
        }
        // If the pawn can capture a piece to the left
        if (board.getPiece(pawnX - 1, pawnY + pawnOffset) != null &&
                board.getPiece(pawnX - 1, pawnY + pawnOffset).isWhite != isWhite) {
            // Add the move to the moves string
            moves += (pawnX - 1) + "," + (pawnY + pawnOffset) + " ";
        }
        // Return the moves string
        return moves.trim();
    }

    // Returns true if the move is valid for the pawn.
    @Override
    public boolean isMoveValid(int x, int y) {
		if(x < 0 || y < 0 || x > board.getBoard()[0].length - 1 ||
				y > board.getBoard().length) {
			return false;
		}

        String validMoves = getValidMoves();

        // Get the target move
        String targetMove = x + "," + y;

        // Return if the move is valid
        return validMoves.contains(targetMove);
    }
}
