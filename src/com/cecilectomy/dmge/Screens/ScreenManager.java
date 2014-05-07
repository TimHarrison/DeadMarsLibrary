package com.cecilectomy.dmge.Screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;
import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;

/**
 * DeadMarsLibrary ScreenManager Class
 * <p>
 * ScreenManager and all included classes are a close code port of the XNA Game
 * State Management sample on App Hub (http://create.msdn.com/en-US).
 * <p>
 * Game State Management <br/>
 * http://create.msdn.com/en-US/education/catalog/sample/game_state_management <br/>
 * Copyright (C) Microsoft Corporation. <br/>
 * See included Microsoft Permissive License.
 * 
 * @author Daniel Cecil
 */
public class ScreenManager extends GameComponent {

	private ArrayList<Screen> screens = new ArrayList<>();
	private ArrayList<Screen> screensToUpdate = new ArrayList<>();
	private GameInput input;

	boolean isInitialized;

	/**
	 * Constructor.
	 * <p>
	 * Attaches this ScreenManager to a {@link GameBase}.
	 * 
	 * @param game reference to GameBase.
	 */
	public ScreenManager(GameBase game) {
		super(game);

		input = new GameInput(game);
	}

	/**
	 * Not sure?
	 */
	@Override
	public void initialize() {
		isInitialized = true;
	}

	/**
	 * Performs the loading of content for Screens in this ScreenManager.
	 */
	@Override
	public void loadContent() {
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);
			screen.loadContent();
		}
	}

	/**
	 * Performs the unloading of content for Screens in this ScreenManager.
	 */
	@Override
	public void unloadContent() {
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);
			screen.unloadContent();
		}
	}

	/**
	 * Updates all visible active screens in this ScreenManager.
	 * 
	 * @param gameTime GameTime object to update with.
	 */
	@Override
	public void update(GameTime gameTime) {
		screensToUpdate.clear();

		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);
			screensToUpdate.add(screen);
		}

		boolean otherScreenHasFocus = !game.getIsActive();
		boolean coveredByOtherScreen = false;

		while (screensToUpdate.size() > 0) {
			Screen screen = screensToUpdate.get(screensToUpdate.size() - 1);

			screensToUpdate.remove(screensToUpdate.size() - 1);

			screen.update(gameTime, otherScreenHasFocus, coveredByOtherScreen);

			if (screen.getScreenState() == ScreenState.TransitionOn
					|| screen.getScreenState() == ScreenState.Active) {
				if (!otherScreenHasFocus) {
					screen.handleInput(input);
					otherScreenHasFocus = true;
				}

				if (!screen.getIsPopup()) {
					coveredByOtherScreen = true;
				}
			}
		}
	}

	/**
	 * Renders all visible screens in this ScreenManager.
	 * 
	 * @param gameTime GameTime object to render with.
	 * @param g Graphics context to render to.
	 */
	@Override
	public void render(GameTime gameTime) {//, Graphics g) {
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);

			if (screen.getScreenState() == ScreenState.Hidden)
				continue;

			screen.render(gameTime, this.game.getGraphics());//, g);
		}
	}

	/**
	 * Adds a {@link Screen} to the {@link ScreenManager}.
	 * 
	 * @param screen {@link Screen} to add.
	 */
	public void addScreen(Screen screen) {
		screen.setScreenManager(this);
		screen.setIsExiting(false);

		if (isInitialized) {
			screen.loadContent();
		}

		screens.add(screen);
	}

	/**
	 * Removes a {@link Screen} from the {@link ScreenManager}.
	 * 
	 * @param screen {@link Screen} to remove.
	 */
	public void removeScreen(Screen screen) {
		if (isInitialized) {
			screen.unloadContent();
		}

		screens.remove(screen);
		screensToUpdate.remove(screen);
	}

	/**
	 * Retrieves an array of all screens currently in the {@link ScreenManager}.
	 * 
	 * @return Array of screens.
	 */
	public Screen[] getScreens() {
		return Arrays.copyOf(screens.toArray(), screens.toArray().length,
				Screen[].class);
	}

	/**
	 * Retrieves the {@link GameInput} object of the {@link ScreenManager}.
	 * 
	 * @return {@link GameInput} object.
	 */
	public GameInput getInput() {
		return input;
	}

	public void fadeBackBufferToBlack(Graphics g, int alpha) {
		float fadeAlpha = (float) (alpha / 255.0);
		fadeAlpha = fadeAlpha < 0 ? 0 : (fadeAlpha > 1 ? 1 : fadeAlpha);
		Graphics2D g2d = (Graphics2D) g;
		Composite ogComposite = g2d.getComposite();
		AlphaComposite composite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, fadeAlpha);
		g2d.setComposite(composite);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
		g2d.setComposite(ogComposite);
	}

	public void fadeBackBufferToColor(Graphics g, Color c, int alpha) {
		float fadeAlpha = (float) (alpha / 255.0);
		fadeAlpha = fadeAlpha < 0 ? 0 : (fadeAlpha > 1 ? 1 : fadeAlpha);
		Graphics2D g2d = (Graphics2D) g;
		Composite ogComposite = g2d.getComposite();
		AlphaComposite composite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, fadeAlpha);
		g2d.setComposite(composite);
		g2d.setColor(c);
		g2d.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
		g2d.setComposite(ogComposite);
	}

}
