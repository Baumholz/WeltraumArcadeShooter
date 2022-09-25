package Game.Utility;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

	public Sounds() {
	}

	public void playLaser() {
		playSound("laser4.wav");
	}

	private void playSound(final String url) {

		try {
			File file = new File("Sound/" + url);
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	public void playExplosion() {
		// TODO Auto-generated method stub
		playSound("explosion.wav");
	}
}