package com.cecilectomy.dmge.Screens.SystemScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Screens.Screen;
import com.cecilectomy.dmge.Screens.ScreenManager;
import com.cecilectomy.dmge.Screens.ScreenState;
import com.cecilectomy.dmge.Utility.TimeSpan;

/**
 * DeadMarsLibrary LoadingScreen Class
 * 
 * @author Daniel Cecil
 */
public class LoadingScreen extends Screen {

	boolean loadingIsSlow;
	boolean otherScreensAreGone;

	Screen[] screensToLoad;

	private LoadingScreen(boolean loadingIsSlow, Screen[] screensToLoad) {
		this.loadingIsSlow = loadingIsSlow;
		this.screensToLoad = screensToLoad;

		if (loadingIsSlow) {
			this.setTransitionOnTime(TimeSpan.fromSeconds(0.5));
		}
	}

	@Override
	public void update(GameTime gameTime, boolean otherScreenHasFocus,
			boolean coveredByOtherScreen) {
		super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
		
		if ((this.getScreenState() == ScreenState.Active)
				&& (this.getScreenManager().getScreens().length == 1)) {
			otherScreensAreGone = true;
		}

		if (otherScreensAreGone) {
			this.getScreenManager().removeScreen(this);

			if (screensToLoad != null) {
				for (int x = 0; x < screensToLoad.length; x++) {
					Screen screen = screensToLoad[x];

					if (screen != null) {
						this.getScreenManager().addScreen(screen);
					}
				}
			}

			this.getScreenManager().getGame().resetElapsedTime();
		}
	}
	
	@Override
	public List<RenderDetails> getRenderDetails() {
		ArrayList<RenderDetails> details = new ArrayList<RenderDetails>();
		
		if (loadingIsSlow) {

			RenderDetails detail = new RenderDetails();
			detail.details.put("type", "Rectangle");
			detail.details.put("rect", new Rectangle(0,0,
					this.getScreenManager().getGame().getRenderer().getResolution().width,
					this.getScreenManager().getGame().getRenderer().getResolution().height));
			detail.details.put("style", "fill");
			detail.details.put("color", Color.black);
			details.add(detail);

			final String message = "Loading...";
			int posx, posy;
			posx = this.getScreenManager().getGame().getRenderer().getResolution().width / 2;
			posy = this.getScreenManager().getGame().getRenderer().getResolution().height / 2;
			
			detail = new RenderDetails();
			detail.details.put("type", "Text");
			detail.details.put("justification", "center");
			detail.details.put("text", message);
			detail.details.put("color", Color.white);
			detail.details.put("font", new Font("Arial", Font.BOLD, 12));
			detail.details.put("x", posx);
			detail.details.put("y", posy);
			details.add(detail);
		}
		
		return details;
	}

	public static void load(ScreenManager screenManager, boolean loadingIsSlow,
			Screen[] screensToLoad) {
		Screen[] screens = screenManager.getScreens();

		for (int x = 0; x < screens.length; x++) {
			Screen screen = screens[x];

			screen.exitScreen();
		}

		LoadingScreen loadingScreen = new LoadingScreen(loadingIsSlow,
				screensToLoad);

		screenManager.addScreen(loadingScreen);
	}

}
