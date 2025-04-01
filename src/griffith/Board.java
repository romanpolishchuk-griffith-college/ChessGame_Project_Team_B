package griffith;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JPanel  {
  private static final int BOARD_SIZE = 8;
  private static final int SQUARE_SIZE = 80;
  private JPanel piecePanel;
  private JFrame window;
//  private ChessPiece[][] board;
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
    
    public Board(boolean initializePieces) {
        setPreferredSize(new java.awt.Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        if (initializePieces) {
            initializePieces();
        }
       }
    
    // Default constructor that creates a full game board
    public Board() {
    }
    
    private void initializePieces() {
        // Initialize White pieces
    // Pawns
    for (int col = 0; col < BOARD_SIZE; col++) {
      setPiece(col, 1, new Pawn(this, true));
  }
  // Rooks
  setPiece(0, 0, new Rook(this, true));
  setPiece(7, 0, new Rook(this,  true));
  // Knights
  setPiece(1, 0, new Knight(this, true));
  setPiece(6, 0, new Knight(this,  true));
  // Bishops
  setPiece(2, 0, new Bishop(this,  true));
  setPiece(5, 0, new Bishop(this,  true));
  // Queen
  setPiece(3, 0, new Queen(this,  true));
  // King
  setPiece(4, 0, new King(this, true));

//   Initialize Black pieces
//   pawns
  for (int col = 0; col < BOARD_SIZE; col++) {
    setPiece(col, 6, new Pawn(this, false));
}
  // Rooks
  setPiece(0, 7, new Rook(this,  false));
  setPiece(7, 7, new Rook(this,  false));
  // Knights
  setPiece(1, 7, new Knight(this,  false));
  setPiece(6, 7, new Knight(this,  false));
  // Bishops
  setPiece(2, 7, new Bishop(this,  false));
  setPiece(5, 7, new Bishop(this, false));
  // Queen
  setPiece(3, 7, new Queen(this,false));
  // King
  setPiece(4, 7, new King(this,  false));
}

    

  public ChessPiece[][] getBoard(){
		return board;
	}

  public void setBoard(ChessPiece[][] board){
		this.board = board;
	}
    
  
  public int getBoredSize(){
    return BOARD_SIZE;
  }

  public ChessPiece getPiece(int x, int y) {
		if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return null;
		}
		return board[board.length-1-y][x];
	}

  public void setPiece(int x, int y, ChessPiece piece) {
		if(x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return;
		}
		board[board.length-1-y][x] = piece;
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
      
      if(window != null && getPiece(newX, newY) != null) {
          window.remove(getPiece(newX, newY).button);  
      }
      
      //Remove piece from old place
      setPiece(pieceX, pieceY, null);
      //Move piece to new place
      setPiece(newX, newY, piece);
  }
  

  
  public void draw(JFrame window) {
	  BoardPanel boardPanel = new BoardPanel();
	  
	  this.window = window;
	  
	  for(int i = 0; i < BOARD_SIZE; i++) {
		  for (int j = 0; j< BOARD_SIZE; j++) {
			  
			  if(getPiece(i,j) != null) {
				  getPiece(i, j).draw(window);
			  }
			 
			  
		  }
	  }
	  window.add(boardPanel);
	  window.revalidate();
	  window.repaint();
	 
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