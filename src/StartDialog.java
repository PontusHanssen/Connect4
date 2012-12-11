import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


public class StartDialog extends JDialog implements MouseListener{
	
	private JPanel panel;
	private JButton human, bot;
	private Game game;
	
	/**
	 * Creates a start dialog where type of game is chosen.
	 * @param game The current game.
	 */
	public StartDialog(Game game){
		setSize(250, 100);
		setResizable(true);
		setLocationRelativeTo(null);
		
		this.game = game;
		panel = new JPanel();
		human = new JButton("Human VS Human");
		bot = new JButton("Human VS Bot");
		human.addMouseListener(this);
		bot.addMouseListener(this);
		
		panel.add(human);
		panel.add(bot);
		add(panel);
		

		setVisible(true);
	}

	/**
	 * Performs action when a button is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(human)) {
			game.bot = false;
		}
		else if(e.getSource().equals(bot)) {
			game.bot = true;
				new LevelDialog(game); 
		}
		new PlayerNames(game);
		dispose();
		
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
