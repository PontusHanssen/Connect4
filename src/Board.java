import java.awt.Color;

public class Board implements Cloneable {

	private final static Color color = Color.blue;
	private MarkerType[][] board;
	public int cols, rows;

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

	/**
	 * Places a marker when a move is made.
	 * @param pos Position selected by player.
	 * @param player Current player.
	 * @throws NoSpaceLeftInColumnException Thrown when column is filled.
	 */
	public void placeMove(int pos, Player player)
			throws NoSpaceLeftInColumnException {
		int col = pos % cols;
		if (possibleMove(col)) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][col] == MarkerType.EMPTY) {
					board[i][col] = player.getColor();
					player.moves++;
					return;
				}

			}
		} else {
			throw new NoSpaceLeftInColumnException();
		}

	}
	
	/**
	 * Checks if there is a winner.
	 * @return MarkerType of winner.
	 */
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

	/**
	 * 
	 * @return Returns the current game board.
	 */
	public MarkerType[][] getBoard() {
		return this.board;
	}

	/**
	 * Sets a MarkerType to a position on the board.
	 * @param pos Position for MarkerType to be placed in.
	 * @param markertype The MarkerType that should be placed in the position.
	 */
	public void setMarkerPos(int pos, MarkerType markertype) {
		int row = pos / cols;
		int col = pos % cols;
		board[row][col] = markertype;
	}

	/**
	 * Checks what MarkerType there is in a certain position.
	 * @param pos Position to be checked.
	 * @return Returns the MarkerType of the position.
	 */
	public MarkerType getMarkerType(int pos) {

		int row = pos / cols;
		int col = pos % cols;
		return board[row][col];
	}

	/**
	 * 
	 * @return Returns the color of the board.
	 */
	public static Color getColor() {
		return color;
	}

	/**
	 * Removes all markers from the board.
	 */
	public void emptyBoard() {
		for(int i = 0;i<rows;i++) {
			for(int k = 0;k<cols;k++) {
				board[i][k] = MarkerType.EMPTY;
			}
		}
	}
	
	/**
	 * Clones the current game board.
	 */
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