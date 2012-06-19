package deadmarslib.ScreenManager;

import deadmarslib.Game.GameInput;
import deadmarslib.Game.GameTime;
import deadmarslib.System.TimeSpan;
import deadmarslib.Utility.MathUtility;
import java.awt.Graphics;

public class Screen {
    
    // <editor-fold defaultstate="expanded" desc="Fields">
    private boolean isPopup = false;
    private ScreenState screenState = ScreenState.TransitionOn;
    private TimeSpan transitionOnTime = TimeSpan.Zero;
    private TimeSpan transitionOffTime = TimeSpan.Zero;
    private float transitionPosition = 1;
    private boolean isExiting = false;
    private boolean otherScreenHasFocus;
    private ScreenManager screenManager;
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Properties">
    public boolean getIsPopup() {
        return isPopup;
    }
    
    protected void setIsPopup(boolean flag) {
        isPopup = flag;
    }
    
    public TimeSpan getTransitionOnTime() {
        return transitionOnTime;
    }
    
    protected void setTransitionOnTime(TimeSpan time) {
        transitionOnTime = time;
    }
    
    public TimeSpan getTransitionOffTime() {
        return transitionOffTime;
    }
    
    protected void setTransitionOffTime(TimeSpan time) {
        transitionOffTime = time;
    }
    
    public float getTransitionPosition() {
        return transitionPosition;
    }
    
    protected void setTransitionPosition(float time) {
        transitionPosition = time;
    }
    
    public int getTransitionAlpha() {
        return (int)(255 - getTransitionPosition() * 255);
    }
    
    public ScreenState getScreenState() {
        return screenState;
    }
    
    protected void setScreenState(ScreenState state) {
        screenState = state;
    }
    
    public boolean getIsExiting() {
        return isExiting;
    }
    
    protected void setIsExiting(boolean flag) {
        isExiting = flag;
    }
    
    public boolean getIsActive() {
        return !otherScreenHasFocus &&
                       (screenState == ScreenState.TransitionOn ||
                        screenState == ScreenState.Active);
    }
    
    public ScreenManager getScreenManager() {
        return screenManager;
    }
    
    public void setScreenManager(ScreenManager sm) {
        screenManager = sm;
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Initialize">
    public Screen() {
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Load and Unload">
    public void LoadContent() {
    }
    
    public void UnloadContent() {
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Update and Render">
    public void Update(GameTime gameTime, boolean otherScreenHasFocus, boolean coveredByOtherScreen) {
        this.otherScreenHasFocus = otherScreenHasFocus;
        
        if(getIsExiting()) {
            setScreenState(ScreenState.TransitionOff);
            
            if(!UpdateTransition(gameTime, getTransitionOffTime(), 1)) {
                screenManager.RemoveScreen(this);
            }
        } else if(coveredByOtherScreen) {
            if(UpdateTransition(gameTime, getTransitionOffTime(), 1)) {
                setScreenState(ScreenState.TransitionOff);
            } else {
                setScreenState(ScreenState.Hidden);
            }
        } else {
            if(UpdateTransition(gameTime, getTransitionOnTime(), -1)) {
                setScreenState(ScreenState.TransitionOn);
            } else {
                setScreenState(ScreenState.Active);
            }
        }
    }
    
    boolean UpdateTransition(GameTime gameTime, TimeSpan time, int direction) {
        float transitionDelta;
        
        if(time == TimeSpan.Zero) {
            transitionDelta = 1;
        } else {
            transitionDelta = (float)((double)gameTime.elapsedGameTime.getMilliseconds() / (double)time.getMilliseconds());
        }
        
        transitionPosition += transitionDelta * direction;

        if (((direction < 0) && (transitionPosition <= 0)) ||
                ((direction > 0) && (transitionPosition >= 1)))
        {
            transitionPosition = MathUtility.clamp(transitionPosition, 0, 1);

            return false;
        }
        
        return true;
    }
    
    public void HandleInput(GameInput input) { }
    
    public void Render(GameTime gameTime, Graphics g) { }
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Public Methods">
    public void ExitScreen() {
        if (transitionOffTime == TimeSpan.Zero)
        {
            screenManager.RemoveScreen(this);
        }
        else
        {
            isExiting = true;
        }
    }
    // </editor-fold>
    
}
