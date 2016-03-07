package com.cecilectomy.dmge.Screens;

import java.awt.Graphics;
import java.util.List;

import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Math.MathEx;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.Utility.TimeSpan;

public class Screen {

	private boolean isPopup = false;
	private ScreenState screenState = ScreenState.TransitionOn;
	private TimeSpan transitionOnTime = TimeSpan.Zero;
	private TimeSpan transitionOffTime = TimeSpan.Zero;
	private float transitionPosition = 1;
	private boolean isExiting = false;
	private boolean otherScreenHasFocus;
	private ScreenManager screenManager;
	
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
		return (int) (255 - getTransitionPosition() * 255);
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
		return !otherScreenHasFocus
				&& (screenState == ScreenState.TransitionOn || screenState == ScreenState.Active);
	}
	
	public ScreenManager getScreenManager() {
		return screenManager;
	}

	protected void setScreenManager(ScreenManager sm) {
		screenManager = sm;
	}

	public Screen() {
	}

	public void loadContent() {
	}

	public void unloadContent() {
	}
	
	public void update(GameTime gameTime, boolean otherScreenHasFocus,
			boolean coveredByOtherScreen) {
		this.otherScreenHasFocus = otherScreenHasFocus;

		if (getIsExiting()) {
			setScreenState(ScreenState.TransitionOff);

			if (!updateTransition(gameTime, getTransitionOffTime(), 1)) {
				screenManager.removeScreen(this);
			}
		} else if (coveredByOtherScreen) {
			if (updateTransition(gameTime, getTransitionOffTime(), 1)) {
				setScreenState(ScreenState.TransitionOff);
			} else {
				setScreenState(ScreenState.Hidden);
			}
		} else {
			if (updateTransition(gameTime, getTransitionOnTime(), -1)) {
				setScreenState(ScreenState.TransitionOn);
			} else {
				setScreenState(ScreenState.Active);
			}
		}
	}
	
	public void render(Renderer renderer) {
		
	}

	protected boolean updateTransition(GameTime gameTime, TimeSpan time,
			int direction) {
		float transitionDelta;

		if (time == TimeSpan.Zero) {
			transitionDelta = 1;
		} else {
			transitionDelta = (float) ((double) gameTime.getElapsedGameTime()
					.getMilliseconds() / (double) time.getMilliseconds());
		}

		transitionPosition += transitionDelta * direction;

		if (((direction < 0) && (transitionPosition <= 0))
				|| ((direction > 0) && (transitionPosition >= 1))) {
			transitionPosition = MathEx.clamp(transitionPosition, 0, 1);

			return false;
		}

		return true;
	}

	public void handleInput(GameInput input) {
	}
	
	public List<RenderDetails> getRenderDetails() {
		return null;
	}
	
	public void exitScreen() {
		if (transitionOffTime == TimeSpan.Zero) {
			screenManager.removeScreen(this);
		} else {
			isExiting = true;
		}
	}

}
