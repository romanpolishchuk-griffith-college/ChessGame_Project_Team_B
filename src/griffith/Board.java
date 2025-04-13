package griffith;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Represents the game board for a chess game.
public class Board extends JPanel {
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 80;

    private JFrame window;
    private java.util.List<ChessPiece> capturedWhitePieces = new java.util.ArrayList<>();
    private java.util.List<ChessPiece> capturedBlackPieces = new java.util.ArrayList<>();
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

    // Constructor that initializes the board with pieces if specified.
    public Board(boolean initializePieces) {
        setPreferredSize(new java.awt.Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        if (initializePieces) {
            initializePieces();
        }
    }

    // Default constructor that creates a full game board.
    public Board() {
    }

    // Initializes the pieces on the board.
    public void initializePieces() {
        // Initialize White pieces
        // Pawns
        for (int col = 0; col < BOARD_SIZE; col++) {
            setPiece(col, 1, new Pawn(this, true));
        }
        // Rooks
        setPiece(0, 0, new Rook(this, true));
        setPiece(7, 0, new Rook(this, true));
        // Knights
        setPiece(1, 0, new Knight(this, true));
        setPiece(6, 0, new Knight(this, true));
        // Bishops
        setPiece(2, 0, new Bishop(this, true));
        setPiece(5, 0, new Bishop(this, true));
        // Queen
        setPiece(3, 0, new Queen(this, true));
        // King
        setPiece(4, 0, new King(this, true));

        //   Initialize Black pieces
        //   pawns
        for (int col = 0; col < BOARD_SIZE; col++) {
            setPiece(col, 6, new Pawn(this, false));
        }
        // Rooks
        setPiece(0, 7, new Rook(this, false));
        setPiece(7, 7, new Rook(this, false));
        // Knights
        setPiece(1, 7, new Knight(this, false));
        setPiece(6, 7, new Knight(this, false));
        // Bishops
        setPiece(2, 7, new Bishop(this, false));
        setPiece(5, 7, new Bishop(this, false));
        // Queen
        setPiece(3, 7, new Queen(this, false));
        // King
        setPiece(4, 7, new King(this, false));
    }

    // Returns the current board configuration.

    public ChessPiece[][] getBoard() {
        return board;
    }

    // Sets the board configuration to the specified array.
    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }

    // Returns the size of the board.
    public int getBoredSize() {
        return BOARD_SIZE;
    }

    // Returns the piece at the specified coordinates.
    public ChessPiece getPiece(int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return null;
        }
        return board[board.length - 1 - y][x];
    }

    // Sets the piece at the specified coordinates.
    public void setPiece(int x, int y, ChessPiece piece) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
            return;
        }
        board[board.length - 1 - y][x] = piece;
    }

    // Moves a piece from its current position to a new position.
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

        //Get whatever piece is at a new position
        ChessPiece targetPiece = getPiece(newX, newY);
        //Checking if a piece of the other side is on that position
        if (targetPiece != null && targetPiece.isWhite != piece.isWhite) {
            //Piece is added to captured panel if yes
            addCapturedPiece(targetPiece);
            //Verifying game window is not null
            if (window != null) {
                //Removing that chesss piece from the game window
                window.remove(targetPiece.button);
            }
        }

        //Remove piece from old place
        setPiece(pieceX, pieceY, null);
        //Move piece to new place
        setPiece(newX, newY, piece);
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

    // Returns the color of the square at the specified coordinates.
    public Color getSquareColor(int row, int column) {
        // Check if the sum of the row and column is even
        if ((row + column) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }

    }

    // Returns the size of the square on the board.
    public int getSquareSize() {
        return SQUARE_SIZE;
    }

    //Method to manage captured pieces
    public void addCapturedPiece(ChessPiece piece) {
        if (piece.isWhite) {
            capturedWhitePieces.add(piece);
        } else {
            capturedBlackPieces.add(piece);
        }
    }

    public java.util.List<ChessPiece> getCapturedWhitePieces() {
        return capturedWhitePieces;
    }

    public java.util.List<ChessPiece> getCapturedBlackPieces() {
        return capturedBlackPieces;
    }

}