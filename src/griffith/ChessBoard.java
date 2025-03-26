package griffith;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends JPanel {

    private int rows;
    private int columns;
    private String[][] board;
    private String[][] boardCopy;
    private int[][] piecePositions;
    private int[][] piecePositionsCopy;
    private int[][] piecePositionsTemp;
    private int[] piecePosition;

    public ChessBoard() {
        this.rows = 8;
        this.columns = 8;
        this.board = new String[rows][columns];
        this.boardCopy = new String[rows][columns];
        this.piecePositions = new int[rows][columns];
        this.piecePositionsCopy = new int[rows][columns];
        this.piecePositionsTemp = new int[rows][columns];
        this.piecePosition = new int[2];
        this.initializeBoard();
        this.initializePiecePositions();
        this.setPreferredSize(new Dimension(800, 800));
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    private void initializePiecePositions() {
        // Initialize piece positions for a new game
        // 1 for white pieces, -1 for black pieces, 0 for empty
        for (int i = 0; i < columns; i++) {
            piecePositions[1][i] = 1; // White pawns
            piecePositions[6][i] = -1; // Black pawns
        }
        // Other pieces
        piecePositions[0][0] = piecePositions[0][7] = 1; // White rooks
        piecePositions[0][1] = piecePositions[0][6] = 1; // White knights
        piecePositions[0][2] = piecePositions[0][5] = 1; // White bishops
        piecePositions[0][3] = 1; // White queen
        piecePositions[0][4] = 1; // White king

        piecePositions[7][0] = piecePositions[7][7] = -1; // Black rooks
        piecePositions[7][1] = piecePositions[7][6] = -1; // Black knights
        piecePositions[7][2] = piecePositions[7][5] = -1; // Black bishops
        piecePositions[7][3] = -1; // Black queen
        piecePositions[7][4] = -1; // Black king
    }

    private void initializeBoard() {
        // Initialize the board with empty strings
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = "";
            }
        }
    }

    private void handleMouseClick(MouseEvent e) {
        // Handle mouse click for selecting and moving pieces
        int x = e.getX() / (getWidth() / columns);
        int y = e.getY() / (getHeight() / rows);
        System.out.println("Mouse clicked at: " + x + ", " + y);
        // Add logic for selecting and moving pieces
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);
    }

    private void drawBoard(Graphics g) {
        // Draw the chess board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i + j) % 2 == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(j * getWidth() / columns, i * getHeight() / rows, getWidth() / columns, getHeight() / rows);
            }
        }
    }

    private void drawPieces(Graphics g) {
        // Draw the pieces on the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (piecePositions[i][j] == 1) {
                    g.setColor(Color.WHITE);
                    g.fillOval(j * getWidth() / columns + 10, i * getHeight() / rows + 10, getWidth() / columns - 20, getHeight() / rows - 20);
                } else if (piecePositions[i][j] == -1) {
                    g.setColor(Color.BLACK);
                    g.fillOval(j * getWidth() / columns + 10, i * getHeight() / rows + 10, getWidth() / columns - 20, getHeight() / rows - 20);
                }
            }
        }
    }
}
