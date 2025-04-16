package griffith;

public class Queen extends ChessPiece {

    // Constructor with specified board and color.
    public Queen(Board board, boolean isWhite) {

        // Call the super constructor
        super(board, isWhite, "Queen");

        // Load the appropriate knight image
        String imageName = isWhite ? "w - queen.png" : "b - queen.png";
        loadImage(imageName);

    }

    // Constructor with specified board.
    public Queen(Board board) {
        super(board);
    }

    // Returns the valid moves for the queen.
    @Override
    public String getValidMoves() {

        // The moves for the queen
        String moves = "";

        // The x position of the queen
        int queenX = -1;

        // The y position of the queen
        int queenY = -1;

        // Loop through the board
        for (int y = 0; y < board.getBoard().length; y++) {

            // Loop through the board
            for (int x = 0; x < board.getBoard()[y].length; x++) {

                // If the piece is the queen
                if (board.getPiece(x, y) == this) {

                    // Set the x position of the queen
                    queenX = x;

                    // Set the y position of the queen
                    queenY = y;


                    // Break the loop
                    break;
                }
            }
        }

        // Move Up
        for (int y = queenY + 1; y < board.getBoard().length; y++) {

            // If the piece is null
            if (board.getPiece(queenX, y) == null) {

                // Add the move to the moves string
                moves += queenX + "," + y + " ";

            } else if (board.getPiece(queenX, y).isWhite != isWhite) {

                // Add the move to the moves string
                moves += queenX + "," + y + " ";

                // Stop if can capture a piece
                break;
            }
            // If the piece is the same color
            else {

                // Break the loop
                break;
            }
        }

        // Move Down
        for (int y = queenY - 1; y >= 0; y--) {

            // If the piece is null
            if (board.getPiece(queenX, y) == null) {

                // Add the move to the moves string
                moves += queenX + "," + y + " ";
            } else if (board.getPiece(queenX, y).isWhite != isWhite) {

                // Add the move to the moves string
                moves += queenX + "," + y + " ";

                // Stop if can capture a piece
                break;
            } else {

                // The same color of a piece
                break;
            }
        }

        // Move Right
        for (int x = queenX + 1; x < board.getBoard()[0].length; x++) {

            // If the piece is null
            if (board.getPiece(x, queenY) == null) {

                // Add the move to the moves string
                moves += x + "," + queenY + " ";
            } else if (board.getPiece(x, queenY).isWhite != isWhite) {

                // Add the move to the moves string
                moves += x + "," + queenY + " ";

                // Stop if can capture a piece
                break;
            } else {
                // The same color of a piece
                break;
            }
        }

        // Move Left
        for (int x = queenX - 1; x >= 0; x--) {

            // If the piece is null
            if (board.getPiece(x, queenY) == null) {

                // Add the move to the moves string
                moves += x + "," + queenY + " ";
            } else if (board.getPiece(x, queenY).isWhite != isWhite) {
                moves += x + "," + queenY + " ";

                // Stop if can capture a piece
                break;
            } else {
                // The same color of a piece
                break;
            }
        }

        // Move diagonally up-right
        for (int x = queenX + 1, y = queenY + 1; x < board.getBoard()[0].length && y < board.getBoard().length; x++, y++) {
            // If the piece is null
            if (board.getPiece(x, y) == null) {
                // Add the move to the moves string
                moves += x + "," + y + " ";
            } else if (board.getPiece(x, y).isWhite != isWhite) {
                moves += x + "," + y + " ";
                // Stop if can capture a piece
                break;
            } else {
                // The same color of a piece
                break;
            }
        }

        // Move diagonally up-left
        for (int x = queenX - 1, y = queenY + 1; x >= 0 && y < board.getBoard().length; x--, y++) {

            if (board.getPiece(x, y) == null) {

                moves += x + "," + y + " ";

            } else if (board.getPiece(x, y).isWhite != isWhite) {

                moves += x + "," + y + " ";
                // Stop if can capture a piece
                break;

            } else {
                // The same color of a piece
                break;
            }
        }

        // Move diagonally down-right
        for (int x = queenX + 1, y = queenY - 1; x < board.getBoard()[0].length && y >= 0; x++, y--) {

            if (board.getPiece(x, y) == null) {
                moves += x + "," + y + " ";

            } else if (board.getPiece(x, y).isWhite != isWhite) {

                moves += x + "," + y + " ";
                // Stop if can capture a piece
                break;
            } else {
                // The same color of a piece
                break;
            }
        }

        // Move diagonally down-left
        for (int x = queenX - 1, y = queenY - 1; x >= 0 && y >= 0; x--, y--) {

            if (board.getPiece(x, y) == null) {

                moves += x + "," + y + " ";

            } else if (board.getPiece(x, y).isWhite != isWhite) {

                moves += x + "," + y + " ";
                // Stop if can capture a piece
                break;

            } else {
                // The same color of a piece
                break;
            }
        }

        return moves.trim();
    }

    // Returns true if the move is valid for the queen.
	public boolean isMoveValid(int x, int y) {
		if(x < 0 || y < 0 || x > board.getBoard()[0].length - 1 ||
				y > board.getBoard().length) {
			return false;
		}

        String validMoves = getValidMoves();

        // Get the target move
        String targetMove = x + "," + y;

        return validMoves.contains(targetMove);
    }


}
