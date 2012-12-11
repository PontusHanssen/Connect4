
public class Player implements Cloneable {
	
	private MarkerType color;
	public int moves=0;
	public String name;
	
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
	}
	
	

