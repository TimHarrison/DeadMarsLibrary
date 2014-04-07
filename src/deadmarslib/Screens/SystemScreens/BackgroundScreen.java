package deadmarslib.Screens.SystemScreens;

import deadmarslib.Assets.AssetHelper;
import deadmarslib.Assets.AssetManager;
import deadmarslib.Core.GameTime;
import deadmarslib.Screens.Screen;
import deadmarslib.Utility.TimeSpan;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

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
	public void render(GameTime gameTime, Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
	}

}
