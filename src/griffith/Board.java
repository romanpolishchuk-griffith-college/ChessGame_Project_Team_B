package griffith;

public class Board {
	private ChessPiece[][] board = {
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
	};

	public Board() {}

	public Board(ChessPiece[][] board) {
		this.board = board;
	}

	public ChessPiece[][] getBoard(){
		return null;
	}

	public void setBoard(ChessPiece[][] board){}

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
	
	public void draw() {
		//Stab
	}
	
}
