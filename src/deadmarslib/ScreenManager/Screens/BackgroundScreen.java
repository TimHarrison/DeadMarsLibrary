package deadmarslib.ScreenManager.Screens;

// <editor-fold defaultstate="expanded" desc="Imports">
import deadmarslib.Game.GameTime;
import deadmarslib.ScreenManager.Screen;
import deadmarslib.System.TimeSpan;
import deadmarslib.Utility.ContentManager;
import java.awt.Graphics;
import java.awt.Image;
// </editor-fold>

/**
 * DeadMarsLibrary BackgroundScreen Class
 *
 * @author Daniel Cecil
 */
public class BackgroundScreen extends Screen {

    // <editor-fold defaultstate="expanded" desc="Fields">
    private Image backgroundImage;
    private String imagePath;
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Initialize">
    public BackgroundScreen(String ip) {
        imagePath = ip;
        this.setTransitionOffTime(TimeSpan.fromSeconds(0.5));
        this.setTransitionOnTime(TimeSpan.fromSeconds(0.5));
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Load and Unload">
    @Override
    public void loadContent() {
        backgroundImage = ContentManager.loadImage(imagePath);
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">
    @Override
    public void update(GameTime gameTime, boolean otherScreenHasFocus, boolean coveredByOtherScreen) {
        super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
    }

    @Override
    public void render(GameTime gameTime, Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
    // </editor-fold>

}
