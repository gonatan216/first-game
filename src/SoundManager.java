import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {

    public static void playSound(String fileName) {
        new Thread(() -> {
            try {
                File soundFile = new File(fileName);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

            } catch (Exception e) {

            }
        }).start();
    }

    public static void playShoot () {
        playSound("Sound For Bullet.WAV");
    }

    public static void playExplosionForRegularMissiles () {
        playSound("Sound For Regular misseiles.WAV");
    }

    public static void playExplosionForSuperMissiles () {
        playSound("Sound For Super Misseiles.WAV");
    }

    public static void playExplosionForAtomic () {
        playSound("Sound For Atomic.WAV");
    }

    public static void playExplosionForPrize () {
        playSound("Sound For prize.WAV");
    }

    public static void playBackgroundMusic (String fileName) {
        new Thread(() -> {
            try {
                File soundFile = new File(fileName);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // שאני יוכל שזה ירוץ תמיד ברקע בלי שזה יעצור
                clip.start();
            } catch (Exception e) {

            }
        }).start();
    }





}
