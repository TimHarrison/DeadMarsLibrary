package com.cecilectomy.dmge.Screens.SystemScreens;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cecilectomy.dmge.Assets.AssetManager;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Screens.Screen;
import com.cecilectomy.dmge.Utility.ErrorDialog;
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
			backgroundImage = (Image)AssetManager.getInstance().loadAsset(BufferedImage.class, imagePath);
		} catch (IOException e) {
			ErrorDialog.show(e.getLocalizedMessage());
		}
	}

	@Override
	public void update(GameTime gameTime, boolean otherScreenHasFocus,
			boolean coveredByOtherScreen) {
		super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
	}
	
	@Override
	public List<RenderDetails> getRenderDetails() {
		ArrayList<RenderDetails> details = new ArrayList<RenderDetails>();
		RenderDetails detail = new RenderDetails();
		detail.details.put("type", "Image");
		detail.details.put("alpha", 255);
		detail.details.put("dest", new Rectangle(0,0,
				this.getScreenManager().getGame().getRenderer().getResolution().width,
				this.getScreenManager().getGame().getRenderer().getResolution().height));
		detail.details.put("src", new Rectangle(0,0,((BufferedImage)backgroundImage).getWidth(), ((BufferedImage)backgroundImage).getHeight()));
		detail.details.put("image", backgroundImage);
		details.add(detail);
		return details;
	}

}
