package griffith;

public class GameLogic {

    public boolean isMoveValid(Board chessBoard, int x, int y, boolean isWhiteTurn) {
        ChessPiece piece = chessBoard.getPiece(x, y);
        if (piece == null || piece.isWhite() != isWhiteTurn) {
            return false; // No piece or wrong color
        }
        return piece.isMoveValid(x, y);
    }

    public void executeMove(Board board, int startX, int startY, int endX, int endY) {
        ChessPiece piece = board.getPiece(startX, startY);
        if (piece != null) {
            board.movePiece(piece, endX, endY);
        }
    }

    public int[] getComputerMove(Board board) {
        // Example: Random valid move for the computer
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPiece(row, col);
                if (piece != null && !piece.isWhite()) { // Computer's pieces
                    for (int targetRow = 0; targetRow < 8; targetRow++) {
                        for (int targetCol = 0; targetCol < 8; targetCol++) {
                            if (piece.isMoveValid(targetRow, targetCol)) {
                                return new int[]{row, col, targetRow, targetCol};
                            }
                        }
                    }
                }
            }
        }
        return null; // No valid moves
    }

    public boolean isGameOver(Board board) {
        // Example: Check if either king is missing
        boolean whiteKingExists = false;
        boolean blackKingExists = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPiece(row, col);
                if (piece instanceof King) {
                    if (piece.isWhite()) {
                        whiteKingExists = true;
                    } else {
                        blackKingExists = true;
                    }
                }
            }
        }
        return !(whiteKingExists && blackKingExists);
    }
}
