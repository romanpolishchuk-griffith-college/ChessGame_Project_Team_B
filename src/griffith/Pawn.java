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

        // Loop through the board to find the pawn's position
        for (int y = 0; y < board.getBoard().length; y++) {
            for (int x = 0; x < board.getBoard()[y].length; x++) {
                if (board.getPiece(x, y) == this) {
                    pawnX = x;
                    pawnY = y;
                    break;
                }
            }
        }

        int pawnOffset = isWhite ? 1 : -1;

        // Normal forward move
        if (board.getPiece(pawnX, pawnY + pawnOffset) == null &&
                pawnY + pawnOffset < board.getBoard().length &&
                pawnY + pawnOffset >= 0) {
            moves += pawnX + "," + (pawnY + pawnOffset) + " ";
        }

        // Double forward move from starting position
        if (isWhite && pawnY == 1 && board.getPiece(pawnX, pawnY + pawnOffset) == null &&
                board.getPiece(pawnX, pawnY + pawnOffset * 2) == null) {
            moves += pawnX + "," + (pawnY + pawnOffset * 2) + " ";
        }
        if (!isWhite && pawnY == 6 && board.getPiece(pawnX, pawnY + pawnOffset) == null &&
                board.getPiece(pawnX, pawnY + pawnOffset * 2) == null) {
            moves += pawnX + "," + (pawnY + pawnOffset * 2) + " ";
        }

        // Capture moves
        if (board.getPiece(pawnX + 1, pawnY + pawnOffset) != null &&
                board.getPiece(pawnX + 1, pawnY + pawnOffset).isWhite != isWhite) {
            moves += (pawnX + 1) + "," + (pawnY + pawnOffset) + " ";
        }
        if (board.getPiece(pawnX - 1, pawnY + pawnOffset) != null &&
                board.getPiece(pawnX - 1, pawnY + pawnOffset).isWhite != isWhite) {
            moves += (pawnX - 1) + "," + (pawnY + pawnOffset) + " ";
        }

        // En Passant
        if (pawnY == (isWhite ? 4 : 3)) { // Check if the pawn is in the correct row for En Passant
            ChessPiece leftPiece = board.getPiece(pawnX - 1, pawnY);
            ChessPiece rightPiece = board.getPiece(pawnX + 1, pawnY);

            if (leftPiece instanceof Pawn && leftPiece.isWhite != isWhite &&
                    board.getLastMovedPiece() == leftPiece &&
                    Math.abs(board.getLastMoveStartY() - board.getLastMoveEndY()) == 2) {
                moves += (pawnX - 1) + "," + (pawnY + pawnOffset) + " ";
            }

            if (rightPiece instanceof Pawn && rightPiece.isWhite != isWhite &&
                    board.getLastMovedPiece() == rightPiece &&
                    Math.abs(board.getLastMoveStartY() - board.getLastMoveEndY()) == 2) {
                moves += (pawnX + 1) + "," + (pawnY + pawnOffset) + " ";
            }
        }

        return moves.trim();
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        if (x < 0 || y < 0 || x >= board.getBoard()[0].length || y >= board.getBoard().length) {
            return false;
        }
    
        String validMoves = getValidMoves();
        String targetMove = x + "," + y;
    
        // Handle En Passant capture
        if (validMoves.contains(targetMove)) {
            if (Math.abs(x - getX()) == 1 && board.getPiece(x, y) == null) {
                int capturedPawnY = isWhite ? y - 1 : y + 1;
                ChessPiece capturedPawn = board.getPiece(x, capturedPawnY);
                if (capturedPawn instanceof Pawn && capturedPawn.isWhite != isWhite) {
                    board.addCapturedPiece(capturedPawn);
                    board.setPiece(x, capturedPawnY, null); // Remove the captured pawn
                }
            }
            return true;
        }
    
        return false;
    }
}
