package com.cecilectomy.dmge.Assets.Loaders;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.cecilectomy.dmge.Assets.AssetManager;

public class BufferedImageAssetLoader implements AssetLoader {
	
	@Override
	public Object load(String path) throws Exception {
        BufferedImage img = ImageIO.read(AssetManager.class.getResource(path));
        return img;
	}
	
}
