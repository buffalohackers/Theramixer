import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Toolkit;


public class ui extends JPanel {
	Toolkit tk = Toolkit.getDefaultToolkit();	
	Camera stream = new Camera();
	
	public ui() { 	
		JLayeredPane overlays = new JLayeredPane();	 	
		overlays.setPreferredSize(new Dimension(stream.getWidth(), stream.getHeight()));
		overlays.setBorder(BorderFactory.createTitledBorder("Testing out some fuck."));
		overlays.add(stream, 0);
		overlays.add(createGrid("Testing I guess"), 1);		
	}
	
	public JLabel createGrid(String text) {
		JLabel grid = new JLabel(text);
		grid.setVerticalAlignment(JLabel.CENTER);
		grid.setHorizontalAlignment(JLabel.CENTER);
		grid.setOpaque(true);
		grid.setBackground(Color.red);
		grid.setBounds(0, 0, stream.getWidth(), stream.getHeight());
		grid.setBorder(BorderFactory.createLineBorder(Color.black));
		return grid;
	}
	
	public static void dOverlayCamera() {
		JFrame frame = new JFrame("Monome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setUndecorated(false);
        
		JComponent testing = new ui();
		testing.setOpaque(true); //content panes must be opaque
        frame.setContentPane(testing);
        
        frame.setVisible(true);	
        frame.pack();
		
	}

}
