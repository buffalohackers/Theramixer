
public class Controller {
	private boolean[] buttons;
	private String[] music;
	private Sample[] samples;
	
	public Controller() {
		buttons = new boolean[6];
		String f = "/home/pe/Code/workspace/Fork-my-Banana/snl12.mp3";
		samples = new Sample[]{new Sample(f), new Sample(f), new Sample(f), new Sample(f), new Sample(f), new Sample(f), new Sample(f)};
	}
	
	public void buttonStates(boolean[] state) {
		for (int i = 0;i < buttons.length;i++) {
			//System.out.print(buttons[i]);
			if (buttons[i] != state[i]) {
				if (i > 1) {
					if (buttons[i]) {
						samples[i].stop();
					} else {
						samples[i].start();
					}
				}
			}
		}
		
		//System.out.println("");
		
		buttons = state;
	}
}
