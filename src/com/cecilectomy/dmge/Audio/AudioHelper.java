package com.cecilectomy.dmge.Audio;

import java.applet.AudioClip;

import javax.sound.sampled.Clip;

public class AudioHelper {
	
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