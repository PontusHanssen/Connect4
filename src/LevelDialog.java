import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class LevelDialog extends JDialog implements MouseListener{
	private JPanel panel;
	private JLabel levelChoice;
	private JButton easy, medium, hard; 
	private Game game; 
	
	public LevelDialog(Game game) {
		this.game = game; 
		
		setSize(300, 100);
		setResizable(true);
		setLocationRelativeTo(null);
	
		levelChoice = new JLabel("What level do you want to play?");
		easy = new JButton("Easy");
		easy.addMouseListener(this);
		medium = new JButton("Medium");
		medium.addMouseListener(this);
		hard = new JButton("Hard");
		hard.addMouseListener(this);
		panel = new JPanel();
		
		panel.add(levelChoice);
		panel.add(easy);
		panel.add(medium);
		panel.add(hard);
		add(panel);
		setVisible(true);
	}
	
	/**
	 * Performs action when a button is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(easy)){
			game.botPlayer.setLevel("Easy"); 
			dispose();
			}
			else if(e.getSource().equals(medium)) {
			game.botPlayer.setLevel("Medium"); 
			dispose();	
			}
			else {
			game.botPlayer.setLevel("Hard"); 
			dispose();
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
