import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Monome {
	
	public static void main(String[] args) {		
		JFrame frame = new JFrame("Transparent Window");
		UserInterface bg = new UserInterface(frame);
		bg.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("This is a label");
		bg.add("South",label);
		frame.getContentPane().add("Center",bg);
		frame.pack();
		frame.setSize(bg.cameraWidth(),bg.cameraHeight());
		frame.setVisible(true);
	}
}
