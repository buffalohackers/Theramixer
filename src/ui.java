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
	 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(d.width, d.height);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(false);	      
        frame.getContentPane().add(new Camera());
        frame.setVisible(true);	
	}

}
