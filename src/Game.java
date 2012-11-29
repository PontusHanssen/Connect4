import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame {
	Board board;
	private JPanel panel;
	private int winWidth, winHeight;

	public Game(int winWidth, int winHeight, int cols, int rows) {
		super("Fyra i rad");
		this.winWidth = winWidth;
		this.winHeight = winHeight;

		board = new Board(rows, cols);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(winWidth, winHeight);
		setResizable(true);
		setLocationRelativeTo(null);
		setUndecorated(true);
		panel = new JPanel(new GridLayout(rows, cols));
		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		for(int pos=(rows*cols)-1; pos>=0; pos--) {
			panel.add(new Marker(board.getMarkerPos(pos)));
			
		}
		panel.setBackground(Board.getColor());
		add(panel);
		
		setVisible(true);
		repaint();
		runGame();
		repaint();
		invalidate();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Toolkit tk = Toolkit.getDefaultToolkit();
				int x = ((int) tk.getScreenSize().getWidth());
				int y = ((int) tk.getScreenSize().getHeight());
				new Game(x, y, 7, 6);
				
				
				
			}
		});
	}
	
	public void runGame() {
		Player p1 = new Player(MarkerType.RED);
		try {
			board.placeMove(0, p1);
			invalidate();
		} catch (NoSpaceLeftInColumnException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}


