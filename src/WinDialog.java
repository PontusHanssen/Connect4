import javax.swing.JDialog;
import javax.swing.JLabel;


@SuppressWarnings("serial")
public class WinDialog extends JDialog{

	private JLabel text;
	public WinDialog(Player player) {
		
		setSize(250, 250);
		setResizable(true);
		setLocationRelativeTo(null);
	
		text = new JLabel("Player " + player.getColor() + " won after " + player.moves + " moves!");
		add(text);
		setVisible(true);
	}
}
