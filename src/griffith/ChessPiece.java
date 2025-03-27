package griffith;

import java.awt.Graphics;
public abstract class ChessPiece {
	
	 protected int col, row;
	 protected int xPos, yPos;
	 protected boolean isWhite;
	 protected String name;
	 protected Board board;

	 public ChessPiece(Board board) {
	        this.board = board;
	    }

	    
	abstract public String getValidMoves();
	
	abstract public boolean isMoveValid();
	
	// Update the position of the piece
    public void updatePosition() {
        this.xPos = board.getSquareSize() * col;
        this.yPos = board.getSquareSize() * row;
    }
	
	abstract public void draw();
	
}
