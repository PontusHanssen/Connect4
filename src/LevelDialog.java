import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LevelDialog extends JDialog implements MouseListener {
	private JPanel panel;
	private JLabel levelChoice;
	private JButton easy, medium, hard;
	private Game game;

	/**
	 * Creates a dialog where player can chose level.
	 * 
	 * @param game
	 */
	public LevelDialog(Game game) {
		this.game = game;

		setSize(300, 200);
		setResizable(true);
		setLocationRelativeTo(null);

		levelChoice = new JLabel("What level do you want to play?");
		easy = new JButton("Easy");
		easy.addMouseListener(this);
		medium = new JButton("Medium");
		medium.addMouseListener(this);
		hard = new JButton("Hard");
		hard.addMouseListener(this);
		panel = new JPanel(new GridBagLayout());

		GridBagConstraints gb = new GridBagConstraints();

		levelChoice.setBackground(Board.getColor());
		gb.weighty = 0.0;
		gb.weightx = 1.0;
		gb.gridx = 0;
		gb.gridy = 0;
		// gb.gridheight=1;
		gb.fill = GridBagConstraints.HORIZONTAL;

		panel.add(levelChoice, gb);

		gb.gridx = 0;
		gb.gridy = 1;
		gb.weighty = 1.0;
		gb.weightx = 1.0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		panel.add(easy, gb);

		gb.gridx = 0;
		gb.gridy = 2;
		gb.weighty = 1.0;
		gb.weightx = 1.0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		panel.add(medium, gb);

		gb.gridx = 0;
		gb.gridy = 3;
		gb.weighty = 1.0;
		gb.weightx = 1.0;
		gb.fill = GridBagConstraints.HORIZONTAL;
		panel.add(hard, gb);

		add(panel);
		setVisible(true);

	}

	/**
	 * Performs action when a button is clicked.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(easy)) {
			game.botPlayer.setLevel("Easy");
			dispose();
		} else if (e.getSource().equals(medium)) {
			game.botPlayer.setLevel("Medium");
			dispose();
		} else {
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
