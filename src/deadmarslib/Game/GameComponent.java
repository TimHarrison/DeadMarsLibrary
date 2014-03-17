package deadmarslib.Game;

import java.awt.Graphics;

/**
 * DeadMarsLib GameComponent Class
 * 
 * @author Daniel Cecil
 */
public class GameComponent {

	/**
	 * Reference to the Game this component is attached to.
	 */
	public GameBase game = null;

	/**
	 * GameComponent Constructor.
	 * 
	 * @param game Game to attach this component to.
	 */
	public GameComponent(GameBase game) {
		this.game = game;
	}

	/**
	 * Overrideable method for initializing a component.
	 */
	public void initialize() {
	}

	/**
	 * Overrideable method for loading content from a component.
	 * <p>
	 * Called automatically when a component is added to a game.
	 */
	public void loadContent() {
	}

	/**
	 * Overrideable method for unloading content from a component.
	 * <p>
	 * Called automatically when a component is removed from a game.
	 */
	public void unloadContent() {
	}

	/**
	 * Overrideable method for updating a component.
	 * <p>
	 * Called automatically during the game loop.
	 */
	public void update(GameTime gameTime) {
	}

	/**
	 * Overrideable method for rendering a component.
	 * <p>
	 * Called automatically during the game loop.
	 */
	public void render(GameTime gameTime, Graphics g) {
	}

}
