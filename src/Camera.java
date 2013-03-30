import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Hashtable;
import javax.swing.JPanel;
import au.edu.jcu.v4l4j.Control;
import au.edu.jcu.v4l4j.FrameGrabber;
import au.edu.jcu.v4l4j.CaptureCallback;
import au.edu.jcu.v4l4j.V4L4JConstants;
import au.edu.jcu.v4l4j.VideoDevice;
import au.edu.jcu.v4l4j.VideoFrame;
import au.edu.jcu.v4l4j.exceptions.StateException;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;

public class Camera extends JPanel implements CaptureCallback, MouseListener {
	private static int width = 1280, height = 720, std = V4L4JConstants.STANDARD_WEBCAM, channel = 0;
	private static String device = "/dev/video0";
	private static int[] lowerThreshold = new int[]{200, 20, 100};
	private static int[] upperThreshold = new int[]{255, 200, 160};
	private int[][][] result;
	

	private VideoDevice videoDevice;
	private FrameGrabber frameGrabber;
	private UserInterface ui;

	public Camera(UserInterface _ui) {
		ui = _ui;
		// Initialise video device and frame grabber
		try {
			videoDevice = new VideoDevice(device);
			frameGrabber = videoDevice.getJPEGFrameGrabber(width, height, channel, std, 20);
			frameGrabber.setCaptureCallback(this);
			width = frameGrabber.getWidth();
			height = frameGrabber.getHeight();
			Hashtable<String, Control> controls = videoDevice.getControlList().getTable();
			
			System.out.println(controls);
			frameGrabber.setFrameInterval(1, 30);
			System.out.println("Starting capture at "+width+"x"+height);
		} catch (V4L4JException e1) {
			System.err.println("Error setting up capture");
			e1.printStackTrace();

			cleanupCapture();
			return;
		}

		// start capture
		try {
			frameGrabber.startCapture();
		} catch (V4L4JException e){
			System.err.println("Error starting the capture");
			e.printStackTrace();
		}
		
		addMouseListener(this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void cleanupCapture() {
		try {
			frameGrabber.stopCapture();
		} catch (StateException ex) {}

		// release the frame grabber and video device
		videoDevice.releaseFrameGrabber();
		videoDevice.release();
	}

	@Override
	public void nextFrame(VideoFrame frame) {
		BufferedImage image = frame.getBufferedImage();

		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

		int[][][] newResult = new int[width][height][3];
		final int pixelLength = 3;
		for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
			int argb = 0;
			argb += -16777216; // 255 alpha
			argb += ((int) pixels[pixel] & 0xff); // blue
			argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
			argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
			int r = ((argb >> 16) & 0x000000FF);
			int g = ((argb >> 4) & 0x000000FF);
			int b = ((argb) & 0x000000FF);
			newResult[col][row][0] = r;
			newResult[col][row][1] = g;
			newResult[col][row][2] = b;
			
			col++;
			if (col == width) {
				col = 0;
				row++;
			}
		}
		
		int numX = 3;
		int numY = 2;
		
		int[][] colorPercentage = new int[numX][numY];
		
		Graphics graphics = image.getGraphics();
	
		graphics.setColor(Color.RED);
		
		result = newResult;
		for (int x = 0;x < result.length;x++) {
			for (int y = 0;y < result[x].length;y++) {
				
			}
		}
		
		for (int x = 0;x < numX;x++) {
			for (int y = 0;y < numY;y++) {
				if (colorPercentage[x][y] > 30000) {
					graphics.fillRect((width*x)/numX, (height*y)/numY, width/numX, height/numY);
				}
				graphics.drawString(Integer.toString(colorPercentage[x][y]), (width*x)/numX, (height*y)/numY);
			}
		}
		
		ui.nextFrame(image);
		
		frame.recycle();
	}
	
	private boolean isWithinThreshold(int[] c) {
		return (c[0] >= lowerThreshold[0] && c[0] <= upperThreshold[0] &&
				c[1] >= lowerThreshold[1] && c[1] <= upperThreshold[1] &&
				c[2] >= lowerThreshold[2] && c[2] <= upperThreshold[2]);
	}

	@Override
	public void exceptionReceived(V4L4JException arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println(result[e.getX()][e.getY()][0] + " " + result[e.getX()][e.getY()][1] + " " + result[e.getX()][e.getY()][2]);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}