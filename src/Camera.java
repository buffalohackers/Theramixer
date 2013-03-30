import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;

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
	private static int[] lowerThreshold = new int[]{135, 192, 183};
	private static int[] upperThreshold = new int[]{255, 255, 255};
	private int[][][] result;
	

	private VideoDevice videoDevice;
	private FrameGrabber frameGrabber;

	public Camera() {
		// Initialise video device and frame grabber
		try {
			videoDevice = new VideoDevice(device);
			frameGrabber = videoDevice.getJPEGFrameGrabber(width, height, channel, std, 20);
			frameGrabber.setCaptureCallback(this);
			width = frameGrabber.getWidth();
			height = frameGrabber.getHeight();
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

		result = new int[width][height][3];
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
			result[col][row][0] = r;
			result[col][row][1] = g;
			result[col][row][2] = b;
			
			Graphics graphics = image.getGraphics();
			
			graphics.setColor(Color.pink);
			
			if (isWithinThreshold(r, g, b)) {
				graphics.drawLine(col, row, col, row);
			}
			
			col++;
			if (col == width) {
				col = 0;
				row++;
			}
		}
		
		for (int x = 0;x < result.length;x++) {
			for (int y = 0;y < result[x].length;y++) {
				
			}
		}
		
		
		getGraphics().drawImage(image, 0, 0, width, height, null);
		frame.recycle();
	}
	
	private boolean isWithinThreshold(int r, int g, int b) {
		return (r >= lowerThreshold[0] && r <= upperThreshold[0] &&
				g >= lowerThreshold[1] && g <= upperThreshold[1] &&
				b >= lowerThreshold[2] && b <= upperThreshold[2]);
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