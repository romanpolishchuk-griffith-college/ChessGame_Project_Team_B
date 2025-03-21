package griffith;

import java.awt.Graphics;
public abstract class ChessPiece {

	abstract public String getValidMoves();
	
	abstract public boolean isMoveValid();
	
	abstract public void draw(Graphics g, int x, int y);
	
}
