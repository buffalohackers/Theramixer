import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class monome {
	
	public static void main(String[] args) {		
		JFrame frame = new JFrame("Transparent Window");
		ui bg = new ui(frame);
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
