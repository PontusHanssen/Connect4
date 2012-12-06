import java.util.Date;
import java.util.Random;

public class AI extends Player {
	String level;

	public AI(MarkerType color, String level) {
		super(color);
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
	public int getMoveMedium(Board board, int largestPos, boolean fromHard) {

		int falseMoves = 0;
		for (int i = 0; i < largestPos; i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, this);
				falseMoves++;
				if (workingBoard.checkWin() == this.getColor()) {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
					moves -= falseMoves;
					return i;

				} else {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
				}
			} catch (NoSpaceLeftInColumnException e) {

			}

		}
		moves -= falseMoves;
		if (fromHard) {
			return -1;
		} else {
			return getMoveEasy(largestPos);
		}
	}

	/**
	 * Position calculated by our supermegaawesome algorithm!!!11
	 * @param board
	 * @param largestPos
	 * @return
	 */
	public int getMoveHard(Board board, int largestPos) {
		if (getMoveMedium(board, largestPos, true) > 0) {
			return getMoveMedium(board, largestPos, true);
		} else {

			for (int pos = 0; pos < largestPos; pos++) {
				if (board.getMarkerType(pos).equals(MarkerType.EMPTY)) {
					// right
					if (pos % board.cols < board.cols - 1) {

						if (board.getMarkerType(pos + 1).equals(getColor())) {
							return pos;
						}
					}

					// down
					if (pos / (board.cols - 1) > 0) {
						if (board.getMarkerType(pos - (board.cols - 1)).equals(
								getColor())) {
							return pos;
						}

					}

				}
			}
			return getMoveEasy(largestPos);
		}

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
		return getMoveMedium(board, largestPos, true);
	}
	else{
		return getMoveHard(board, largestPos);
	}
		}
}