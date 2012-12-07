import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.SortedMap;
import java.io.File; 

import javax.swing.JDialog;

import com.sun.java.util.jar.pack.Package.File;

public class Toplist extends JDialog implements MouseListener {

	private SortedMap<Integer, String> toplist;

	public Toplist() {
		
		
	}

	public void saveToplist(Player winner) {

	}

	public String readToplist() {
		File file = new File(System.getProperty("user.dir")+ "/src/toplist.txt");
		
	}

	public void updateToplist(Player winner) {
		if (toplist.isEmpty()) {
			toplist.put(winner.moves, winner.name);
		} else {
			if (toplist.lastKey() > winner.moves) {
				toplist.put(winner.moves, winner.name);
				toplist.remove(toplist.lastKey());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
