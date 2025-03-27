package griffith;

import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
public abstract class ChessPiece {
	
	 protected int col, row;
	 protected int xPos, yPos;
	 protected boolean isWhite;
	 protected String name;
	 protected Board board;
	 protected Image sprite;

	 public ChessPiece(Board board) {
	        this.board = board;
	    }

	 protected void loadImage(String imageName) {
	        try {
	            // Get the project root directory (2 levels up from the class file)
	            String projectRoot = new File(getClass().getProtectionDomain()
	                    .getCodeSource().getLocation().toURI()).getParentFile().getPath();
	            
	            // Construct the path to the image file
	            String imagePath = projectRoot + "/src/res/" + imageName;
	            System.out.println("Attempting to load image from: " + imagePath);
	            
	            // Load the image
	            File imageFile = new File(imagePath);
	            if (!imageFile.exists()) {
	                System.err.println("Image file does not exist: " + imagePath);
	                return;
	            }
	            
	            sprite = ImageIO.read(imageFile)
	                    .getScaledInstance(board.getSquareSize(), board.getSquareSize(), Image.SCALE_SMOOTH);
	        } catch (Exception e) {
	            System.err.println("Error loading image: " + imageName);
	            e.printStackTrace();
	        }
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
