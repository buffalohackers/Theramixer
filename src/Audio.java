import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Audio {
	
	HashMap<String, Clip> _samples;
	
	public Audio(){
//		_samples = getSamples();
		
	}
	
	public void printCapabilities(){
		System.out.print("Types: ");
		for(Type t : AudioSystem.getAudioFileTypes()){
			System.out.print(t + ", ");
		}
	}
	
	public Clip getSample(String key){
		return _samples.get(key);
	}
	
	public void playSample(String key){
		Clip sample = getSample(key);
		sample.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public Clip clipFromURL(String url){
		Clip clip;
		AudioInputStream audioStream;
		try {
			clip = AudioSystem.getClip();
			audioStream = AudioSystem.getAudioInputStream(new URL(url));
			clip.open(audioStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			clip = null;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			clip = null;
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			clip = null;
		} catch (IOException e) {
			e.printStackTrace();
			clip = null;
		}
		return clip;
	}
	
	/*
	 * Private methods
	 */
	
	private HashMap<String, Clip> getSamples(){
		HashMap<String, Clip> urls = new HashMap<String, Clip>(){{
			put("default", clipFromURL("http://pscode.org/media/leftright.wav"));
			put("primal", clipFromURL("http://www.rectified.us/Primal_Groove_&_Rectified_-_Nouveau_Luxe.mp3"));
		}};
		return urls; 
	}
}
