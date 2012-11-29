import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Marker extends JPanel{
	private Color color;
	
	public Marker(MarkerType color) {
		switch(color) {
		case EMPTY:
			this.color = Color.WHITE;
			break;
		case RED:
			this.color = Color.RED;
			break;
		case YELLOW:
			this.color = Color.YELLOW;
			break;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Board.getColor());
		g.setColor(color);
		
		g.fillOval((getWidth()-90)/2, (getHeight()-90)/2, 90, 90);
		
	}

}
