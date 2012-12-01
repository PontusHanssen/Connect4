import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class Marker extends JPanel implements MouseInputListener {
	private Color color;
	private int pos;

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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int pos = this.pos;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
