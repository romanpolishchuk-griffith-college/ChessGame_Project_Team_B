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