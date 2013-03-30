import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;


public class ui {
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	public ui() {
		Dimension d = tk.getScreenSize();
	 	JFrame frame = new JFrame("Transparent Window");
	 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(d.width, d.height);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(false);	      
        frame.getContentPane().add(new Camera(this));
        frame.setVisible(true);	
	}
	
	public void nextFrame(BufferedImage image) {
		
	}

}
