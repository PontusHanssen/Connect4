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
	public int getMoveHard(Board board, int largestPos) {
		for (int i = 0; i < largestPos; i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, new Player(MarkerType.RED, "Fake player"));
				if (workingBoard.checkWin() == MarkerType.RED) {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
					return i;
				} else {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
				}
			} catch (NoSpaceLeftInColumnException e) {
			}
		}		
		
		return getMoveMedium(board, largestPos);
	}

	/**
	 * Sets level for the bot.
	 * @param level 
	 */
	public void setLevel(String level) {
		this.level = level;
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
		return getMoveHard(board, largestPos);
	}
		}
}