package griffith;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Board extends JPanel {

    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 80;

    private java.util.List<ChessPiece> capturedWhitePieces = new java.util.ArrayList<>();
    private java.util.List<ChessPiece> capturedBlackPieces = new java.util.ArrayList<>();
    private boolean gameOver = false;

    private JFrame window;

    private ChessPiece[][] board = {
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null}
    };

    
    // Constructors
    public Board(boolean initializePieces) {
        setPreferredSize(new java.awt.Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        if (initializePieces) {
            initializePieces();
        }
    }

    public Board() {
    }

    public void movePiece(ChessPiece piece, int newX, int newY) {
        int pieceX = -1;
        int pieceY = -1;
    
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (getPiece(x, y) == piece) {
                    pieceX = x;
                    pieceY = y;
                    break;
                }
            }
        }
    
      
        Renderer.updateGameStats();
    
        // Get the piece at the new position
        ChessPiece targetPiece = getPiece(newX, newY);
    
        // If a piece is captured
        if (targetPiece != null && targetPiece.isWhite != piece.isWhite) {
            addCapturedPiece(targetPiece);
    
            // Remove the captured piece's button from the window
            if (targetPiece.button != null && window != null) {
                window.remove(targetPiece.button);
                window.revalidate();
                window.repaint();
            }
        }
    
        // Check if the captured piece is a king
        if (targetPiece instanceof King) {
            String winner = piece.isWhite() ? "White" : "Black";
            JOptionPane.showMessageDialog(window, winner + " wins!");
            gameOver = true; // End the game
        }
    
        // Remove the piece from its old position
        setPiece(pieceX, pieceY, null);
    
        // Move the piece to the new position
        setPiece(newX, newY, piece);
    }

    // Initializes the pieces on the board.
    public void initializePieces() {
        // White pieces
        for (int col = 0; col < BOARD_SIZE; col++) {
            setPiece(col, 1, new Pawn(this, true));
        }

        setPiece(0, 0, new Rook(this, true));
        setPiece(7, 0, new Rook(this, true));

        setPiece(1, 0, new Knight(this, true));
        setPiece(6, 0, new Knight(this, true));

        setPiece(2, 0, new Bishop(this, true));
        setPiece(5, 0, new Bishop(this, true));

        setPiece(3, 0, new Queen(this, true));

        setPiece(4, 0, new King(this, true));

        //   Black pieces
        for (int col = 0; col < BOARD_SIZE; col++) {
            setPiece(col, 6, new Pawn(this, false));
        }

        setPiece(0, 7, new Rook(this, false));
        setPiece(7, 7, new Rook(this, false));

        setPiece(1, 7, new Knight(this, false));
        setPiece(6, 7, new Knight(this, false));

        setPiece(2, 7, new Bishop(this, false));
        setPiece(5, 7, new Bishop(this, false));

        setPiece(3, 7, new Queen(this, false));

        setPiece(4, 7, new King(this, false));
    }

    // Draws the board and its pieces.
    public void draw(JFrame window) {
        BoardPanel boardPanel = new BoardPanel();
        // Set the window for the board
        this.window = window;
        // Draw the pieces on the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                // Draw the piece if it exists
                if (getPiece(i, j) != null) {
                    getPiece(i, j).draw(window);
                }

            }
        }
        // Add the board to the window
        window.add(boardPanel);
        // Refresh the window
        window.revalidate();
        window.repaint();

    }

    //Method to manage captured pieces
    public void addCapturedPiece(ChessPiece piece) {
        if (piece.isWhite) {
            capturedWhitePieces.add(piece);
        } else {
            capturedBlackPieces.add(piece);
        }
    }


    // Boolean flags
    public ChessPiece isSquareUnderAttack(int squareX, int squareY, boolean isEnemyWhite) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (getPiece(x, y) != null && getPiece(x, y).isWhite == isEnemyWhite
                        && getPiece(x, y).isMoveValid(squareX, squareY)) {
                    return getPiece(x, y);
                }
            }
        }
        return null;
    }

    public boolean isWhiteWon() {
        int kingX = 0;
        int kingY = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (getPiece(x, y) instanceof King && !getPiece(x, y).isWhite) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }

        if (isSquareUnderAttack(kingX, kingY, true) == null) {
            return false;
        }

        if (kingY + 1 < BOARD_SIZE &&
                getPiece(kingX, kingY + 1) == null &&
                isSquareUnderAttack(kingX, kingY + 1, true) == null) {
            return false;
        }

        if (kingY - 1 >= 0 &&
                getPiece(kingX, kingY - 1) == null &&
                isSquareUnderAttack(kingX, kingY - 1, true) == null) {
            return false;
        }

        if (kingX + 1 < BOARD_SIZE &&
                getPiece(kingX + 1, kingY) == null &&
                isSquareUnderAttack(kingX + 1, kingY, true) == null) {
            return false;
        }

        if (kingX - 1 >= 0 &&
                getPiece(kingX - 1, kingY) == null &&
                isSquareUnderAttack(kingX - 1, kingY, true) == null) {
            return false;
        }

        if (kingX - 1 >= 0 &&
                kingY - 1 >= 0 &&
                getPiece(kingX - 1, kingY - 1) == null &&
                isSquareUnderAttack(kingX - 1, kingY - 1, true) == null) {
            return false;
        }

        if (kingX - 1 >= 0 &&
                kingY + 1 < BOARD_SIZE &&
                getPiece(kingX - 1, kingY + 1) == null &&
                isSquareUnderAttack(kingX - 1, kingY + 1, true) == null) {
            return false;
        }

        if (kingX + 1 < BOARD_SIZE &&
                kingY - 1 >= 0 &&
                getPiece(kingX + 1, kingY - 1) == null &&
                isSquareUnderAttack(kingX + 1, kingY - 1, true) == null) {
            return false;
        }

        if (kingX + 1 < BOARD_SIZE &&
                kingY + 1 < BOARD_SIZE &&
                getPiece(kingX + 1, kingY + 1) == null &&
                isSquareUnderAttack(kingX + 1, kingY + 1, true) == null) {
            return false;
        }

        ChessPiece attackingPiece = isSquareUnderAttack(kingX, kingY, true);

        int attackingPieceX = attackingPiece.getX();
        int attackingPieceY = attackingPiece.getY();

        //String[] validMovesOFAttackingPiece = (attackingPiece.getValidMoves() + " " + attackingPieceX + "," + attackingPieceY).split(" ");
        String[] validMovesOfAttackingPiece = (attackingPieceX + "," + attackingPieceY).split(" ");
        
        for (int i = 0; i < validMovesOfAttackingPiece.length; i++) {
            if (isSquareUnderAttack(Integer.parseInt(validMovesOfAttackingPiece[i].charAt(0) + ""), Integer.parseInt(validMovesOfAttackingPiece[i].charAt(2) + ""), false) != null) {
                return false;
            }
        }

        return true;
    }

    public boolean isBlackWon() {
        int kingX = 0;
        int kingY = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (getPiece(x, y) instanceof King && getPiece(x, y).isWhite) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }

        if (isSquareUnderAttack(kingX, kingY, false) == null) {
            return false;
        }

        if (kingY + 1 < BOARD_SIZE &&
                getPiece(kingX, kingY + 1) == null &&
                isSquareUnderAttack(kingX, kingY + 1, false) == null) {
            return false;
        }

        if (kingY - 1 >= 0 &&
                getPiece(kingX, kingY - 1) == null &&
                isSquareUnderAttack(kingX, kingY - 1, false) == null) {
            return false;
        }

        if (kingX + 1 < BOARD_SIZE &&
                getPiece(kingX + 1, kingY) == null &&
                isSquareUnderAttack(kingX + 1, kingY, false) == null) {
            return false;
        }

        if (kingX - 1 >= 0 &&
                getPiece(kingX - 1, kingY) == null &&
                isSquareUnderAttack(kingX - 1, kingY, false) == null) {
            return false;
        }

        if (kingX - 1 >= 0 &&
                kingY - 1 >= 0 &&
                getPiece(kingX - 1, kingY - 1) == null &&
                isSquareUnderAttack(kingX - 1, kingY - 1, false) == null) {
            return false;
        }

        if (kingX - 1 >= 0 &&
                kingY + 1 < BOARD_SIZE &&
                getPiece(kingX - 1, kingY + 1) == null &&
                isSquareUnderAttack(kingX - 1, kingY + 1, false) == null) {
            return false;
        }

        if (kingX + 1 < BOARD_SIZE &&
                kingY - 1 >= 0 &&
                getPiece(kingX + 1, kingY - 1) == null &&
                isSquareUnderAttack(kingX + 1, kingY - 1, false) == null) {
            return false;
        }

        if (kingX + 1 < BOARD_SIZE &&
                kingY + 1 < BOARD_SIZE &&
                getPiece(kingX + 1, kingY + 1) == null &&
                isSquareUnderAttack(kingX + 1, kingY + 1, false) == null) {
            return false;
        }

        ChessPiece attackingPiece = isSquareUnderAttack(kingX, kingY, false);

        int attackingPieceX = attackingPiece.getX();
        int attackingPieceY = attackingPiece.getY();

        //String[] validMovesOFAttackingPiece = (attackingPiece.getValidMoves() + " " + attackingPieceX + "," + attackingPieceY).split(" ");
        String[] validMovesOfAttackingPiece = (attackingPieceX + "," + attackingPieceY).split(" ");
        
        for (int i = 0; i < validMovesOfAttackingPiece.length; i++) {
            if (isSquareUnderAttack(Integer.parseInt(validMovesOfAttackingPiece[i].charAt(0) + ""), Integer.parseInt(validMovesOfAttackingPiece[i].charAt(2) + ""), true) != null) {
                return false;
            }
        }

        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isUnderCheck(boolean isCheckForWhite) {
        int kingX = 0;
        int kingY = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (getPiece(x, y) instanceof King && getPiece(x, y).isWhite == isCheckForWhite) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }

        if(isCheckForWhite && !isBlackWon() && isSquareUnderAttack(kingX, kingY, false) != null) {
            return true;
        }

        if(!isCheckForWhite && !isWhiteWon() && isSquareUnderAttack(kingX, kingY, true) != null) {
            return true;
        }

        return false;
    }


    // Setters
    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }

    public void setPiece(int x, int y, ChessPiece piece) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        board[board.length - 1 - y][x] = piece;
    }


    // Getters
    public ArrayList<Map<ChessPiece, String>> getValidMovesUnderCheck(boolean forWhitePlayer){
        ArrayList<Map<ChessPiece, String>> validMoves = new ArrayList<>();

        int kingX = 0;
        int kingY = 0;

        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (getPiece(x, y) instanceof King && getPiece(x, y).isWhite == forWhitePlayer) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }

        if (kingY + 1 < BOARD_SIZE &&
                getPiece(kingX, kingY + 1) == null &&
                isSquareUnderAttack(kingX, kingY + 1, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), kingX + "," + (kingY + 1));
            validMoves.add(move);
        }

        if (kingY - 1 >= 0 &&
                getPiece(kingX, kingY - 1) == null &&
                isSquareUnderAttack(kingX, kingY - 1, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), kingX + "," + (kingY - 1));
            validMoves.add(move);
        }

        if (kingX + 1 < BOARD_SIZE &&
                getPiece(kingX + 1, kingY) == null &&
                isSquareUnderAttack(kingX + 1, kingY, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), (kingX + 1) + "," + kingY);
            validMoves.add(move);
        }

        if (kingX - 1 >= 0 &&
                getPiece(kingX - 1, kingY) == null &&
                isSquareUnderAttack(kingX - 1, kingY, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), (kingX - 1) + "," + kingY);
            validMoves.add(move);
        }

        if (kingX - 1 >= 0 &&
                kingY - 1 >= 0 &&
                getPiece(kingX - 1, kingY - 1) == null &&
            isSquareUnderAttack(kingX - 1, kingY - 1, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), (kingX - 1) + "," + (kingY - 1));
            validMoves.add(move);
        }

        if (kingX - 1 >= 0 &&
                kingY + 1 < BOARD_SIZE &&
                getPiece(kingX - 1, kingY + 1) == null &&
                isSquareUnderAttack(kingX - 1, kingY + 1, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), (kingX - 1) + "," + (kingY + 1));
            validMoves.add(move);
        }

        if (kingX + 1 < BOARD_SIZE &&
                kingY - 1 >= 0 &&
                getPiece(kingX + 1, kingY - 1) == null &&
                isSquareUnderAttack(kingX + 1, kingY - 1, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), (kingX + 1) + "," + (kingY - 1));
            validMoves.add(move);
        }

        if (kingX + 1 < BOARD_SIZE &&
                kingY + 1 < BOARD_SIZE &&
                getPiece(kingX + 1, kingY + 1) == null &&
                isSquareUnderAttack(kingX + 1, kingY + 1, !forWhitePlayer) == null) {
            Map<ChessPiece, String> move = new HashMap<>();
            move.put(getPiece(kingX, kingY), (kingX + 1) + "," + (kingY + 1));
            validMoves.add(move);
        }

        ChessPiece attackingPiece = isSquareUnderAttack(kingX, kingY, !forWhitePlayer);

        int attackingPieceX = attackingPiece.getX();
        int attackingPieceY = attackingPiece.getY();

        //String[] validMovesOfAttackingPiece = (attackingPiece.getValidMoves() + attackingPieceX + "," + attackingPieceY).split(" ");
        String[] validMovesOfAttackingPiece = (attackingPieceX + "," + attackingPieceY).split(" ");
        
        
        for (int i = 0; i < validMovesOfAttackingPiece.length; i++) {
            ChessPiece defendingPiece = isSquareUnderAttack(Integer.parseInt(validMovesOfAttackingPiece[i].charAt(0) + ""), Integer.parseInt(validMovesOfAttackingPiece[i].charAt(2) + ""), forWhitePlayer);
            if (defendingPiece != null) {
                Map<ChessPiece, String> move = new HashMap<>();
                move.put(defendingPiece, validMovesOfAttackingPiece[i].charAt(0) + "," + validMovesOfAttackingPiece[i].charAt(2));
                validMoves.add(move);
            }
        }

        return validMoves;
    }

    public Color getSquareColor(int row, int column) {
        // Check if the sum of the row and column is even
        if ((row + column) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }

    }

    public int getSquareSize() {
        return SQUARE_SIZE;
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public int getBoredSize() {
        return BOARD_SIZE;
    }

    public ChessPiece getPiece(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return null;
        }
        return board[board.length - 1 - y][x];
    }

    public java.util.List<ChessPiece> getCapturedWhitePieces() {
        return capturedWhitePieces;
    }

    public java.util.List<ChessPiece> getCapturedBlackPieces() {
        return capturedBlackPieces;
    }

}