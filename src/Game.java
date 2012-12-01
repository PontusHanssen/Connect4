import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame implements OnClick{
	public static Board board;
	private JPanel panel;
	private int winWidth, winHeight, rows, cols;

	public Game(int winWidth, int winHeight, int cols, int rows) {
		super("Fyra i rad");
		this.rows = rows;
		this.cols = cols;
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
		
		for(int pos=(rows*cols)-cols; pos>=0; pos-=cols) {
			
			
			for(int i = 0; i<cols; i++) {
				panel.add(new Marker(board.getMarkerPos(pos+i), pos+i));
			}
		}
		panel.setBackground(Board.getColor());
		add(panel);
		
		setVisible(true);
		
		runGame();

		
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

	public static MarkerType getBoardMarker(int pos) {
		return board.getMarkerPos(pos);
	}
	
	public void runGame() {
		Player p1 = new Player(MarkerType.RED);
		Player p2 = new Player(MarkerType.YELLOW); 
		
			invalidate();
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			
			
		} catch (NoSpaceLeftInColumnException e) {
			// TODO Auto-generated catch block
			return;
			
		
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		board.placeMov( , player)
	}

}


