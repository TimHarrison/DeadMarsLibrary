package com.cecilectomy.dmge.Assets;

import java.applet.AudioClip;

import javax.sound.sampled.Clip;

/**
 * DeadMarsLib ContentManager Class
 * 
 * @author Daniel Cecil
 */
public class AssetHelper {

	// TODO (Daniel): We need to move this out into
	// some sort of audio engine.
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