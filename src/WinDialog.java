import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class WinDialog extends JDialog implements MouseListener{
	private JPanel panel;
	private JLabel text;
	private JButton newGame, exit;
	private Game game;
	public WinDialog(Player player, Game game) {
		this.game = game;
		
		setSize(250, 100);
		setResizable(true);
		setLocationRelativeTo(null);
	
		text = new JLabel("Player " + player.getColor() + " won after " + player.moves + " moves!");
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
