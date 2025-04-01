package griffith;

public class Pawn extends ChessPiece {
    
        @Override
        public String getValidMoves() {
            return "Forward 1";
        }
    
        @Override
        public boolean isMoveValid() {
            return true;
        }
    
        @Override
        public void draw() {
            System.out.println("Drawing Pawn");
        }
}
