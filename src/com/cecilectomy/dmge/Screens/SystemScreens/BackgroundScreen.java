package com.cecilectomy.dmge.Screens.SystemScreens;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import com.cecilectomy.dmge.Assets.AssetManager;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.Renderers.Java2DGameRenderer;
import com.cecilectomy.dmge.Screens.Screen;
import com.cecilectomy.dmge.Utility.TimeSpan;

/**
 * DeadMarsLibrary BackgroundScreen Class
 * 
 * @author Daniel Cecil
 */
public class BackgroundScreen extends Screen {

	private Image backgroundImage;
	private String imagePath;

	public BackgroundScreen(String ip) {
		imagePath = ip;
		this.setTransitionOffTime(TimeSpan.fromSeconds(0.5));
		this.setTransitionOnTime(TimeSpan.fromSeconds(0.5));
	}

	@Override
	public void loadContent() {
		try {
			backgroundImage = (Image)AssetManager.loadAsset(Image.class, imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameTime gameTime, boolean otherScreenHasFocus,
			boolean coveredByOtherScreen) {
		super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
	}

	@Override
	public void render(Java2DGameRenderer renderer) {
		Graphics g = renderer.getGraphics();
		g.drawImage(backgroundImage, 0, 0, null);
	}

}
