import java.awt.Color;
/**
 * Creates a game board and contains functions for checking for winner, checking if move is possible and to know the state of the current board.
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
	 * @return Returns true if board is full, otherwise false.
	 */
	public boolean isBoardFull() {
		return boardFull; 
	}
	
	/** 
	 * Checks if the marker x+dx, y+dy is within the board.
	 * @param x Current position x
	 * @param y Current position y
	 * @param dx Delta-x
	 * @param dy Delta-y
	 * @return True if marker is on board, else false.
	 */
	private boolean isValidCheck(int x, int y) {
		if(x >-1 && x < cols && y > -1 && y < rows) { 
			System.out.println(x + " true  " + y);
			return true;
		}
		else {
			System.out.println(x + " false  " + y);
			return false;
		}
		/*		if(dx==0 && x>=0 && x< cols && y>=0 && y<rows-1) {
			return true; 
		}
		else if(dy==0 && x>=0 && x<cols-1 && y>=0 && y<rows-2) {
			return true; 
		}
		else if(dx==-1 && x>=0 && x< cols-2 && y>=0 && y<rows-2) {
			return true; 
		}
		else if(dx==1 && dy==1 && x>0 && x<cols-1 && y>=0 && y< rows-2) {
			return true;
		}
		else {
			return false;
		}*/
	}
	/**
	 * Checks for win every direction
	 * @param currentPlayer
	 * @return The player with 4 in a row, EMPTY if no winner.
	 */
	public MarkerType checkWin(Player currentPlayer) {
		boardFull = true;
		for(int c=0; c < cols; c++) {
			if(board[rows-1][c] == MarkerType.EMPTY) {
				boardFull = false; 
			}
		}
		
		MarkerType EW, NS, NE, NW;
		EW = checkWinDirection(currentPlayer, 1, 0);
		NS = checkWinDirection(currentPlayer, 0, 1);
		NE = checkWinDirection(currentPlayer, 1, 1);
		NW = checkWinDirection(currentPlayer, -1, 1);
		//System.out.println("EW: " + EW + "\nNS: " + NS + "\nNE: " + NE + "\nNW: " + NW);
		
		if(EW != MarkerType.EMPTY) {
			return EW;
		}
		else if(NS != MarkerType.EMPTY) {
			return NS;
		}
		else if(NE != MarkerType.EMPTY) {
			return NE;
		}
		else if(NW != MarkerType.EMPTY) {
			return NW;
		}
		else {
			return MarkerType.EMPTY;
		}
	}
	
	/**
	 * Checks if there is a winner in a certain direction.
	 * dx=0,dy=1 : Vertical
	 * dx=1,dy=0 : Horizontal
	 * dx=1,dy=1 : NorthWest Diagonal
	 * dx=-1,dy=1 : NorthEast Diagonal
	 * @param currentPlayer
	 * @param dx
	 * @param dy
	 * @return 
	 */
	public MarkerType checkWinDirection(Player currentPlayer, int dx, int dy) {
		int countInRow = 0, x = 0, y=0;
		for(int i=0; i<rows*cols-1; i++) {
			countInRow = 0;
			x=i%cols;
			y=i/cols;
		
		for(int checked = 0; checked < 4 && isValidCheck(x, y); checked++, x += dx, y +=dy) {
			if(board[x][y] != currentPlayer.getColor()) {
				countInRow=0;
			}
			else {
				countInRow++; 
			}
			if(countInRow == 4) {
				return currentPlayer.getColor();
			}
		}
		}
			return MarkerType.EMPTY; 
		}
		


	
	
	/**
	 * Checks if there is a winner.
	 * @return MarkerType of winner.
	 */
	/*public MarkerType checkWin() {
		//check top row if board is full
		boardFull = true;
		horizDiagoWin = false;
		for(int c=0; c < cols; c++) {
			if(board[rows-1][c] == MarkerType.EMPTY) {
				boardFull = false; 
			}
		}
		MarkerType win = MarkerType.EMPTY;

	// horizontal
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols - 3; c++) {

				if (board[r][c] == board[r][c + 1]
						&& board[r][c] == board[r][c + 2]
						&& board[r][c] == board[r][c + 3]
						&& board[r][c] != MarkerType.EMPTY) {
					horizDiagoWin = true;
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
					horizDiagoWin = true;
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
					horizDiagoWin = true;
					win = board[r][c];
				}
			}
		} 

		return win;

	}*/
	

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