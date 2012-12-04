import java.awt.Color;

public class Board implements Cloneable {

	private final static Color color = Color.blue;
	private MarkerType[][] board;
	private int cols, rows;

	/**
	 * Creates new game board.
	 * 
	 * @param rows
	 *            Number of rows on board.
	 * @param cols
	 *            Number of columns on board.
	 */
	public Board(int rows, int cols) {
		this.cols = cols;
		this.rows = rows;

		board = new MarkerType[rows][cols];
		for (int i = 0; i < cols * rows; i++) {
			setMarkerPos(i, MarkerType.EMPTY);
		}

	}

	/**
	 * Checks if there is space left in column.
	 * 
	 * @param col
	 *            Column to check.
	 * @return <code>true</code> if space left in col, else <code>false</code>.
	 */
	private boolean possibleMove(int col) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == MarkerType.EMPTY) {
				return true;
			}
		}

		return false;
	}

	public void placeMove(int pos, Player player)
			throws NoSpaceLeftInColumnException {
		int col = pos % cols;
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
		// horizontal
		MarkerType win = MarkerType.EMPTY;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols - 3; c++) {
				if (board[r][c] == board[r][c + 1]
						&& board[r][c] == board[r][c + 2]
						&& board[r][c] == board[r][c + 3]
						&& board[r][c] != MarkerType.EMPTY) {

					win = board[r][c];
				}
			}

		}

		// vertical
		for (int c = 0; c < cols; c++) {
			for (int r = 0; r < rows - 3; r++) {
				if (board[r][c] == board[r + 1][c]
						&& board[r][c] == board[r + 2][c]
						&& board[r][c] == board[r + 3][c]
						&& board[r][c] != MarkerType.EMPTY) {

					win = board[r][c];
				}
			}

		}

		// diagonals NE
		for (int r = 0; r < rows - 3; r++) {
			for (int c = 0; c < cols - 3; c++) {
				if (board[r][c] == board[r + 1][c + 1]
						&& board[r][c] == board[r + 2][c + 2]
						&& board[r][c] == board[r + 3][c + 3]
						&& board[r][c] != MarkerType.EMPTY) {
					win = board[r][c];
				}
			}
		}

		// diagonals NW
		for (int r = 0; r < rows - 3; r++) {
			for (int c = cols - 1; c > 2; c--) {
				if (board[r][c] == board[r + 1][c - 1]
						&& board[r][c] == board[r + 2][c - 2]
						&& board[r][c] == board[r + 3][c - 3]
						&& board[r][c] != MarkerType.EMPTY) {
					win = board[r][c];
				}
			}
		}

		return win;

	}

	public MarkerType[][] getBoard() {
		return this.board;
	}

	public void setMarkerPos(int pos, MarkerType markertype) {
		int row = pos / cols;
		int col = pos % cols;
		board[row][col] = markertype;
	}

	public MarkerType getMarkerType(int pos) {

		int row = pos / cols;
		int col = pos % cols;
		return board[row][col];
	}

	public static Color getColor() {
		return color;
	}

	public void emptyBoard() {
		for(int i = 0;i<rows;i++) {
			for(int k = 0;k<cols;k++) {
				board[i][k] = MarkerType.EMPTY;
			}
		}
	}
	
	public Board clone() {
		Board cloneBoard = new Board(rows, cols);
		for(int i=0; i<rows; i++) {
			for(int k=0; k<cols; k++) {
				cloneBoard.board[i][k] = board[i][k];
			}
		}
		return cloneBoard;
	}
}