package griffith;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundControl {
	//Path to the move sound
	private static String moveSoundPath = "src/res/move.wav";
	
	//Path to the background music
	private static String backgroundMusicPath = "src/res/background-music.wav";
	
	public static void playMoveSound() {
		playSound(moveSoundPath);
	}
	
	public static void playBackroundMusic() {
		playSound(backgroundMusicPath);
	}
	
	private static void playSound(String path) {
		try {
			//Get the audio stream
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
	    	//Create clip out of audio stream
			Clip clip = AudioSystem.getClip();
	    	clip.open(audioInputStream);
	    	//Play the clip
	    	clip.start();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
	}
}
