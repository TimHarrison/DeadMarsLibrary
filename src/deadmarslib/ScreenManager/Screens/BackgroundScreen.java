package deadmarslib.ScreenManager.Screens;

import deadmarslib.Game.GameTime;
import deadmarslib.ScreenManager.Screen;
import deadmarslib.System.ContentManager;
import deadmarslib.System.TimeSpan;
import java.awt.Graphics;
import java.awt.Image;

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
    public void LoadContent() {
        backgroundImage = ContentManager.LoadImage(imagePath);
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">
    @Override
    public void Update(GameTime gameTime, boolean otherScreenHasFocus, boolean coveredByOtherScreen) {
        super.Update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
    }

    @Override
    public void Render(GameTime gameTime, Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
    // </editor-fold>

}
