/**
 * Creates a new player for the game. Player has a name and a variable to store number of moves.
 * @author Tova Linder och Pontus Persson
 *
 */
public class Player implements Cloneable {
	
	private MarkerType color;
	private int moves=0;
	private String name;
	
	/**
	 * Creates a new player.
	 * @param color
	 * @param name
	 */
	public Player(MarkerType color, String name) {
		this.color = color;   
		this.name = name;
		}
	
	/**
	 * 
	 * @return Returns the MarkerType of the player.
	 */
	public MarkerType getColor() {
		return this.color; 
	}
	
	/**
	 * 
	 * @return Returns the number of moved made by the player.
	 */
	public int getMoves(){
		return this.moves; 
	}
	
	public void addMove() {
		this.moves ++; 
	}
	public void resetMoves() {
		this.moves = 0; 
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name; 
	}
}
	

