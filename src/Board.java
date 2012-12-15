import java.awt.Color;



/**
 * Creates a game board and contains functions for checking for winner, checking
 * if move is possible and to know the state of the current board.
 * 
 * @author Tova Linder och Pontus Persson
 * 
 */
public class Board implements Cloneable {

	private final static Color color = Color.blue;
	private MarkerType[][] board;
	private int cols, rows;
	private boolean boardFull = true;

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
	 * 
	 * @param pos
	 *            Position selected by player.
	 * @param player
	 *            Current player.
	 * @throws NoSpaceLeftInColumnException
	 *             Thrown when column is filled.
	 */
	public void placeMove(int pos, Player player)
			throws NoSpaceLeftInColumnException {
		int col = pos % cols;
		if (possibleMove(col)) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][col] == MarkerType.EMPTY) {
					board[i][col] = player.getColor();
					player.addMove();
					return;

				}
			}
		} else {
			throw new NoSpaceLeftInColumnException();
		}

	}

	/**
	 * Returns the variable that knows if the board is full or not.
	 * 
	 * @return Returns true if board is full, otherwise false.
	 */
	public boolean isBoardFull() {
		return boardFull;
	}



	/**
	 * Loops though the board to see if currrentPlayer has won.
	 * 
	 * @param currentPlayer
	 * @return The MarkerType of currentPlayer, EMPTY if currentPlayer didn't win.
	 */
	public MarkerType checkWin(Player currentPlayer) {
		boardFull = true;
		for (int c = 0; c < cols; c++) {
			if (board[rows - 1][c] == MarkerType.EMPTY) {
				boardFull = false;
			}
		}

		for (int i = 0; i < cols * rows; i++) {
			int x = i % cols;
			int y = i / cols;
			
			if(checkWinDirection(x, y, 1, 0) == currentPlayer.getColor()) {
				return currentPlayer.getColor();
			}
			else if(checkWinDirection(x, y, 0, 1) == currentPlayer.getColor()) {
				return currentPlayer.getColor();
			}
			else if(checkWinDirection(x, y, 1, 1) == currentPlayer.getColor()) {
				return currentPlayer.getColor();
			}
			else if(checkWinDirection(x, y, 1, -1) == currentPlayer.getColor()) {
				return currentPlayer.getColor();
			}
		}
		
		//If there's no winner, return EMPTY
		return MarkerType.EMPTY;
	}

/**
 * Checks for a winner from coordinate x,y in the direction dx,dy.
 * dx=1,dy=0 => Horizontal
 * dx=0,dy=1 => Vertical
 * dx=1,dy=1 => NorthEast
 * dx=-1,dy=1 => NorthWest
 * 
 * @param x x coordinate on the board
 * @param y y coordinate on the board
 * @param dx direction to check on the x-axis
 * @param dy direction to check on the y-axis
 * @return MarkerType of winner or EMPTY if there's no winner
 */
	public MarkerType checkWinDirection(int x, int y, int dx, int dy) {
		MarkerType[] inARow = new MarkerType[4];
		for (int i = 0; i < inARow.length; i++, x += dx, y += dy) {
			inARow[i] = getMarkerTypeIfValid(x, y);
		}
		if (inARow[0] == inARow[1] && inARow[1] == inARow[2]
				&& inARow[2] == inARow[3]) {
			return inARow[0];
		} else {
			return MarkerType.EMPTY;
		}
	}

	

	/**
	 * Returns the MarkerType on the given x and y coordinates. If x and/or y is invalid returns EMPTY
	 * @param x
	 * @param y
	 * @return MarkerType on the given x and y coordinates.
	 */
	public MarkerType getMarkerTypeIfValid(int x, int y) {
		if (0 <= x && x < cols && 0 <= y && y < rows) {
			return board[y][x];
		} else {
			return MarkerType.EMPTY;
		}
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
	 * 
	 * @param pos
	 *            Position for MarkerType to be placed in.
	 * @param markertype
	 *            The MarkerType that should be placed in the position.
	 */
	public void setMarkerPos(int pos, MarkerType markertype) {
		int row = pos / cols;
		int col = pos % cols;
		board[row][col] = markertype;
	}

	/**
	 * Checks what MarkerType there is in a certain position.
	 * 
	 * @param pos
	 *            Position to be checked.
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

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	/**
	 * Removes all markers from the board.
	 */
	public void emptyBoard() {
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < cols; k++) {
				board[i][k] = MarkerType.EMPTY;
			}
		}
	}

	/**
	 * Clones the current game board.
	 */
	public Board clone() {
		Board cloneBoard = new Board(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int k = 0; k < cols; k++) {
				cloneBoard.board[i][k] = board[i][k];
			}
		}
		return cloneBoard;
	}
}