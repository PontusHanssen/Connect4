import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.BufferedOutputStream;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;	
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Toplist extends JDialog implements MouseListener {

	private SortedMap<Integer, String> toplist = new TreeMap<Integer, String>();
	public JLabel toplista;
	
	/**
	 * Creates a new toplist.
	 * @param player Winner of current game.
	 */
	public Toplist (Player player) {
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		readToplist(); 
		updateToplist(player); 
		toplista = new JLabel(print());
		JButton ok = new JButton("OK!");
		ok.addMouseListener(this); 
		JPanel panel = new JPanel(); 
		panel.add(toplista);
		panel.add(ok); 
		add(panel); 
		setVisible(true); 
	}

	/**
	 * Puts together a string to be printed.
	 * @return String - printable toplist with the right format
	 */
	public String print() {
		String printList = "<html><body><table style='color: #ccc; border: 1px solid #000'><tr><th>Plats</th><th>Namn</th><th>Drag</th></tr>";
		int place = 1; 
		if(toplist.isEmpty()){
			return "No results";
		}
		for(int i : toplist.keySet()) {
			printList += "<tr><td>" + place + ".</td><td>" + toplist.get(i) + "</td><td>" + i + "</td></tr>";  
			place++; 
			if(place > 5){
				break;
			}
		}
		printList += "</body></html>";
		return printList; 
		
	}
	/**
	 * Saves the toplist to a file.
	 */
	public void saveToplist() {
		File f1 = new File(System.getProperty("user.dir") + "/src/toplist.txt");
		String outputString = new String(); 
		try {
			BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(f1));
			
			for(int i : toplist.keySet()) {
				outputString += (String.valueOf(i) + "#" + toplist.get(i) + "\n");  
				}
			System.out.println(outputString);
			try {
				oStream.write(outputString.getBytes());
				oStream.close(); 
			} catch (IOException e) {

			} 
		} catch (FileNotFoundException e) {
		
		} 
		
		

	}

	/**
	 * Reads the toplist from a file.
	 */
	public void readToplist() {
		File f1 = new File(System.getProperty("user.dir")+ "/src/toplist.txt");
		String file = new String();
		try {
			Scanner scanner = new Scanner(new FileInputStream(f1));
			while(scanner.hasNext()){
				String next = scanner.next();
				file += next + "\n";
			}
			file = file.substring(0, file.length()-1);
		} catch (FileNotFoundException e) {
			System.out.println(f1.getAbsolutePath() + ": Not found!");
		}
		if(file.length()<1) {
			System.out.println("Filen är tom");
			return;
		}
		String[] rows=file.split("\n");
		for(int i=0; i<rows.length;i++){
			String[] nameScore = rows[i].split("#");
			toplist.put(Integer.parseInt(nameScore[0]), nameScore[1]);
		}
		
		invalidate();
		repaint(); 
		
	}

	/**
	 * Updates the toplist when a player beats the score.
	 * @param winner Winner of current game.
	 */
	public void updateToplist(Player winner) {
			toplist.put(winner.moves, winner.name);
		
		invalidate();
		repaint(); 
	}
	
/**
 * Actions to be performed when mouse is clicked.
 */
	@Override
	public void mouseClicked(MouseEvent e) { 
		saveToplist();
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
