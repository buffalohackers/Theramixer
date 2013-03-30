import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Monome {
	
	public static void main(String[] args) {		
		JFrame frame = new JFrame("Monome Player");
		
		UserInterface bg = new UserInterface(frame);
		Controller controller = new Controller();
		Camera camera = new Camera(bg, controller);
		bg.setStream(camera);
		bg.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.getContentPane().add("Center",bg);
		
		frame.pack();
		frame.setSize(bg.screenWidth(),bg.screenHeight());
		frame.setVisible(true);
		
	}
}
