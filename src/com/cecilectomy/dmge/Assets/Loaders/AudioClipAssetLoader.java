package com.cecilectomy.dmge.Assets.Loaders;

import java.applet.Applet;
import java.applet.AudioClip;

import com.cecilectomy.dmge.Audio.AudioHelper;

public class AudioClipAssetLoader implements AssetLoader {
	
	@Override
	public Object load(String path) throws Exception {
        AudioClip sound = Applet.newAudioClip(AudioHelper.class.getResource(path));
        return sound;
	}
	
}
