package com.cecilectomy.dmge.Core;

import com.cecilectomy.dmge.Utility.TimeSpan;

public class GameTime {

	private TimeSpan elapsedGameTime;
	private TimeSpan elapsedRealTime;
	private TimeSpan totalGameTime;
	private TimeSpan totalRealTime;
	
	public GameTime() {
		elapsedGameTime = new TimeSpan();
		elapsedRealTime = new TimeSpan();
		totalGameTime = new TimeSpan();
		totalRealTime = new TimeSpan();
	}
	
	public TimeSpan getElapsedGameTime() {
		return elapsedGameTime;
	}
	
	public TimeSpan getElapsedRealTime() {
		return elapsedRealTime;
	}
	
	public TimeSpan getTotalGameTime() {
		return totalGameTime;
	}
	
	public TimeSpan getTotalRealTime() {
		return totalRealTime;
	}

}
