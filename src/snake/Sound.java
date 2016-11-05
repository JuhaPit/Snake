package snake;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	public static void PlaySoundLoop() {

		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Snake.class.getResource("searching.wav"));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

			Thread.sleep(10000);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	static void PlaySoundPoint() {

		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Snake.class.getResource("point.wav"));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();

			Thread.sleep(clip.getMicrosecondLength() / 1000);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	static void PlaySoundHit() {

		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(Snake.class.getResource("hit.wav"));
			clip = AudioSystem.getClip();
			clip.open(inputStream);
			clip.start();

			Thread.sleep(clip.getMicrosecondLength() / 1000);

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
