
public class Player implements Cloneable {
	
	private MarkerType color;
	public int moves=0;
	
	public Player(MarkerType color) {
		this.color = color;   
		}
	
	public MarkerType getColor() {
		return this.color; 
	}
	
	public int getMoves(){
		return this.moves; 
	}
	}
	
	

