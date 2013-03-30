import javazoom.jl.decoder.JavaLayerException;


public class Controller {
	private boolean[] buttons;
	private Sample[] samples;
	private int i;
	
	public Controller() {
		buttons = new boolean[6];
		String f = "/home/patrick/workspace/Fork-my-Banana/snl12.mp3";
		samples = new Sample[]{new Sample("/home/patrick/workspace/Fork-my-Banana/snl12.mp3"), new Sample("/home/patrick/workspace/Fork-my-Banana/robot.mp3"), new Sample(f), new Sample(f)};
	}
	
	public void buttonStates(boolean[] state) {
		for (i = 0;i < buttons.length;i++) {
			if (buttons[i] != state[i]) {
				if (i > 1) {
					if (buttons[i]) {
						samples[i-2].stop();
					} else {
						samples[i-2].start();
					}
				}
			} else {
				if (i > 1 && !buttons[i]) {
					samples[i-2].stop();
				}
			}
		}
		
		buttons = state;
	}
}
