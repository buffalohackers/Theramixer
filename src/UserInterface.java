import java.awt.Color;
import java.awt.Font;
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
    Camera stream;
    String recStatus = "Not Recording";
    boolean x = true;
	
	public UserInterface(JFrame frame) { 		
		frame.addComponentListener(this);
		frame.addWindowFocusListener(this);
		new Thread(this).start();        
	}	
	public void paintComponent(Graphics g) {
		if (background != null) {
			float xRatio = (float) tk.getScreenSize().width/stream.getWidth();
			float yRatio = (float) tk.getScreenSize().height/stream.getHeight();
			int StreamWidth = stream.getWidth();
			int screenHeight = tk.getScreenSize().height;
			int screenWidth = tk.getScreenSize().width;
			int blockWidth = screenWidth/3;
			int blockHeight = screenHeight/2;
			
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			                    RenderingHints.VALUE_ANTIALIAS_ON);		
			
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.scale(xRatio, yRatio);
			tx.translate(-StreamWidth, 0);
			
			AffineTransformOp op = new AffineTransformOp(tx, 
	                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			background = op.filter(background, null);
			   
			g.drawImage(background,0,0,null);
			
			if (x) {
				g.setFont( new Font( "Arial", Font.BOLD, 50 ) );
				g.setColor(new Color(255, 255, 255, 255));
				g.drawString("Change Mode", blockWidth/5, blockHeight/2);
				g.drawString("Mix 01", blockWidth/3+blockWidth, blockHeight/4);
				g.drawString("Mix 03", blockWidth/3+(blockWidth*2), blockHeight/4);
				g.drawString("Record Loop", blockWidth/4, blockHeight+(blockHeight/4));
				g.drawString(recStatus, blockWidth/4, blockHeight+(blockHeight/2));
				g.drawString("Mix 02", blockWidth/3+blockWidth, blockHeight/4+blockHeight);
				g.drawString("Mix 04", blockWidth/3+(blockWidth*2), blockHeight/4+blockHeight);
				g.drawLine(blockWidth, 0, (blockWidth)+3, screenHeight);
				g.drawLine(2*blockWidth, 0, (2*blockWidth)+3, screenHeight);
				g.drawLine(0, blockHeight, screenWidth, blockHeight+3);
			} else {
				g.drawLine(blockWidth, 0, (blockWidth)+2, stream.getHeight());
				g.drawLine(blockWidth+200, 0, (blockWidth)+202, stream.getHeight());
			}
		}
	}
	
	public void setStream(Camera _camera) {
		stream = _camera;
	}
	

	public void nextFrame(BufferedImage img, String status) {
		recStatus = status;
		background = img;
		repaint();
	}
	
	public int screenWidth() {
		return tk.getScreenSize().width;
	}
	
	public int screenHeight() {
		return tk.getScreenSize().width;
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
