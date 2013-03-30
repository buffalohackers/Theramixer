import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;

class UserInterface extends JComponent implements ComponentListener, WindowFocusListener, Runnable  {
	Toolkit tk = Toolkit.getDefaultToolkit();
	protected BufferedImage background;
    Camera stream = new Camera(this);
    boolean x = true;
	
	public UserInterface(JFrame frame) { 		
		frame.addComponentListener(this);
		frame.addWindowFocusListener(this);
		new Thread(this).start();        
	}	

	public void paintComponent(Graphics g) {
		int blockWidth = stream.getWidth()/3;
		int blockHeight = stream.getHeight()/2;
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                    RenderingHints.VALUE_ANTIALIAS_ON);
		
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-stream.getWidth(), 0);
		
		AffineTransformOp op = new AffineTransformOp(tx, 
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		background = op.filter(background, null);
		   
		g.drawImage(background,0,0,null);
		if (x) {
			g.drawLine(blockWidth, 0, (blockWidth)+2, stream.getHeight());
			g.drawLine(2*blockWidth, 0, (2*blockWidth)+2, stream.getHeight());
			g.drawLine(0, blockHeight, stream.getWidth(), blockHeight+2);
		} else {
			g.drawLine(blockWidth, 0, (blockWidth)+2, stream.getHeight());
			g.drawLine(blockWidth+200, 0, (blockWidth)+202, stream.getHeight());
		}
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
