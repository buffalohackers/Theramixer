import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sample {
	private String _fileName;
	private Player _player;
	private Thread currentThread;
	
	public static ArrayList<Sample> samplesInCurrentDirectory(){
		return samplesInDirectory("./");
	}
	
	public static ArrayList<Sample> samplesInDirectory(String directory){
		ArrayList<Sample> samples = new ArrayList<Sample>();
		File fs[] = new File(directory).listFiles(new FileFilter(){
			@Override
			public boolean accept(File f) {
				String[] nameParts = f.getName().split("\\.");
				return nameParts.length == 2 && nameParts[1].equals("mp3");
			}
		});
		for(File file : fs) samples.add(new Sample(file.getName()));
		return samples;
	}
	
	public Sample(String fileName){
		_fileName = fileName;
	}
	
	public void start(){
		currentThread = new Thread(new Runnable(){
			public void run(){
				try {
					_player = makePlayer();
					_player.play();
					while (!Thread.currentThread().isInterrupted()) {
						if (_player.isComplete()) {
							System.out.println("test");
							_player.close();
							_player = makePlayer();
							_player.play();
						}
					}
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		});
		currentThread.start();
	}
	
	public void stop(){
		new Thread(new Runnable(){
			public void run(){
				if (_player != null) {
					_player.close();
					currentThread.interrupt();
				}
					
			}
		}).start();
	}
	
	public String getFileName() {
		return _fileName;
	}
	
	private Player makePlayer(){
		try {
			FileInputStream inputStream = new FileInputStream(_fileName);
			BufferedInputStream bufferedInput = new BufferedInputStream(inputStream);
			return new Player(bufferedInput);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}