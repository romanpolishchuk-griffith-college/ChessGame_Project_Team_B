package griffith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameLogic {

    private static boolean isPlayerWhite = true; // Default to white

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


    // Boolean flags
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

    public static boolean isPlayerWhite() {
        return isPlayerWhite;
    }

    public static boolean isComputerWhite() {
        return !isPlayerWhite; // Opposite of the player's color
    }

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


    // Getters
    public static int[] getComputerMove(Board board) {
        boolean computerColor = isComputerWhite(); // Get the computer's color

        ArrayList<Map<ChessPiece, String>> validMoves = new ArrayList<>();

        // Example: Random valid move for the computer
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Get the piece at the specified position
                ChessPiece piece = board.getPiece(row, col);

                // If the piece belongs to the computer, get its valid moves
                if (piece != null && piece.isWhite == computerColor &&
                        piece.getValidMoves() != "") {
                    Map<ChessPiece, String> moves = new HashMap<>();
                    moves.put(piece, piece.getValidMoves());

                    validMoves.add(moves);
                }
            }
        }

        // Filter out moves that would place the king in check
        ArrayList<int[]> filteredMoves = new ArrayList<>();
        for (Map<ChessPiece, String> moveMap : validMoves) {
            ChessPiece piece = moveMap.keySet().iterator().next();
            String[] moves = moveMap.get(piece).split(" ");
            for (String move : moves) {
                int targetX = Integer.parseInt(move.split(",")[0]);
                int targetY = Integer.parseInt(move.split(",")[1]);

                // Temporarily move the piece
                ChessPiece originalPiece = board.getPiece(targetX, targetY);
                int currentX = piece.getX();
                int currentY = piece.getY();
                board.setPiece(currentX, currentY, null);
                board.setPiece(targetX, targetY, piece);

                // Check if the king is in check
                boolean isInCheck = false;
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        ChessPiece opponentPiece = board.getPiece(row, col);
                        if (opponentPiece != null && opponentPiece.isWhite != computerColor &&
                                opponentPiece.isMoveValid(piece.getX(), piece.getY())) {
                            isInCheck = true;
                            break;
                        }
                    }
                    if (isInCheck) break;
                }

                // Revert the move
                board.setPiece(targetX, targetY, originalPiece);
                board.setPiece(currentX, currentY, piece);

                // Add the move if it doesn't place the king in check
                if (!isInCheck) {
                    filteredMoves.add(new int[]{currentX, currentY, targetX, targetY});
                }
            }
        }

        // If no valid moves are left, return null
        if (filteredMoves.isEmpty()) {
            return null;
        }

        // Choose a random move from the filtered list
        int[] chosenMove = filteredMoves.get((int) (Math.random() * filteredMoves.size()));
        return chosenMove;
    }


    //Setters
    public static void setPlayerColor(boolean isWhite) {
        isPlayerWhite = isWhite;
    }

}
