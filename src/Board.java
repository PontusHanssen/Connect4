import java.awt.Color;

public class Board {

	private final static Color color = Color.blue;
	private MarkerType[][] board;
	private int cols, rows;
	public Board(int rows, int cols) {
		this.cols = cols;
		this.rows = rows;
		
		board = new MarkerType[rows][cols];
		for (int i=0; i<cols*rows;i++){
			setMarkerPos(i, MarkerType.EMPTY);
			}
		
	}

	private boolean possibleMove(int col) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == MarkerType.EMPTY) {
				return true;
			}
		}

		return false;
	}

	public void placeMove (int col, Player player) throws NoSpaceLeftInColumnException {
		if (possibleMove(col)) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][col] == MarkerType.EMPTY) {
					board[i][col] = player.getColor();
					return;
				}

			}
		} else {
			throw new NoSpaceLeftInColumnException(); 
		}

	}

	public MarkerType[][] getBoard() {
		return this.board;
	}
	private void setMarkerPos(int pos, MarkerType markertype) {
		int row = pos/cols;
		int col = pos%cols;
		board[row][col]=markertype;
	}
	public MarkerType getMarkerPos(int pos) {
		
		int row = pos/cols;
		int col = pos%cols;
		return board[row][col];
	}
	
	public static Color getColor() {
		return color;
	}
}
