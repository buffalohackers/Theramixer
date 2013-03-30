import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class monome {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				Sample sample = Sample.samplesInCurrentDirectory().get(0);
				for(int i=0; i<10; i++){
					sample.start();
					sleep(1000);
					sample.stop();
				}
			}
		});
	}
	
	private static void sleep(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
