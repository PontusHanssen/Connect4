

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates a Dialog-window when game is over to show the winner and give the option to start a new game or exit.
 * @author Tova Linder och Pontus Persson
 *
 */
@SuppressWarnings("serial")
public class WinDialog extends JDialog implements MouseListener{
	private JPanel panel;
	private JLabel text;
	private JButton newGame, exit;
	private Game game;
	
	/**
	 * Creates a Dialog when there is a winner.
	 * @param player Winner of the game.
	 * @param game The current game.
	 */
	public WinDialog(Player player, Game game) {
		this.game = game;
		
		setSize(250, 100);
		setResizable(true);
		setLocationRelativeTo(null);
	
		text = new JLabel("Player " + player.getName() + " won after " + player.getMoves() + " moves!");
		newGame = new JButton("New game");
		exit = new JButton("Exit game");
		exit.addMouseListener(this);
		newGame.addMouseListener(this);
		panel = new JPanel();
		
		panel.add(text);
		panel.add(newGame);
		panel.add(exit);
		add(panel);
		setVisible(true);
		Toplist toplist = new Toplist(player); 
		toplist.invalidate(); 
		toplist.repaint(); 
	}
	
	/**
	 * Creates a Dialog when the game is a tie.
	 */
	public WinDialog() {
		setSize(250, 100);
		setResizable(true);
		setLocationRelativeTo(null);
	
		text = new JLabel("Out of space :( The game was a tie");
		newGame = new JButton("New game");
		exit = new JButton("Exit game");
		exit.addMouseListener(this);
		newGame.addMouseListener(this);
		panel = new JPanel();
		
		panel.add(text);
		panel.add(newGame);
		panel.add(exit);
		add(panel);
		setVisible(true);
	}
	
	/**
	 * Performs action when a button is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
			if(e.getSource().equals(newGame)){
			game.resetGame();
			dispose();
			}
			else if(e.getSource().equals(exit)) {
				System.exit(0);
			}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
