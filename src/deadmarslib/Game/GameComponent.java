package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Graphics;
// </editor-fold>

/**
 * DeadMarsLib GameComponent Class
 * 
 * @author Daniel Cecil
 */
public class GameComponent {// extends JComponent {

    // <editor-fold defaultstate="expanded" desc="Fields">
    
    /**
     * Reference to the Game this component is attached to.
     */
    public GameBase Game = null;

    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Initialize">
    
    /**
     * Constructor
     *
     * @param game Game to attach this component to.
     */
    public GameComponent(GameBase game) {
        this.Game = game;
    }

    /**
     * Overrideable method for initializing a component.
     */
    public void Initialize() {
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Load and Unload">
    
    /**
     * Overrideable method for loading content from a component.
     * <p>
     * Called automatically when a component is added to a game.
     */
    public void LoadContent() {
    }

    /**
     * Overrideable method for unloading content from a component.
     * <p>
     * Called automatically when a component is removed from a game.
     */
    public void UnloadContent() {
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Update and Render">
    
    /**
     * Overrideable method for updating a component.
     * <p>
     * Called automatically during the game loop.
     */
    public void Update(GameTime gameTime) {
    }

    /**
     * Overrideable method for rendering a component.
     * <p>
     * Called automatically during the game loop.
     */
    public void Render(GameTime gameTime, Graphics g) {
    }
    
    // </editor-fold>
}
