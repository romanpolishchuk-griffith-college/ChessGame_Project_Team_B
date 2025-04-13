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
    
    protected void initializePieces() {
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
  
  private ChessPiece isSquareUnderAttack(int squareX, int squareY, boolean isEnemyWhite) {
	  for(int y = 0; y < BOARD_SIZE; y++) {
		  for(int x = 0; x < BOARD_SIZE; x++) {
			  if(getPiece(x, y) != null && getPiece(x, y).isWhite == isEnemyWhite
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
	  
	  if(isSquareUnderAttack(kingX, kingY, true) == null){
		  return false;
	  }
	  
	  if(kingY + 1 < BOARD_SIZE &&
			  getPiece(kingX, kingY + 1) == null &&
			  isSquareUnderAttack(kingX, kingY + 1, true) == null) {
			  		return false;
	  }
	  
	  if(kingY - 1 >= 0 &&
			  getPiece(kingX, kingY - 1) == null &&
			  isSquareUnderAttack(kingX, kingY - 1, true) == null) {
			  		return false;
	  }
	  
	  if(kingX + 1 < BOARD_SIZE &&
			  getPiece(kingX + 1, kingY) == null &&
			  isSquareUnderAttack(kingX + 1, kingY, true) == null) {
			  		return false;
	  }
	  
	  if(kingX - 1 >= 0 &&
			  getPiece(kingX - 1, kingY) == null &&
			  isSquareUnderAttack(kingX - 1, kingY, true) == null) {
			  		return false;
	  }
	  
	  if(kingX - 1 >= 0 &&
			  kingY - 1 >= 0 &&
			  getPiece(kingX - 1, kingY - 1) == null &&
			  isSquareUnderAttack(kingX - 1, kingY - 1, true) == null) {
			  		return false;
	  }
	  
	  if(kingX - 1 >= 0 &&
			  kingY + 1 < BOARD_SIZE &&
			  getPiece(kingX - 1, kingY + 1) == null &&
			  isSquareUnderAttack(kingX - 1, kingY + 1, true) == null) {
			  		return false;
	  }
	  
	  if(kingX + 1 < BOARD_SIZE &&
			  kingY - 1 >= 0 &&
			  getPiece(kingX + 1, kingY - 1) == null &&
			  isSquareUnderAttack(kingX + 1, kingY - 1, true) == null) {
			  		return false;
	  }
	  
	  if(kingX + 1 < BOARD_SIZE &&
			  kingY + 1 < BOARD_SIZE &&
			  getPiece(kingX + 1, kingY + 1) == null &&
			  isSquareUnderAttack(kingX + 1, kingY + 1, true) == null) {
			  		return false;
	  }
	  
	  ChessPiece attackingPiece = isSquareUnderAttack(kingX, kingY, true);
	  
	  int attackingPieceX = 0;
	  int attackingPieceY = 0;
	  for (int y = 0; y < BOARD_SIZE; y++) {
          for (int x = 0; x < BOARD_SIZE; x++) {
              if (getPiece(x, y) == attackingPiece) {
            	  attackingPieceX = x;
            	  attackingPieceY = y;
                  break;
              }
          }
      }
	  
	  String[] validMovesOFAttakingPiece = (attackingPiece.getValidMoves() + attackingPieceX + "," + attackingPieceY).split(" ");
	  
	  for(int i = 0; i < validMovesOFAttakingPiece.length; i++) {
		  if(isSquareUnderAttack(validMovesOFAttakingPiece[i].charAt(0), validMovesOFAttakingPiece[i].charAt(2), false) != null) {
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
	  
	  if(isSquareUnderAttack(kingX, kingY, false) == null){
		  return false;
	  }
	  
	  if(kingY + 1 < BOARD_SIZE &&
			  getPiece(kingX, kingY + 1) == null &&
			  isSquareUnderAttack(kingX, kingY + 1, false) == null) {
			  		return false;
	  }
	  
	  if(kingY - 1 >= 0 &&
			  getPiece(kingX, kingY - 1) == null &&
			  isSquareUnderAttack(kingX, kingY - 1, false) == null) {
			  		return false;
	  }
	  
	  if(kingX + 1 < BOARD_SIZE &&
			  getPiece(kingX + 1, kingY) == null &&
			  isSquareUnderAttack(kingX + 1, kingY, false) == null) {
			  		return false;
	  }
	  
	  if(kingX - 1 >= 0 &&
			  getPiece(kingX - 1, kingY) == null &&
			  isSquareUnderAttack(kingX - 1, kingY, false) == null) {
			  		return false;
	  }
	  
	  if(kingX - 1 >= 0 &&
			  kingY - 1 >= 0 &&
			  getPiece(kingX - 1, kingY - 1) == null &&
			  isSquareUnderAttack(kingX - 1, kingY - 1, false) == null) {
			  		return false;
	  }
	  
	  if(kingX - 1 >= 0 &&
			  kingY + 1 < BOARD_SIZE &&
			  getPiece(kingX - 1, kingY + 1) == null &&
			  isSquareUnderAttack(kingX - 1, kingY + 1, false) == null) {
			  		return false;
	  }
	  
	  if(kingX + 1 < BOARD_SIZE &&
			  kingY - 1 >= 0 &&
			  getPiece(kingX + 1, kingY - 1) == null &&
			  isSquareUnderAttack(kingX + 1, kingY - 1, false) == null) {
			  		return false;
	  }
	  
	  if(kingX + 1 < BOARD_SIZE &&
			  kingY + 1 < BOARD_SIZE &&
			  getPiece(kingX + 1, kingY + 1) == null &&
			  isSquareUnderAttack(kingX + 1, kingY + 1, false) == null) {
			  		return false;
	  }
	  
	  ChessPiece attackingPiece = isSquareUnderAttack(kingX, kingY, false);
	  
	  int attackingPieceX = 0;
	  int attackingPieceY = 0;
	  for (int y = 0; y < BOARD_SIZE; y++) {
          for (int x = 0; x < BOARD_SIZE; x++) {
              if (getPiece(x, y) == attackingPiece) {
            	  attackingPieceX = x;
            	  attackingPieceY = y;
                  break;
              }
          }
      }
	  
	  String[] validMovesOFAttakingPiece = (attackingPiece.getValidMoves() + attackingPieceX + "," + attackingPieceY).split(" ");
	  
	  for(int i = 0; i < validMovesOFAttakingPiece.length; i++) {
		  if(isSquareUnderAttack(validMovesOFAttakingPiece[i].charAt(0), validMovesOFAttakingPiece[i].charAt(2), true) != null) {
			  return false;
		  }
	  }
	  
	  return true;
  }
}