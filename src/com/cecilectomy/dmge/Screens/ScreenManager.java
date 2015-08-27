package com.cecilectomy.dmge.Screens;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;
import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Rendering.RenderDetails;

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
	public ScreenManager(GameBase game, Component component) {
		super(game);

		input = new GameInput(game, component);
	}

	/**
	 * Performs the loading of content for Screens in this ScreenManager.
	 */
	@Override
	public void initialize() {
		isInitialized = true;
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);
			screen.loadContent();
		}
	}

	/**
	 * Performs the unloading of content for Screens in this ScreenManager.
	 */
	@Override
	public void cleanUp() {
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

		boolean otherScreenHasFocus = !getGame().getIsActive();
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
	
	@Override
	public List<RenderDetails> getRenderDetails() {
		ArrayList<RenderDetails> details = new ArrayList<RenderDetails>();
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);

			if (screen.getScreenState() == ScreenState.Hidden)
				continue;
			
			List<RenderDetails> ds = screen.getRenderDetails();
			if(ds != null) {
				details.addAll(ds);
			}
		}
		return details;
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
	
}
