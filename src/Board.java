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

	public void placeMove (int pos, Player player) throws NoSpaceLeftInColumnException {
		int col = pos%cols;
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
	
	public MarkerType checkWin() {
		//horisontal 
		for(int r=0; r < rows; r++) {
			for(int c=0; c < cols; c++) {
				if(board[r][c] == board[r][c+1] && board[r][c] == board[r][c+2] && board[r][c] == board[r][c+3]) {
					return board[r][c]; 
				}
			}
			
		}
		
		//vertical
		for(int c=0; c < cols; c++) {
			for(int r=0; r < rows; r++) {
				if(board[r][c] == board[r+1][c] && board[r][c] == board[r+2][c] && board[r][c] == board[r+3][c]) {
					return board[r][c]; 
				}
			}
			
		}
		
		//diagonals
		for(int r=0; r < rows; r++) {
			for(int c=0; c < cols; c++) {
				if(board[r][c] == board[r+1][c+1] && board[r][c] == board[r+2][c+2] && board[r][c] == board[r+3][c+3]) {
					return board[r][c];
				}
			}
		}
	
		return MarkerType.EMPTY; 
	
	}
	


	
	public MarkerType[][] getBoard() {
		return this.board;
	}
	private void setMarkerPos(int pos, MarkerType markertype) {
		int row = pos/cols;
		int col = pos%cols;
		board[row][col]=markertype;
	}
	public MarkerType getMarkerType(int pos) {
		
		int row = pos/cols;
		int col = pos%cols;
		return board[row][col];
	}
	
	public static Color getColor() {
		return color;
	}
}
