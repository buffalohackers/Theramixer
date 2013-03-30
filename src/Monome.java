import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Monome {
	
	public static void main(String[] args) {		
		JFrame frame = new JFrame("Monome Player");
		UserInterface bg = new UserInterface(frame);
		bg.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.getContentPane().add("Center",bg);
		
		frame.pack();
		frame.setSize(bg.cameraWidth(),bg.cameraHeight());
		frame.setVisible(true); 
		
		ArrayList<Sample> samples = Sample.samplesInCurrentDirectory();		
		samples.get(0).start();	       
		
	}
}