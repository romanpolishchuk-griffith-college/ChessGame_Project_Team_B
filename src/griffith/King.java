package griffith;

// Represents a king.
public class King extends ChessPiece {

    // Constructor with specified board and color.
    public King(Board board, boolean isWhite) {

        super(board, isWhite, "King");
        this.isWhite = isWhite;

        // Load the appropriate knight image
        String imageName = isWhite ? "w - king.png" : "b - king.png";
        loadImage(imageName);
    }

    // Constructor with specified board.
    public King(Board board) {
        super(board);
    }

    // Returns the valid moves for the king.
    @Override
    public String getValidMoves() {

        // The moves for the king
        String moves = "";

        // The x position of the king
        int kingX = -1;

        // The y position of the king
        int kingY = -1;

        // Loop through the board
        for (int y = 0; y < board.getBoard().length; y++) {

            // Loop through the board
            for (int x = 0; x < board.getBoard()[y].length; x++) {

                // If the piece is the king
                if (board.getPiece(x, y) == this) {

                    // Set the x position of the king
                    kingX = x;

                    // Set the y position of the king
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

    // Returns true if the move is valid for the king.
    @Override
    public boolean isMoveValid(int x, int y) {
        // Check if the move is within bounds
        if (x < 0 || y < 0 || x >= board.getBoard()[0].length || y >= board.getBoard().length) {
            return false;
        }

        // Get the valid moves for the king
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;

        // If the move is not in the valid moves list, return false
        if (!validMoves.contains(targetMove)) {
            return false;
        }

        // Temporarily move the king to the target position
        ChessPiece originalPiece = board.getPiece(x, y);
        int currentX = getX();
        int currentY = getY();
        board.setPiece(currentX, currentY, null);
        board.setPiece(x, y, this);

        // Check if the king would be in check at the target position
        boolean isInCheck = board.isSquareUnderAttack(x, y, !isWhite) != null;

        // Revert the move
        board.setPiece(x, y, originalPiece);
        board.setPiece(currentX, currentY, this);

        // Return false if the king would be in check, otherwise true
        return !isInCheck;
    }
}