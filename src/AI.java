import java.util.Date;
import java.util.Random;

public class AI extends Player {

	public AI(MarkerType color) {
		super(color);
	}

	public int makeMoveRandom(int largestPos) {
		Random randomPos = new Random(new Date().getTime());
		int pos = randomPos.nextInt(largestPos);
		return pos;
	}

	public int makeMoveEasy(Board board, int largestPos) {
		
		for (int i = 0; i < largestPos; i++) {
			Board workingBoard = board.clone();
			try {
				workingBoard.placeMove(i, this);
				if (workingBoard.checkWin() == this.getColor()) {
					workingBoard.setMarkerPos(i, MarkerType.EMPTY);
					System.out.println("lol");
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
