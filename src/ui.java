import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Toolkit;


public class ui {
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	public ui() {
		Dimension d = tk.getScreenSize();
	 	JFrame frame = new JFrame("Transparent Window");
        frame.setSize(d.width, d.height);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(false);

        frame.setBackground(new Color(255, 255, 255, 255));	      
        frame.getContentPane().add(new Camera());
        frame.getRootPane().putClientProperty("apple.awt.draggableWindowBackground", false);
        frame.getContentPane().setLayout(new java.awt.BorderLayout());
        frame.setVisible(true);	
	}

}
