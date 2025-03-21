package griffith;

import java.awt.Color;
import java.awt.Graphics;

public class Board  {
	private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 70;
    
 
    
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
	       
	       
	    }
	 
	public Color  getSquareColor(int row, int column) {
		// TODO Auto-generated method stub
		if ((row + column) % 2 == 0) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
		
	}
	
}



