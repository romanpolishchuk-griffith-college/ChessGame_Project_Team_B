package griffith;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel  {
  private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 70;
    
    public Board() {
        setPreferredSize(new java.awt.Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
    }
    
  private ChessPiece[][] board = {
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null,
      null, null, null, null, null, null, null, null
  };
  

  
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
                  g.setColor(getSquareColor(row, col));;
                  
                  // Draw the square
                  g.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
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
  
}