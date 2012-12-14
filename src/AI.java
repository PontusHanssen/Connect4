import java.util.Date;
import java.util.Random;

/**
 * Creates a bot-player and calculates positions for the bot-player's moves.
 * @author Tova Linder och Pontus Persson
 *
 */
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
 * Returns position for the easy bot
 * @param board
 * @param opponent
 * @return
 */
	public int getMoveEasy(Board board, Player opponent) {
		if(canWin(board, this) > -1) {
			return canWin(board, this);
		}
		else if(canWin(board, opponent) > -1) {
			return canWin(board, opponent);
		}
		else if(getMiddlePosition(board, this) > -1) {
			return getMiddlePosition(board, this);
		}
		else if(nextTo(board, this) > -1) {
			return nextTo(board, this);
		}
		else {
			return random(board);
		}
		}



	/**
	 * Position calculated by our supermegaawesome algorithm!!!11
	 * @param board
	 * @param opponent
	 * @return
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
		else if(getMiddlePosition(board, this) > -1) {
			return getMiddlePosition(board, this);
		}
		else if(nextTo(board, this) > -1) {
			return nextTo(board, this);
		}
		else {
			return random(board);
		}
	}

	/**
	 * Sets level for the bot.
	 * @param level 
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
	private int random(Board board) {
		Random randomPos = new Random(new Date().getTime());
		int pos = randomPos.nextInt(board.getRows()*board.getCols()-1);
		return pos;
	}

	private int twoStepsAhead(Board board, Player currentPlayer) {
		Player player = new Player(currentPlayer.getColor(), "Fake Player");
		int largestPos = board.getCols() * board.getRows() -1;
		for(int i=0;i < largestPos; i++){
			Board firstBoard = board.clone();
			try{
				firstBoard.placeMove(i, player);
				for(int k=0;k<largestPos;k++) {
					Board secoundBoard = firstBoard.clone();
					secoundBoard.placeMove(k, player);
					if(secoundBoard.checkWin(player) == player.getColor()) {
						if(firstBoard.checkWinDirection(player, 1, 0) == player.getColor()){
							return -1;
						}
						firstBoard.setMarkerPos(i, MarkerType.EMPTY);
						firstBoard.setMarkerPos(k, MarkerType.EMPTY);
						if(firstBoard.checkWinDirection(player, 1, 0) == player.getColor()){
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
		int largestPos = board.getCols() * board.getRows() -1;
		for(int i=0;i<largestPos;i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, player);
				if(workingBoard.checkWin(player) == player.getColor()) {
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
	
	private int getMiddlePosition(Board board, Player currentPlayer) {
		int middle = board.getCols()/2;
		if(board.getMarkerType(middle) == MarkerType.EMPTY) {
			return middle;
		}
		else {
			return -1;
		}
	}
	
	private int nextTo(Board board, Player currentPlayer) {
int largestPos = board.getCols()*board.getRows()-1; 
		Player player = new Player(currentPlayer.getColor(), "Fake Player");
		
		//horizontal to the right
		for (int i = 0; i < largestPos; i++) {
			if(board.getMarkerType(i) == player.getColor() 
					&& i % board.getCols() < (board.getCols() - 1)
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
					testPosition = testPosition-board.getCols(); 
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
					testPosition = testPosition-board.getCols(); 
					} 
				}
				}
				
			//horizontal to the left
		else if (board.getMarkerType(i) == player.getColor() 
					&& i % board.getCols() > 0 
                    && i % board.getCols() < (board.getCols() - 1)
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
				testPosition = testPosition-board.getCols(); 
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
				testPosition = testPosition-board.getCols(); 
				}
			}
			 
			}

}
		// vertical
		for (int i = 0; i < largestPos; i++) {
			if(board.getMarkerType(i) == player.getColor()
					&& i / board.getCols() < (board.getRows() - 2)
					&& board.getMarkerType(i + board.getCols()) == MarkerType.EMPTY) {
				return (i + board.getCols()); 
			}
		}	
		
		
		//diagonal NE
		for (int i = 0; i < largestPos; i++) {
			if(board.getMarkerType(i) == player.getColor() 
					&& i % board.getCols() < (board.getCols() - 1)
					&& i/board.getCols() <= board.getRows()-2
					&& board.getMarkerType(i+1) == MarkerType.EMPTY) {
				try {
					board.placeMove((i+(board.getCols()+1)), player);
				} catch (NoSpaceLeftInColumnException e) {
					
				} 
				if(board.getMarkerType(i+(board.getCols()+1))==board.getMarkerType(i)) {
					boolean hit = false; 
					int testPosition = (i+(board.getCols()+1)); 
					while(!hit) {
					if(board.getMarkerType(testPosition)==player.getColor()) {
						board.setMarkerPos(testPosition, MarkerType.EMPTY); 
						hit = true; 
					}
					testPosition = testPosition-board.getCols(); 
					} 
					return (i+(board.getCols()+1)); 
				}
				else {
					boolean hit = false; 
					int testPosition = (i+(board.getCols()+1)); 
					while(!hit) {
					if(board.getMarkerType(testPosition)==player.getColor()) {
						board.setMarkerPos(testPosition, MarkerType.EMPTY); 
						hit = true; 
					}
					testPosition = testPosition-board.getCols(); 
					} 
				}
				}
		}
		
			//diagonal NW
			for (int i = 0; i < largestPos; i++) {
				if(board.getMarkerType(i) == player.getColor() 
						&& i % board.getCols() > 0 
	                    && i % board.getCols() < (board.getCols() - 1)
	                    && i/board.getCols() <= board.getRows()-2
						&& board.getMarkerType(i+(board.getCols()-1)) == MarkerType.EMPTY) {
					try {
						board.placeMove((i+(board.getCols()-1)), player);
					} catch (NoSpaceLeftInColumnException e) {
						
					} 
					if(board.getMarkerType(i+(board.getCols()-1))==board.getMarkerType(i)) {
						boolean hit = false; 
						int testPosition = (i+(board.getCols()-1)); 
						while(!hit) {
						if(board.getMarkerType(testPosition)==player.getColor()) {
							board.setMarkerPos(testPosition, MarkerType.EMPTY); 
							hit = true; 
						}
						testPosition = testPosition-board.getCols(); 
						} 
						return (i+(board.getCols()-1)); 
					}
					else {
						boolean hit = false; 
						int testPosition = (i+(board.getCols()-1)); 
						while(!hit) {
						if(board.getMarkerType(testPosition)==player.getColor()) {
							board.setMarkerPos(testPosition, MarkerType.EMPTY); 
							hit = true; 
						}
						testPosition = testPosition-board.getCols(); 
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
		return getMoveEasy(board, new Player(MarkerType.RED, "opp"));
	}
	else{
		return getMoveHard(board, new Player(MarkerType.RED, "opp"));
	}
		}
}