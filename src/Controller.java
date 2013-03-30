
public class Controller {
	private boolean[] buttons;
	private String[] music;
	private Sample[] samples;
	
	public Controller() {
		buttons = new boolean[6];
		Sample musicFile = Sample.samplesInCurrentDirectory().get(0);
		samples = new Sample[]{musicFile, musicFile, musicFile, musicFile, musicFile, musicFile};
	}
	
	public void buttonStates(boolean[] state) {
		for (int i = 0;i < buttons.length;i++) {
			//System.out.print(buttons[i]);
			if (buttons[i] != state[i]) {
				System.out.println("HERE" + i);
				if (i > 1) {
					if (buttons[i]) {
						samples[i].stop();
					} else {
						samples[i].start();
					}
				}
			} else if (!state[i]) {
				samples[i].stop();
			}
		}
		
		//System.out.println("");
		
		buttons = state;
	}
}
