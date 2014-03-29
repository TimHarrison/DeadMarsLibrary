package deadmarslib.Game;

import java.awt.Graphics;

/**
 * DeadMarsLib GameComponent Class
 * 
 * @author Daniel Cecil
 */
public class GameComponent {

	/**
	 * Reference to the {@link GameBase} this component is attached to.
	 */
	// TODO: Use getter and setter instead of direct access.
	public GameBase game = null;

	/**
	 * GameComponent Constructor.
	 * 
	 * @param game
	 *            {@link GameBase} to attach this component to.
	 */
	// TODO: maybe instead set the game when adding to the games components?
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
	 * Called automatically when a component is added to the game.
	 */
	public void loadContent() {
	}

	/**
	 * Overrideable method for unloading content from a component.
	 * <p>
	 * Called automatically when a component is removed from the game.
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
