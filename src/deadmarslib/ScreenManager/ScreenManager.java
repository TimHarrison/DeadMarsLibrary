package deadmarslib.ScreenManager;

import deadmarslib.Game.GameBase;
import deadmarslib.Game.GameComponent;
import deadmarslib.Game.GameInput;
import deadmarslib.Game.GameTime;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScreenManager extends GameComponent {
        
    // <editor-fold defaultstate="expanded" desc="Fields">
    private ArrayList<Screen> screens = new ArrayList<>();
    private ArrayList<Screen> screensToUpdate = new ArrayList<>();
    private GameInput input;
    
    boolean isInitialized;
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Properties">
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Initialize">
    public ScreenManager(GameBase game) {
        super(game);
        
        input = new GameInput(game);
    }
    
    @Override
    public void Initialize() {
        isInitialized = true;
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Load and Unload">
    @Override
    public void LoadContent() {
        for(int x = 0; x < screens.size(); x++) {
            Screen screen = screens.get(x);
            screen.LoadContent();
        }
    }
    
    @Override
    public void UnloadContent() {
        for(int x = 0; x < screens.size(); x++) {
            Screen screen = screens.get(x);
            screen.UnloadContent();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">
    @Override
    public void Update(GameTime gameTime) {
        screensToUpdate.clear();
        
        for(int x = 0; x < screens.size(); x++) {
            Screen screen = screens.get(x);
            screensToUpdate.add(screen);
        }
        
        boolean otherScreenHasFocus = !Game.getIsActive();
        boolean coveredByOtherScreen = false;
        
        while(screensToUpdate.size() > 0) {
            Screen screen = screensToUpdate.get(screensToUpdate.size() - 1);
            
            screensToUpdate.remove(screensToUpdate.size() - 1);
            
            screen.Update(gameTime, otherScreenHasFocus, coveredByOtherScreen);
            
            if(screen.getScreenState() == ScreenState.TransitionOn ||
               screen.getScreenState() == ScreenState.Active)
            {
                if(!otherScreenHasFocus) {
                    screen.HandleInput(input);
                    otherScreenHasFocus = true;
                }
                
                if(!screen.getIsPopup()) {
                    coveredByOtherScreen = true;
                }
            }
        }
    }

    @Override
    public void Render(GameTime gameTime, Graphics g) {
        for(int x = 0; x < screens.size(); x++) {
            Screen screen = screens.get(x);
            
            if(screen.getScreenState() == ScreenState.Hidden)
                continue;
            
            screen.Render(gameTime, g);
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Public Methods">
    public void AddScreen(Screen screen) {
        screen.setScreenManager(this);
        screen.setIsExiting(false);
        
        if(isInitialized) {
            screen.LoadContent();
        }
        
        screens.add(screen);
    }
    
    public void RemoveScreen(Screen screen) {
        if(isInitialized) {
            screen.UnloadContent();
        }
        
        screens.remove(screen);
        screensToUpdate.remove(screen);
    }

    public Screen[] getScreens() {
        return Arrays.copyOf(screens.toArray(), screens.toArray().length, Screen[].class);
    }
    
    public GameInput getInput() {
        return input;
    }
    
    public void FadeBackBufferToBlack(Graphics g, int alpha) {
        float fadeAlpha = (float)(alpha / 255.0);
        fadeAlpha = fadeAlpha < 0 ? 0 : (fadeAlpha > 1 ? 1 : fadeAlpha);
        Graphics2D g2d = (Graphics2D)g;
        Composite ogComposite = g2d.getComposite();
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeAlpha);
        g2d.setComposite(composite);
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, this.Game.getWidth(), this.Game.getHeight());
        g2d.setComposite(ogComposite);
    }
    // </editor-fold>
}
