package com.cecilectomy.dmge.Assets.Loaders;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.cecilectomy.dmge.Assets.AssetManager;
import com.cecilectomy.dmge.Audio.AudioHelper;

public class StringAssetLoader implements AssetLoader {
	
	@Override
	public Object load(String path) throws Exception {
		File file = new File(AssetManager.class.getResource(path).getFile());
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		
		while(line != null) {
			sb.append(line + "\n");
			line = br.readLine();
		}

		br.close();
		
		return sb.toString();
	}
	
}
