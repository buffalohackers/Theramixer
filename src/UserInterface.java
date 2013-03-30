import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

class UserInterface extends JComponent implements ComponentListener, WindowFocusListener, Runnable  {
	Toolkit tk = Toolkit.getDefaultToolkit();
	protected BufferedImage background;
    Camera stream = new Camera(this);
	
	public UserInterface(JFrame frame) { 	
		
		frame.addComponentListener(this);
		frame.addWindowFocusListener(this);
		new Thread(this).start();        
	}	

	public void paintComponent(Graphics g) {
		int blockWidth = stream.getWidth()/3;
		int blockHeight = stream.getHeight()/2;
		g.drawImage(background,0,0,null);
		g.drawLine(blockWidth, 0, (blockWidth)+2, stream.getHeight());
		g.drawLine(2*blockWidth, 0, (2*blockWidth)+2, stream.getHeight());
		g.drawLine(0, blockHeight, stream.getWidth(), blockHeight+2);
	}
	
	public void nextFrame(BufferedImage img) {
			background = img;
			repaint();
	}
	
	public int cameraWidth() {
		return stream.getWidth();
	}
	
	public int cameraHeight() {
		return stream.getHeight();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
