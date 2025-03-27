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
        // Initialize knights
        board[0][1] = new Knight(this, 1, 0, true);  // White knight
        board[0][6] = new Knight(this, 6, 0, true);  // White knight
        board[7][1] = new Knight(this, 1, 7, false); // Black knight
        board[7][6] = new Knight(this, 6, 7, false); // Black knight
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