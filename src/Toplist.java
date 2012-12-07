import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
import java.util.SortedMap;
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

	private SortedMap<Integer, String> toplist;

	public Toplist	() {
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		readToplist(); 
		JLabel toplista = new JLabel(print()); 
		JButton ok = new JButton("OK!");
		ok.addMouseListener(this); 
		JPanel panel = new JPanel(); 
		panel.add(toplista);
		panel.add(ok); 
		add(panel); 
		setVisible(false); 
	}

	private String print() {
		String printList = new String();
		int place = 1; 
		for(int i : toplist.keySet()) {
			printList.concat(place + ". " + toplist.get(i) + " : " + toplist.get(i) + "\n");  
			place++; 
		}
		return printList; 
		
	}
	
	public void saveToplist(Player winner) {
		File f1 = new File(System.getProperty("user.dir") + "/src/toplist.txt");
		String outputString = new String(); 
		try {
			BufferedOutputStream oStream = new BufferedOutputStream(new FileOutputStream(f1));
			
			for(int i : toplist.keySet()) {
				outputString.concat(String.valueOf(i) + "|" + toplist.get(i) + "\n");  
				}
			
			try {
				oStream.write(outputString.getBytes());
				oStream.close(); 
			} catch (IOException e) {

			} 
		} catch (FileNotFoundException e) {
		
		} 
		
		

	}

	public void readToplist() {
		File f1 = new File(System.getProperty("user.dir")+ "/src/toplist.txt");
		String file = new String();
		try {
			Scanner scanner = new Scanner(new FileInputStream(f1));
			while(scanner.hasNext()){
				file.concat(scanner.nextLine() + '\n');
			}
		} catch (FileNotFoundException e) {
			System.out.println(f1.getAbsolutePath() + ": Not found!");
		}
		String[] rows=file.split("\n");
		for(int i=0; i<rows.length;i++){
			String[] nameScore = rows[i].split("|");
			toplist.put(Integer.parseInt(nameScore[0]), nameScore[1]);
		}
		

		
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
