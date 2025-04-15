package griffith;

public class GameLogic {

    private static boolean isPlayerWhite = true; // Default to white

    //method to set the player's color
    public static void setPlayerColor(boolean isWhite) {
        isPlayerWhite = isWhite;
    }

    // method to get the player's color
    public static boolean isPlayerWhite() {
        return isPlayerWhite;
    }

    // Method to check if the computer is white
    public static boolean isComputerWhite() {
        return !isPlayerWhite; // Opposite of the player's color
    }

    // Checks if a move is valid
    public boolean isMoveValid(Board chessBoard, int startX, int startY, int endX, int endY, boolean isWhiteTurn) {
        // Get the piece at the start position
        ChessPiece piece = chessBoard.getPiece(startX, startY);

        // If the piece is null or the color of the piece is not the same as the current turn, return false
        if (piece == null || piece.isWhite() != isWhiteTurn) {
            return false; // No piece or wrong color
        }

        // Check if the move is valid for the piece
        return piece.isMoveValid(endX, endY);
    }

    // Executes a move 
    public boolean executeMove(Board board, int startX, int startY, int endX, int endY) {

        // Get the piece at the specified position
        ChessPiece piece = board.getPiece(startX, startY);

        // If the piece is not null, move the piece to the specified position
        if (piece != null) {

            board.movePiece(piece, endX, endY);
            return true;

        }

        return false;
    }

        public static int[] getComputerMove(Board board) {
        boolean computerColor = isComputerWhite(); // Get the computer's color

        // Example: Random valid move for the computer
        for (int row = 0; row < 8; row++) {


            for (int col = 0; col < 8; col++) {


                // Get the piece at the specified position
                ChessPiece piece = board.getPiece(row, col);

                // If the piece belongs to the computer, get its valid moves
                if (piece != null && piece.isWhite == computerColor) {
                    for (int targetRow = 0; targetRow < 8; targetRow++) {
                        for (int targetCol = 0; targetCol < 8; targetCol++) {

                            // If the move is valid, return the move
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

    // Checks if the game is over
    public boolean isGameOver(Board board) {

        // Example: Check if either king is missing
        // Whether the white king exists
        boolean whiteKingExists = false;

        // Whether the black king exists
        boolean blackKingExists = false;

        // Loop through the board
        for (int row = 0; row < 8; row++) {

            for (int col = 0; col < 8; col++) {

                // Get the piece at the specified position
                ChessPiece piece = board.getPiece(row, col);

                // If the piece is a king

                if (piece instanceof King) {

                    // If the piece is white, set the white king to true
                    if (piece.isWhite) {
                        whiteKingExists = true;
                    } else {
                        blackKingExists = true;
                    }
                }
            }
        }
        // Return if the game is over
        return !(whiteKingExists && blackKingExists);
    }
}
