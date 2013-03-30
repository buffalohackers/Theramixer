import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.DataBufferByte;
import javax.swing.JPanel;

import au.edu.jcu.v4l4j.FrameGrabber;
import au.edu.jcu.v4l4j.CaptureCallback;
import au.edu.jcu.v4l4j.V4L4JConstants;
import au.edu.jcu.v4l4j.VideoDevice;
import au.edu.jcu.v4l4j.VideoFrame;
import au.edu.jcu.v4l4j.exceptions.StateException;
import au.edu.jcu.v4l4j.exceptions.V4L4JException;

public class Camera extends JPanel implements CaptureCallback {
        private static int width = 640, height = 480, std = V4L4JConstants.STANDARD_WEBCAM, channel = 0;
        private static String device = "/dev/video0";

        private VideoDevice videoDevice;
        private FrameGrabber frameGrabber;

        public static void main(String args[]){
        	new Camera();
        }
        
        public Camera () {
            
			// Initialise video device and frame grabber
			try {
				videoDevice = new VideoDevice(device);
                frameGrabber = videoDevice.getJPEGFrameGrabber(width, height, channel, std, 80);
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
                getGraphics().drawImage(frame.getBufferedImage(), 0, 0, width, height, null);
                
                byte[] pixels = ((DataBufferByte) frame.getBufferedImage().getRaster().getDataBuffer()).getData();
                
                frame.recycle();
        }

		@Override
		public void exceptionReceived(V4L4JException arg0) {
			// TODO Auto-generated method stub
			
		}
}