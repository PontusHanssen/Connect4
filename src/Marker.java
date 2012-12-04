import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Marker extends JPanel {
	private Color color;
	private int pos;
	JLabel poslabel;

	public Marker(MarkerType color, int pos) {
		this.pos = pos;
		switch (color) {
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
		
		poslabel = new JLabel(String.valueOf(pos/7) + "/" + String.valueOf(pos%7));
		add(poslabel);
	}

	public void setNewColor() {
		MarkerType marker = Game.getBoardMarker(pos);
		switch (marker) {
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
		setNewColor();
		setBackground(Board.getColor());
		g.setColor(color);

		g.fillOval((getWidth() - 90) / 2, (getHeight() - 90) / 2, 90, 90);

	}

	public int getPos() {
		return pos;
	}

	

}
