import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerNames extends JDialog implements MouseListener {

	private JPanel panel;
	private JLabel label;
	private JTextField red, yellow;
	private JButton button;
	private Game game;


	public PlayerNames(Game game) {

		setSize(250, 100);
		setResizable(true);
		setLocationRelativeTo(null);

		this.game = game;
		
		panel = new JPanel();
		label = new JLabel("Insert you names!");
		red = new JTextField("Red");
		if (!game.bot) {
			yellow = new JTextField("Yellow");
		} else {
			yellow = new JTextField("Bot");
			yellow.setEditable(false);
		}
		button = new JButton("OK");
		button.addMouseListener(this);

		panel.add(label);
		panel.add(red);
		panel.add(yellow);
		panel.add(button);
		add(panel);

		setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		game.playerRed.name = red.getText();
		if (!game.bot) {
			game.playerYellow.name = yellow.getText();
		} else {
			game.botPlayer.name = yellow.getText();
		}
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
