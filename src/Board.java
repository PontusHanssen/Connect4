import java.awt.Color;


public class Board {
	
	private Marker[][] board;
	private int cols, rows;
	public final Color boardColor = Color.blue;
	
	
/**
 * 
 * @param rows Number of rows on the board
 * @param cols Number of columns on the board 
 */
	public Board(int rows, int cols) {
		this.cols = cols;
		this.rows = rows;
		board = new Marker[rows][cols];
		for(Marker[] currentRow : board) {
			for(Marker currentMarker : currentRow) {
				currentMarker = Marker.EMPTY;
			}
		}
	}

	/**
	 * 
	 * @param col Specified column to search
	 * @return True if move is legal. Else false
	 */
	private boolean canHazMove(int col){

		for(int i=0; i < rows; i++) {
			if(board[i][cols] == Marker.EMPTY) {
				return true;
			}
		}
		return false;
	}

/**
 * 
 * @param col Column to place marker in
 * @param player The current player
 * @return True if move was made. Else false
 */
	public boolean placeMove(int col, Marker player) {
		if(!canHazMove(col)) {
			return false;
		}
		else {
			for(int i=0; i < rows; i++) {
				if(board[i][cols] == Marker.EMPTY) {
					board[i][cols] = player;
					return true;
				}
			}
			
		}
		return false;
		
	}

}
