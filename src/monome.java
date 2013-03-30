public class monome {
	
	public static void main(String[] args){	
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			ui test = new ui();
	        public void run() {
	        	test.dOverlayCamera();
	        }
		});
	}
}
