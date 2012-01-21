package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import deadmarslib.System.TimeSpan;
// </editor-fold>

/**
 * DeadMarsLib GameTime Class
 * 
 * @author Daniel Cecil
 */
public class GameTime {
    
    // <editor-fold defaultstate="expanded" desc="Fields">
    
    /**
     * Total elapsed time since the last game update.
     */
    public TimeSpan elapsedGameTime;
    
    /**
     * Total elapsed time since the last rendered frame.
     */
    public TimeSpan elapsedRealTime;
    
    /**
     * Total elapsed time since the start of the game. Game clock.
     */
    public TimeSpan totalGameTime;
    
    /**
     * Total elapsed time since the start of the game. Wall clock.
     */
    public TimeSpan totalRealTime;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Initialization">
    
    /**
     * Constructor
     * <p>
     * Initializes this GameTime's {@link TimeSpan}s.
     */
    public GameTime() {
        elapsedGameTime = new TimeSpan();
        elapsedRealTime = new TimeSpan();
        totalGameTime = new TimeSpan();
        totalRealTime = new TimeSpan();
    }
    
    // </editor-fold>
    
}
