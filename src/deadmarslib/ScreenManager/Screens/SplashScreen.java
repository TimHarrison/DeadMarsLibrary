package deadmarslib.ScreenManager.Screens;

// <editor-fold defaultstate="expanded" desc="Imports">
import deadmarslib.Game.GameInput;
import deadmarslib.Game.GameTime;
import deadmarslib.ScreenManager.Screen;
import deadmarslib.System.TimeSpan;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
// </editor-fold>

/**
 * DeadMarsLibrary SplashScreen Class
 *
 * @author Daniel Cecil
 */
public class SplashScreen extends Screen {

    // <editor-fold defaultstate="expanded" desc="Fields">
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
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Initialize">
    public SplashScreen(Screen[] screenArr, BufferedImage splashImage, double screenDuration, double fadeInOutDuration) {
        super();
        
        this.setScreenArr(screenArr);
        this.setSplashImage(splashImage);
        this.setScreenDuration(screenDuration);
        this.setTransitionOnTime(TimeSpan.fromSeconds(fadeInOutDuration));
        this.setTransitionOffTime(TimeSpan.fromSeconds(fadeInOutDuration));
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">
    @Override
    public void update(GameTime gameTime, boolean otherScreenHasFocus, boolean coveredByOtherScreen) {
        screenTime += (float)(gameTime.elapsedGameTime.getMilliseconds() / 1000.0f);
        if (screenTime >= screenDuration)
        {
            LoadingScreen.load(this.getScreenManager(), false, screenArr);
        }
        
        super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
    }

    @Override
    public void render(GameTime gameTime, Graphics g) {
        super.render(gameTime, g);
        
        int resW = this.getScreenManager().game.getResolution().width;
        int resH = this.getScreenManager().game.getResolution().height;
        
        Graphics2D g2 = (Graphics2D)g;
        
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(this.getTransitionAlpha()/255.0));
        g2.setComposite(composite);
        
        g2.drawImage(this.getSplashImage(), 0, 0, resW, resH, 0, 0, this.getSplashImage().getWidth(), this.getSplashImage().getHeight(), null);
        
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Handle Input">
    @Override
    public void handleInput(GameInput input) {
        super.handleInput(input);

        if (input.lastKeyPressed()!=null)
        {
            this.setScreenTime(this.getScreenDuration());
        }
    }
    // </editor-fold>
    
}
