package snake;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	static void PlaySoundLoop(File Sound){
		
		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
			Thread.sleep(10000);
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
}
