package deadmarslib.Core;

import deadmarslib.Utility.TimeSpan;

/**
 * DeadMarsLibrary GameTime Class
 * <p>
 * Modeled after XNA's GameTime class.
 * 
 * @author Daniel Cecil
 */
public class GameTime {

	/**
	 * Total elapsed time since the last game update.
	 */
	// TODO: use getter / setter
	public TimeSpan elapsedGameTime;

	/**
	 * Total elapsed time since the last rendered frame.
	 */
	// TODO: use getter / setter
	public TimeSpan elapsedRealTime;

	/**
	 * Total elapsed time since the start of the game. Game clock.
	 */
	// TODO: use getter / setter
	public TimeSpan totalGameTime;

	/**
	 * Total elapsed time since the start of the game. Wall clock.
	 */
	// TODO: use getter / setter
	public TimeSpan totalRealTime;

	/**
	 * Constructor
	 * <p>
	 * Initializes the {@link TimeSpan}s.
	 */
	public GameTime() {
		elapsedGameTime = new TimeSpan();
		elapsedRealTime = new TimeSpan();
		totalGameTime = new TimeSpan();
		totalRealTime = new TimeSpan();
	}

}
