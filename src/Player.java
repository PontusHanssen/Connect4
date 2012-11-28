
public class Player implements Cloneable {
	
	private Marker color;
	public int moves=0; 
	
	public Player(Marker color) {
		this.color = color;   
		}
	
	public Marker getColor() {
		return this.color; 
	}
	
	public int getMoves(){
		return this.moves; 
	}
	}
	
	

