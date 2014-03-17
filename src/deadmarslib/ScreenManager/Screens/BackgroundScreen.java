package deadmarslib.ScreenManager.Screens;

import deadmarslib.Game.GameTime;
import deadmarslib.ScreenManager.Screen;
import deadmarslib.System.TimeSpan;
import deadmarslib.Utility.ContentManager;
import java.awt.Graphics;
import java.awt.Image;

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
		backgroundImage = ContentManager.loadImage(imagePath);
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
