import javazoom.jl.decoder.JavaLayerException;


public class Controller {
	private boolean[] buttons;
	private Sample[] samples;
	private int i;
	
	public Controller() {
		buttons = new boolean[6];
		String f = "/home/pe/Code/workspace/Fork-my-Banana/snl12.mp3";
		samples = new Sample[]{new Sample(f), new Sample(f), new Sample(f), new Sample(f), new Sample(f), new Sample(f), new Sample(f)};
	}
	
	public void buttonStates(boolean[] state) {
		for (i = 0;i < buttons.length;i++) {
			if (buttons[i] != state[i]) {
				if (i > 1) {
					if (buttons[i]) {
						System.out.println("here");
						samples[i].stop();
					} else {
						samples[i].start();
					}
				}
			}
		}
		
		buttons = state;
	}
}
