
public class Player implements Cloneable {
	
	private MarkerType color;
	public int moves=0;
	
	public Player(MarkerType color) {
		this.color = color;   
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
	
	

