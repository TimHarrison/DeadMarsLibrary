package deadmarslib.Game;

import deadmarslib.System.TimeSpan;

/**
 * DeadMarsLib GameTime Class
 * 
 * @author Daniel Cecil
 */
public class GameTime {

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

}
