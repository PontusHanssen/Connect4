import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends JFrame implements MouseListener {
	public static Board board;
	private JPanel gamePanel, statsPanel, containerPanel;
	private int winWidth, winHeight, rows, cols;
	private Player p1 = new Player(MarkerType.RED);
	private Player p2 = new Player(MarkerType.YELLOW); 
	private Player currentPlayer;

	public Game(int winWidth, int winHeight, int cols, int rows) {
		super("Fyra i rad");
		this.rows = rows;
		this.cols = cols;
		this.winWidth = winWidth;
		this.winHeight = winHeight;
		currentPlayer = p1;

		board = new Board(rows, cols);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(winWidth, winHeight);
		setResizable(true);
		setLocationRelativeTo(null);
		setUndecorated(true);
		containerPanel = new JPanel();
		statsPanel = new JPanel();
		gamePanel = new JPanel(new GridLayout(rows, cols));
		GridBagLayout gridbagLayoutSettings = new GridBagLayout();
		containerPanel.setLayout(gridbagLayoutSettings);
		GridBagConstraints gb = new GridBagConstraints();
		
		statsPanel.add(new JLabel("lol"));
		statsPanel.setBackground(board.getColor());
		
		
		
		
		
		
		for(int pos=(rows*cols)-cols; pos>=0; pos-=cols) {
			
			
			for(int i = 0; i<cols; i++) {
				//panel.add(new Marker(board.getMarkerPos(pos+i), pos+i));
				Marker marker = new Marker(board.getMarkerType(pos+i), pos+i);
				marker.addMouseListener(this);
				gamePanel.add(marker);
				
			}
			
		}
		gamePanel.setBackground(Board.getColor());
		gb.weighty = 1.0;
		gb.weightx = 1.0;
		gb.gridx=0;
		gb.gridy=0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		
		containerPanel.add(statsPanel, gb);
		
		gb.gridx=0;
		gb.gridy=1;
		gb.fill = GridBagConstraints.BOTH;
		containerPanel.add(gamePanel, gb);
		add(containerPanel);
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
		return board.getMarkerType(pos);
	}
	
	public void runGame() {

		


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Marker clicked = (Marker) e.getSource();
		try {
			board.placeMove(clicked.getPos(), currentPlayer);
			currentPlayer.moves++;
			if(board.getMarkerType(clicked.getPos()) == board.checkWin()){
				System.out.println("WINWINWIN" + board.checkWin());
			}
			if(currentPlayer == p1){
				currentPlayer = p2;
				
			}
			else{
				currentPlayer = p1;
				
			}
		} catch (NoSpaceLeftInColumnException e1) {
			
		}
		repaint();
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

}


