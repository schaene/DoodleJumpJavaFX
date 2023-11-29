import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameConstants {
    public static final int GameHeight = 1100;
    public static final int GameWidth = 700;
    public static final int PlatformWidth = 120;
    public static final int springWidth = 40;

    // found a solution to play audio here: https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
    public static void playSound(File sound) throws Exception {
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(sound.toURI().toURL());  
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
