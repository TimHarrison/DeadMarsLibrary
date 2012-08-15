package deadmarslib.ScreenManager.Screens;

// <editor-fold default="collapsed" desc="Imports">
import deadmarslib.Game.GameTime;
import deadmarslib.ScreenManager.Screen;
import deadmarslib.ScreenManager.ScreenManager;
import deadmarslib.ScreenManager.ScreenState;
import deadmarslib.System.TimeSpan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
// </editor-fold>

/**
 * DeadMarsLibrary LoadingScreen Class
 *
 * @author Daniel Cecil
 */
public class LoadingScreen extends Screen {

    // <editor-fold defaultstate="expanded" desc="Fields">
    boolean loadingIsSlow;
    boolean otherScreensAreGone;

    Screen[] screensToLoad;
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Initialize">
    private LoadingScreen(boolean loadingIsSlow, Screen[] screensToLoad) {
        this.loadingIsSlow = loadingIsSlow;
        this.screensToLoad = screensToLoad;

        if(loadingIsSlow) {
            this.setTransitionOnTime(TimeSpan.fromSeconds(0.5));
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">
    @Override
    public void update(GameTime gameTime, boolean otherScreenHasFocus, boolean coveredByOtherScreen) {
        super.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);

        if (otherScreensAreGone)
        {
            this.getScreenManager().removeScreen(this);

            if(screensToLoad != null)
            {
                for (int x = 0; x < screensToLoad.length; x++)
                {
                    Screen screen = screensToLoad[x];

                    if (screen != null)
                    {
                        this.getScreenManager().addScreen(screen);
                    }
                }
            }

            this.getScreenManager().game.resetElapsedTime();
        }
    }

    @Override
    public void render(GameTime gameTime, Graphics g) {
        if ((this.getScreenState() == ScreenState.Active) &&
            (this.getScreenManager().getScreens().length == 1))
        {
            otherScreensAreGone = true;
        }

        if (loadingIsSlow)
        {
            final String message = "Loading...";
            g.setFont(new Font("Arial", Font.BOLD, 12));

            int posx, posy;

            posx = this.getScreenManager().game.getResolution().width / 2;
            posy = this.getScreenManager().game.getResolution().height / 2;

            int textWidth = g.getFontMetrics().stringWidth("Loading...");

            g.setColor(Color.black);
            g.fillRect(0, 0, this.getScreenManager().game.getResolution().width, this.getScreenManager().game.getResolution().height);
            g.setColor(Color.white);
            g.drawString(message, posx - textWidth / 2, posy);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Public Methods">
    public static void load(ScreenManager screenManager, boolean loadingIsSlow, Screen[] screensToLoad) {
        Screen[] screens = screenManager.getScreens();
        
        for (int x = 0; x < screens.length; x++) {
            Screen screen = screens[x];

            screen.exitScreen();
        }

        LoadingScreen loadingScreen = new LoadingScreen(loadingIsSlow, screensToLoad);

        screenManager.addScreen(loadingScreen);
    }
    // </editor-fold>

}
