package griffith;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel  {
  private static final int BOARD_SIZE = 8;
  private static final int SQUARE_SIZE = 80;
  private ChessPiece[][] board;
    
    public Board() {
        setPreferredSize(new java.awt.Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        initializePieces();
    }
    
    private void initializePieces() {
        // Initialize White pieces
        // Pawns
        for (int col = 0; col < BOARD_SIZE; col++) {
          board[1][col] = new Pawn(this, col, 1, true);
      }
      // Rooks
      board[0][0] = new Rook(this, 0, 0, true);
      board[0][7] = new Rook(this, 7, 0, true);
      // Knights
      board[0][1] = new Knight(this, 1, 0, true);
      board[0][6] = new Knight(this, 6, 0, true);
      // Bishops
      board[0][2] = new Bishop(this, 2, 0, true);
      board[0][5] = new Bishop(this, 5, 0, true);
      // Queen
      board[0][3] = new Queen(this, 3, 0, true);
      // King
      board[0][4] = new King(this, 4, 0, true);

      // Initialize Black pieces
      // Pawns
      for (int col = 0; col < BOARD_SIZE; col++) {
          board[6][col] = new Pawn(this, col, 6, false);
      }
      // Rooks
      board[7][0] = new Rook(this, 0, 7, false);
      board[7][7] = new Rook(this, 7, 7, false);
      // Knights
      board[7][1] = new Knight(this, 1, 7, false);
      board[7][6] = new Knight(this, 6, 7, false);
      // Bishops
      board[7][2] = new Bishop(this, 2, 7, false);
      board[7][5] = new Bishop(this, 5, 7, false);
      // Queen
      board[7][3] = new Queen(this, 3, 7, false);
      // King
      board[7][4] = new King(this, 4, 7, false);
    }
    
//  private ChessPiece[][] board = {
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null,
//      null, null, null, null, null, null, null, null
//  };
  

  
  public int getBoredSize(){
    return BOARD_SIZE;
  }

  public ChessPiece setPiece() {
    //Stab
    return null;
  }
  
  public ChessPiece getPiece() {
    //Stab
    return null;
  }
  
  public void draw(Graphics g) {
      // Draw the chess board squares
      for (int row = 0; row < BOARD_SIZE; row++) {
          for (int col = 0; col < BOARD_SIZE; col++) {
              // Set color based on position
              g.setColor(getSquareColor(row, col));
              // Draw the square
              g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
              
              // Draw piece if exists
              ChessPiece piece = board[row][col];
              if (piece != null) {
                  piece.draw(g);
              }
          }
      }
  }
  
   @Override
  protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          draw(g);
      }
   
  public Color  getSquareColor(int row, int column) {
 
    if ((row + column) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    
  }
  
  public int getSquareSize() {
	  return SQUARE_SIZE;
  }
}