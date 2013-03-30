import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class monome {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				Audio audio = new Audio();
				audio.printCapabilities();
//				audio.playSample("primal");
		 		JOptionPane.showMessageDialog(null, "Close this bitch");
			}
		});
	}
}
