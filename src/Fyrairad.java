import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Fyrairad extends JFrame{
	
	public Fyrairad() {
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("My test program");
		setVisible(true);
		
		
		JLabel hello = new JLabel("Hello!");
		hello.setLocation(10, 10);
		hello.setBackground(Color.black);
		hello.setForeground(Color.GREEN);
		
		
		JPanel panel = new JPanel();
		panel.setLocation(10, 10);
		panel.setBackground(Color.blue);
		add(panel);
		panel.add(hello);
		
	}
	public void action(ActionEvent e) {
		
	}
	public static void main(String[] args) {
		
		new Fyrairad();
	}

}
 	