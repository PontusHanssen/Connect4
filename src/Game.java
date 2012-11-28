import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game{
	Board board;
	private int winWidth, winHeight;

	public Game(int cols, int rows, int winWidth, int winHeight) {
		this.winWidth = winWidth;
		this.winHeight = winHeight;
		
		board = new Board(rows, cols);
		JFrame frame = new JFrame("Fyra i rad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(winWidth, winWidth);
		//frame.setResizable(false);
		frame.add(new JPanel());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	public static void main(String[] args) {
	new Game(800, 600, 7, 6);
	}

//	private void print(Marker[][] board) {
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.drawOval(0, 0, 80, 80);
//		g2d.setColor(Color.white);
//
//	}

}
