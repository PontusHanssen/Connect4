import java.awt.Color;

public class Board {

	private final Color color = Color.blue;
	private Marker[][] board;
	public Board(int rows, int cols) {
		board = new Marker[rows][cols];
		for (Marker currentRow[] : board) {
			for (Marker currentPos : currentRow) {
				currentPos = Marker.EMPTY;
			}
		}
	}

	private boolean possibleMove(int col) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == Marker.EMPTY) {
				return true;
			}
		}

		return false;
	}

	public void placeMove (int col, Player player) throws NoSpaceLeftInColumnException {
		if (possibleMove(col)) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][col] == Marker.EMPTY) {
					board[i][col] = player.getColor();
					return;
				}

			}
		} else {
			throw new NoSpaceLeftInColumnException(); 
		}

	}

	public Marker[][] getBoard() {
		return this.board;
	}
	
}
