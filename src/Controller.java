import java.util.ArrayList;

public class Controller {
	private boolean[] buttons;
	private ArrayList<Sample> samples;
	private boolean isRecording;
	private ArrayList<Sample> recording;
	private ArrayList<Long[]> duration;
	private long startTime;
	private Thread recordedThread;
	private int threadNum = 0;
	
	public Controller() {
		recording = new ArrayList<Sample>();
		duration = new ArrayList<Long[]>();
		isRecording = false;
		buttons = new boolean[6];
		
		samples = Sample.samplesInCurrentDirectory();
		System.out.println(samples.get(0).getFileName());
//		samples = new Sample[]{new Sample("/home/pe/Code/workspace/Fork-my-Banana/snl12.mp3"), new Sample("/home/pe/Code/workspace/Fork-my-Banana/robot.mp3"), new Sample(f), new Sample(f)};
	}
	
	public void buttonStates(boolean[] state) {
		for (int i = 0;i < buttons.length;i++) {
			if (buttons[i] != state[i]) {
				if (i > 1) {
					if (buttons[i]) {
						samples.get(i-2).stop();
						if (isRecording) {
							Long[] curDuration = duration.get(duration.size()-1);
							curDuration[1] = System.currentTimeMillis()-startTime;
							duration.set(duration.size()-1, curDuration);
						}
					} else {
						samples.get(i-2).start();
						if (isRecording) {
							recording.add(new Sample(samples.get(i-2).getFileName()));
							duration.add(new Long[]{System.currentTimeMillis()-startTime, (long)0});
						}
					}
				} else if (i == 1) {
					if (buttons[i]) {
						isRecording = !isRecording;
						
						if (isRecording) {
							System.out.println("RESTART");
							if (recordedThread != null) {
								recordedThread.interrupt();
							}
							for (Sample sample: recording) {
								sample.stop();
								System.out.println("stopping");
							}
							
							recording.clear();
							duration.clear();
							startTime = System.currentTimeMillis();
						} else {
							recordedThread = new Thread(new Runnable(){
								public void run() {
									int curStartIndex = 0, curEndIndex = 0;
									long threadStartTime = System.currentTimeMillis();
									int tnum = threadNum;
									threadNum++;
									while (!Thread.currentThread().isInterrupted()) {
										try {
											Thread.currentThread().sleep(50);
											if (curStartIndex < duration.size()) {
												if (duration.get(curStartIndex)[0] < System.currentTimeMillis() - threadStartTime) {
													recording.get(curStartIndex).start();
													curStartIndex++;
												}
											}
											
											if (curEndIndex < duration.size()) {
												if (duration.get(curEndIndex)[1] < System.currentTimeMillis() - threadStartTime) {
													recording.get(curEndIndex).stop();
													curEndIndex++;
												}
											} else if (curEndIndex == curStartIndex) {
												curStartIndex = 0;
												curEndIndex = 0;
												threadStartTime = System.currentTimeMillis();
											}
										} catch (InterruptedException e) {
											Thread.currentThread().interrupt();
											e.printStackTrace();
										}
										
									}
								}
							});
							recordedThread.start();
						}
						
					}
				}
			} else {
				if (i > 1 && !buttons[i]) {
					samples.get(i-2).stop();
				}
			}
		}
		
		buttons = state;
	}
}
