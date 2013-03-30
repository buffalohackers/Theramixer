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



class ui extends JComponent implements ComponentListener, WindowFocusListener, Runnable  {
	Toolkit tk = Toolkit.getDefaultToolkit();
	protected BufferedImage background;
    Camera stream = new Camera(this);
	
	public ui(JFrame frame) { 	
		
		frame.addComponentListener(this);
		frame.addWindowFocusListener(this);
		new Thread(this).start();        
	}	

	public void paintComponent(Graphics g) {
		Point pos = this.getLocationOnScreen();
		Point offset = new Point(-pos.x,-pos.y);
		g.drawImage(background,offset.x,offset.y,null);
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
