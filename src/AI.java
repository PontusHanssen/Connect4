import java.util.Date;
import java.util.Random;

public class AI extends Player {

	public AI(MarkerType color) {
		super(color);
	}

	/**
	 * AI that generates a random number to place a marker in.
	 * @param largestPos The largest position number on the board.
	 * @return A position to place the next move.
	 */
	public int makeMoveRandom(int largestPos) {
		Random randomPos = new Random(new Date().getTime());
		int pos = randomPos.nextInt(largestPos);
		return pos;
	}

	/**
	 * AI that checks if it is possible to win in this move. Otherwise places a marker in random position.
	 * @param board Current game board.
	 * @param largestPos The largest position number on the board.
	 * @return A position to place the next move.
	 */
	public int makeMoveEasy(Board board, int largestPos) {
		
		for (int i = 0; i < largestPos; i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, this);
				if (workingBoard.checkWin() == this.getColor()) {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
					return i;
					
				} else {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
				}
			} catch (NoSpaceLeftInColumnException e) {

			}
			
		}

		return makeMoveRandom(largestPos);

	}

}
