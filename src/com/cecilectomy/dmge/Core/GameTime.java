package com.cecilectomy.dmge.Core;

import com.cecilectomy.dmge.Utility.TimeSpan;

/**
 * DeadMarsLibrary GameTime Class
 * <p>
 * Modeled after XNA's GameTime class.
 * 
 * @author Daniel Cecil
 */
public class GameTime {

	private TimeSpan elapsedGameTime;
	private TimeSpan elapsedRealTime;
	private TimeSpan totalGameTime;
	private TimeSpan totalRealTime;

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

	/**
	 * Get total elapsed time since the last game update.
	 */
	public TimeSpan getElapsedGameTime() {
		return elapsedGameTime;
	}

	/**
	 * Get total elapsed time since the last rendered frame.
	 */
	public TimeSpan getElapsedRealTime() {
		return elapsedRealTime;
	}

	/**
	 * Get total elapsed time since the start of the game. Game clock.
	 */
	public TimeSpan getTotalGameTime() {
		return totalGameTime;
	}

	/**
	 * Get total elapsed time since the start of the game. Wall clock.
	 */
	public TimeSpan getTotalRealTime() {
		return totalRealTime;
	}

}
