package com.cecilectomy.dmge.Screens.SystemScreens;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Screens.Screen;
import com.cecilectomy.dmge.Utility.TimeSpan;

public class SplashScreen extends Screen {

    private BufferedImage splashImage = null;
    private Screen[] screenArr = null;
    private double screenTime = 0.0f;
    private double screenDuration = 1.0f;
    private double fadeInOutDuration = 0.5f;

    public final BufferedImage getSplashImage() {
        return splashImage;
    }

    public final void setSplashImage(BufferedImage splashImage) {
        this.splashImage = splashImage;
    }
    
    public final Screen[] getScreenArr() {
        return screenArr;
    }

    public final void setScreenArr(Screen[] screenArr) {
        this.screenArr = screenArr;
    }
    
    public final double getScreenTime() {
        return screenTime;
    }

    public final void setScreenTime(double screenTime) {
        this.screenTime = screenTime;
    }

    public final double getScreenDuration() {
        return screenDuration;
    }

    public final void setScreenDuration(double screenDuration) {
        this.screenDuration = screenDuration;
    }
    
    public final double getFadeInOutDuration() {
        return fadeInOutDuration;
    }

    public final void setFadeInOutDuration(double fadeInOutDuration) {
        this.fadeInOutDuration = fadeInOutDuration;
    }

    public SplashScreen(Screen[] screenArr, BufferedImage splashImage, double screenDuration, double fadeInOutDuration) {
        super();
        
        this.setScreenArr(screenArr);
        this.setSplashImage(splashImage);
        this.setScreenDuration(screenDuration);
        this.setTransitionOnTime(TimeSpan.fromSeconds(fadeInOutDuration));
        this.setTransitionOffTime(TimeSpan.fromSeconds(fadeInOutDuration));
    }

    @Override
    public void update(GameTime gameTime, boolean otherScreenHasFocus, boolean coveredByOtherScreen) {
        screenTime += (float)(gameTime.getElapsedGameTime().getMilliseconds() / 1000.0f);
        if (screenTime >= screenDuration)
        {
            LoadingScreen.load(this.getScreenManager(), false, screenArr);
        }
        
        super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
    }
    
    @Override
    public List<RenderDetails> getRenderDetails() {
		ArrayList<RenderDetails> details = new ArrayList<RenderDetails>();
		
		RenderDetails detail = new RenderDetails();
		detail.details.put("type", "Image");
		detail.details.put("alpha", this.getTransitionAlpha());
		detail.details.put("dest", new Rectangle(0,0,
				this.getScreenManager().getGame().getRenderer().getResolution().width,
				this.getScreenManager().getGame().getRenderer().getResolution().height));
		detail.details.put("src", new Rectangle(0,0,((BufferedImage)this.getSplashImage()).getWidth(), ((BufferedImage)this.getSplashImage()).getHeight()));
		detail.details.put("image", this.getSplashImage());
		details.add(detail);
		
		return details;
    }

    @Override
    public void handleInput(GameInput input) {
        super.handleInput(input);

        if (input.lastKeyPressed()!=null)
        {
            this.setScreenTime(this.getScreenDuration());
        }
    }

}
