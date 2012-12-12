import java.util.Date;
import java.util.Random;

public class AI extends Player {
	private String level;

	/**
	 * Creates new bot player.
	 * @param color Markercolor of bot player.
	 * @param level Level for bot player.
	 */
	public AI(MarkerType color, String level) {
		super(color, "Bot");
		this.level = level;
	}

	/**
	 * AI that generates a random number to place a marker in.
	 * 
	 * @param largestPos
	 *            The largest position number on the board.
	 * @return A position to place the next move.
	 */
	public int getMoveEasy(int largestPos) {
		Random randomPos = new Random(new Date().getTime());
		int pos = randomPos.nextInt(largestPos);
		return pos;
	}

	/**
	 * AI that checks if it is possible to win in this move. Otherwise places a
	 * marker in random position.
	 * 
	 * @param board
	 *            Current game board.
	 * @param largestPos
	 *            The largest position number on the board.
	 * @return A position to place the next move.
	 */
	public int getMoveMedium(Board board, int largestPos) {
		for (int i = 0; i < largestPos; i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, new Player(this.getColor(), "Fake player"));
				if (workingBoard.checkWin() == this.getColor()) {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
					return i;

				} else {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
				}
			} catch (NoSpaceLeftInColumnException e) {

			}

		}

		return getMoveEasy(largestPos);
	}

	/**
	 * Position calculated by our supermegaawesome algorithm!!!11
	 * @param board
	 * @param largestPos
	 * @retur
	 */
	public int getMoveHard(Board board, Player opponent) {
		if(canWin(board, this) > -1) {
			return canWin(board, this);
		}
		else if(canWin(board, opponent) > -1) {
			return canWin(board, opponent);
		}
		else if(twoStepsAhead(board, this) > -1) {
			return twoStepsAhead(board, this);
		}

		else if(twoStepsAhead(board, opponent) > -1) {
			return twoStepsAhead(board, opponent);
		}
		else if(isMiddleEmpty(board, this) > -1) {
			return isMiddleEmpty(board, this);
		}
		else if(nextTo(board, this) > -1) {
			return nextTo(board, this);
		}
		else {
			return getMoveEasy(board.cols*board.rows-1);
		}
	}

	/**
	 * Sets level for the bot.
	 * @param level 
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	private int twoStepsAhead(Board board, Player currentPlayer) {
		Player player = new Player(currentPlayer.getColor(), "Fake Player");
		int largestPos = board.cols * board.rows -1;
		for(int i=0;i < largestPos; i++){
			Board firstBoard = board.clone();
			try{
				firstBoard.placeMove(i, player);
				for(int k=0;k<largestPos;k++) {
					Board secoundBoard = firstBoard.clone();
					secoundBoard.placeMove(k, player);
					if(secoundBoard.checkWin() == player.getColor()) {
						if(firstBoard.horizontalWin){
							return -1;
						}
						firstBoard.setMarkerPos(i, MarkerType.EMPTY);
						firstBoard.setMarkerPos(k, MarkerType.EMPTY);
						if(firstBoard.horizontalWin){
							return -1;
						}
						else {
						return i;
						}
					}
					else {
						firstBoard.setMarkerPos(i, MarkerType.EMPTY);
						firstBoard.setMarkerPos(k, MarkerType.EMPTY);
					}
				}
			} catch(NoSpaceLeftInColumnException e) {
				
			}
		}
		return -1;
	}

	private int canWin(Board board, Player currentPlayer) {
		Player player = new Player(currentPlayer.getColor(), "Fake Player");
		int largestPos = board.cols * board.rows -1;
		for(int i=0;i<largestPos;i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, player);
				if(workingBoard.checkWin() == player.getColor()) {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
					return i;
				}
				else {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
				}
				} catch(NoSpaceLeftInColumnException e) {
					
			}
		}
		return -1;
	}
	
	private int isMiddleEmpty(Board board, Player currentPlayer) {
		Player player = new Player(currentPlayer.getColor(), "Fake Player");
		int middle = board.cols/2;
		if(board.getMarkerType(middle) == MarkerType.EMPTY) {
			return middle;
		}
		else {
			return -1;
		}
	}
	
	private int nextTo(Board board, Player currentPlayer) {
int largestPos = board.cols*board.rows-1; 
		Player player = new Player(currentPlayer.getColor(), "Fake Player");
		
		//horizontal to the right
		for (int i = 0; i < largestPos; i++) {
			if(board.getMarkerType(i) == player.getColor() 
					&& i % board.cols < (board.cols - 1)
					&& board.getMarkerType(i+1) == MarkerType.EMPTY) {
				try {
					board.placeMove((i+1), player);
				} catch (NoSpaceLeftInColumnException e) {
					
				} 
				if(board.getMarkerType(i+1)==board.getMarkerType(i)) {
					boolean hit = false; 
					int testPosition = (i+1); 
					while(!hit) {
					if(board.getMarkerType(testPosition)==player.getColor()) {
						board.setMarkerPos(testPosition, MarkerType.EMPTY); 
						hit = true; 
					}
					testPosition = testPosition-board.cols; 
					} 
					return (i+1); 
				}
				else {
					boolean hit = false; 
					int testPosition = (i+1); 
					while(!hit) {
					if(board.getMarkerType(testPosition)==player.getColor()) {
						board.setMarkerPos(testPosition, MarkerType.EMPTY); 
						hit = true; 
					}
					testPosition = testPosition-board.cols; 
					} 
				}
				}
				
			//horizontal to the left
		else if (board.getMarkerType(i) == player.getColor() 
					&& i % board.cols > 0 
                    && i % board.cols < (board.cols - 1)
					&& board.getMarkerType(i-1) == MarkerType.EMPTY) {
			try {
				board.placeMove((i-1), player);
			} catch (NoSpaceLeftInColumnException e) {
				
			} 
			if(board.getMarkerType(i-1)==board.getMarkerType(i)) {
				boolean hit = false; 
				int testPosition = (i-1); 
				while(!hit) {
				if(board.getMarkerType(testPosition)==player.getColor()) {
					board.setMarkerPos(testPosition, MarkerType.EMPTY); 
					hit = true; 
				}
				testPosition = testPosition-board.cols; 
				}
				return (i-1);
			}
			else {
				boolean hit = false; 
				int testPosition = (i-1); 
				while(!hit) {
				if(board.getMarkerType(testPosition)==player.getColor()) {
					board.setMarkerPos(testPosition, MarkerType.EMPTY); 
					hit = true; 
				}
				testPosition = testPosition-board.cols; 
				}
			}
			 
			}

}
		// vertical
		for (int i = 0; i < largestPos; i++) {
			if(board.getMarkerType(i) == player.getColor()
					&& i / board.cols < (board.rows - 2)
					&& board.getMarkerType(i + board.cols) == MarkerType.EMPTY) {
				return (i + board.cols); 
			}
		}	
		
		
		//diagonal NE
		for (int i = 0; i < largestPos; i++) {
			if(board.getMarkerType(i) == player.getColor() 
					&& i % board.cols < (board.cols - 1)
					&& board.getMarkerType(i+1) == MarkerType.EMPTY) {
				try {
					board.placeMove((i+(board.cols+1)), player);
				} catch (NoSpaceLeftInColumnException e) {
					
				} 
				if(board.getMarkerType(i+(board.cols+1))==board.getMarkerType(i)) {
					boolean hit = false; 
					int testPosition = (i+(board.cols+1)); 
					while(!hit) {
					if(board.getMarkerType(testPosition)==player.getColor()) {
						board.setMarkerPos(testPosition, MarkerType.EMPTY); 
						hit = true; 
					}
					testPosition = testPosition-board.cols; 
					} 
					return (i+(board.cols+1)); 
				}
				else {
					boolean hit = false; 
					int testPosition = (i+(board.cols+1)); 
					while(!hit) {
					if(board.getMarkerType(testPosition)==player.getColor()) {
						board.setMarkerPos(testPosition, MarkerType.EMPTY); 
						hit = true; 
					}
					testPosition = testPosition-board.cols; 
					} 
				}
				}
		}
		
			//diagonal NW
			for (int i = 0; i < largestPos; i++) {
				if(board.getMarkerType(i) == player.getColor() 
						&& i % board.cols > 0 
	                    && i % board.cols < (board.cols - 1)
						&& board.getMarkerType(i+(board.cols-1)) == MarkerType.EMPTY) {
					try {
						board.placeMove((i+(board.cols-1)), player);
					} catch (NoSpaceLeftInColumnException e) {
						
					} 
					if(board.getMarkerType(i+(board.cols-1))==board.getMarkerType(i)) {
						boolean hit = false; 
						int testPosition = (i+(board.cols-1)); 
						while(!hit) {
						if(board.getMarkerType(testPosition)==player.getColor()) {
							board.setMarkerPos(testPosition, MarkerType.EMPTY); 
							hit = true; 
						}
						testPosition = testPosition-board.cols; 
						} 
						return (i+(board.cols-1)); 
					}
					else {
						boolean hit = false; 
						int testPosition = (i+(board.cols-1)); 
						while(!hit) {
						if(board.getMarkerType(testPosition)==player.getColor()) {
							board.setMarkerPos(testPosition, MarkerType.EMPTY); 
							hit = true; 
						}
						testPosition = testPosition-board.cols; 
						} 
					}
					}
}
			return -1;
	}
	
/**
 * 	Returns position to place column based on bot level.
 * @param board 
 * @param largestPos
 * @return Position for bot to play.
 */
public int getMove(Board board, int largestPos){
	if(level.equals("Easy")){
		return getMoveEasy(largestPos);
	}
	else if(level.equals("Medium")) {
		return getMoveMedium(board, largestPos);
	}
	else{
		return getMoveHard(board, new Player(MarkerType.RED, "opp"));
	}
		}
}