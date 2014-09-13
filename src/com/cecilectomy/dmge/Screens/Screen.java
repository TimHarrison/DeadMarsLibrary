package com.cecilectomy.dmge.Screens;

import java.awt.Graphics;

import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Math.MathEx;
import com.cecilectomy.dmge.Rendering.Renderers.Java2DRenderer;
import com.cecilectomy.dmge.Utility.TimeSpan;

/**
 * DeadMarsLibrary Screen Class
 * 
 * @author Daniel Cecil
 */
public class Screen {

	private boolean isPopup = false;
	private ScreenState screenState = ScreenState.TransitionOn;
	private TimeSpan transitionOnTime = TimeSpan.Zero;
	private TimeSpan transitionOffTime = TimeSpan.Zero;
	private float transitionPosition = 1;
	private boolean isExiting = false;
	private boolean otherScreenHasFocus;
	private ScreenManager screenManager;

	/**
	 * Retrieves whether this {@link Screen} is a pop-up or not.
	 * 
	 * @return whether Screen is pup-up.
	 */
	public boolean getIsPopup() {
		return isPopup;
	}

	protected void setIsPopup(boolean flag) {
		isPopup = flag;
	}

	/**
	 * Retrieves the {@link TimeSpan} of this {@link Screen}'s transition to on.
	 * 
	 * @return {@link TimeSpan}.
	 */
	public TimeSpan getTransitionOnTime() {
		return transitionOnTime;
	}

	protected void setTransitionOnTime(TimeSpan time) {
		transitionOnTime = time;
	}

	/**
	 * Retrieves the {@link TimeSpan} of this {@link Screen}'s transition to
	 * off.
	 * 
	 * @return {@link TimeSpan}.
	 */
	public TimeSpan getTransitionOffTime() {
		return transitionOffTime;
	}

	protected void setTransitionOffTime(TimeSpan time) {
		transitionOffTime = time;
	}

	/**
	 * Retrieves how far along in transition the screen is.
	 * 
	 * @return transition position.
	 */
	public float getTransitionPosition() {
		return transitionPosition;
	}

	protected void setTransitionPosition(float time) {
		transitionPosition = time;
	}

	/**
	 * Retrieves an alpha value based on the transition position.
	 * 
	 * @return transition alpha.
	 */
	public int getTransitionAlpha() {
		return (int) (255 - getTransitionPosition() * 255);
	}

	/**
	 * Retrieves the {@link ScreenState} of this {@link Screen}.
	 * 
	 * @return {@link ScreenState}.
	 */
	public ScreenState getScreenState() {
		return screenState;
	}

	protected void setScreenState(ScreenState state) {
		screenState = state;
	}

	/**
	 * Retrieves whether this {@link Screen} is currently exiting.
	 * 
	 * @return whether {@link Screen} is exiting.
	 */
	public boolean getIsExiting() {
		return isExiting;
	}

	protected void setIsExiting(boolean flag) {
		isExiting = flag;
	}

	/**
	 * Retrieves whether this {@link Screen} is currently active.
	 * 
	 * @return whether {@link Screen} is active.
	 */
	public boolean getIsActive() {
		return !otherScreenHasFocus
				&& (screenState == ScreenState.TransitionOn || screenState == ScreenState.Active);
	}

	/**
	 * Retrieves the {@link ScreenManager} associated with this {@link Screen}.
	 * 
	 * @return {@link ScreenManager}.
	 */
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

	/**
	 * Update this Screen's transition and state.
	 * 
	 * @param gameTime GameTime object to update with.
	 * @param otherScreenHasFocus If other Screen has focus.
	 * @param coveredByOtherScreen If this Screen Covered by another.
	 */
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

	protected boolean updateTransition(GameTime gameTime, TimeSpan time,
			int direction) {
		float transitionDelta;

		if (time == TimeSpan.Zero) {
			transitionDelta = 1;
		} else {
			transitionDelta = (float) ((double) gameTime.elapsedGameTime
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

	public void render(Java2DRenderer renderer) {
	}

	/**
	 * Exit the screen.
	 * <p>
	 * If the transition off time is zero, the screen will exit immediately. If
	 * there is a transition off time, the screen will be marked as exiting and
	 * will exit automatically when it is done.
	 */
	public void exitScreen() {
		if (transitionOffTime == TimeSpan.Zero) {
			screenManager.removeScreen(this);
		} else {
			isExiting = true;
		}
	}

}
