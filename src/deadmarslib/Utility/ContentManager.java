package deadmarslib.Utility;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

/**
 * DeadMarsLib ContentManager Class
 * 
 * @author Daniel Cecil
 */
public class ContentManager {

    /**
     * Load an image from a file into a {@link BufferedImage}.
     * 
     * @param filepath File path to the image file.
     * @return A {@link BufferedImage} containing the loaded image.
     */
    public static BufferedImage loadImage(String filepath) {
        try{
            BufferedImage img = ImageIO.read(ContentManager.class.getResource(filepath));
            System.out.println("Loaded File: " + filepath);
            return img;
        } catch(IOException | IllegalArgumentException e) {
            System.out.println("Missing File: " + filepath);
            return null;
        }
    }

    /**
     * Load a sound from a file into a {@link Clip}.
     * 
     * @param filepath File path to the sound file.
     * @return A {@link Clip} containing the loaded sound.
     */
    public static AudioClip loadSound(String filepath) {
        AudioClip sound = null;
        
        try{
            sound = Applet.newAudioClip(ContentManager.class.getResource(filepath));
            System.out.println("Loaded File: " + filepath);
        } catch (Exception e) {
            System.out.println("Missing File: " + filepath);
        }
        
        return sound;
    }

    /**
     * Plays a specified sound {@link Clip}.
     * 
     * @param sound Sound {@link Clip} to play.
     */
    public static void playSound(AudioClip sound) {
        final AudioClip clip = sound;
        try{
            new Thread() {
                @Override
                public void run() {
                    clip.play();
                }
            }.start();
        } catch (IllegalThreadStateException | NullPointerException e) {
        }
    }

}