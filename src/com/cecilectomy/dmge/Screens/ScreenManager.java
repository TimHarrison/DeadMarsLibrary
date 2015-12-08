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
import com.cecilectomy.dmge.Rendering.Renderer;

/*
 * ScreenManager and all included classes are a close code port of the XNA Game
 * State Management sample on App Hub (http://create.msdn.com/en-US).
 * <p>
 * Game State Management <br/>
 * http://create.msdn.com/en-US/education/catalog/sample/game_state_management <br/>
 * Copyright (C) Microsoft Corporation. <br/>
 * See included Microsoft Permissive License.
 */
public class ScreenManager extends GameComponent {

	private ArrayList<Screen> screens = new ArrayList<>();
	private ArrayList<Screen> screensToUpdate = new ArrayList<>();
	private GameInput input;

	boolean isInitialized;
	
	public ScreenManager(GameBase game, Component component) {
		super(game);

		input = new GameInput(game, component);
	}
	
	@Override
	public void initialize() {
		isInitialized = true;
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);
			screen.loadContent();
		}
	}
	
	@Override
	public void cleanUp() {
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);
			screen.unloadContent();
		}
	}
	
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
	
	@Override
	public void render(Renderer renderer) {
		for (int x = 0; x < screens.size(); x++) {
			Screen screen = screens.get(x);

			if (screen.getScreenState() == ScreenState.Hidden)
				continue;
			
			screen.render(renderer);
		}
	}
	
	public void addScreen(Screen screen) {
		screen.setScreenManager(this);
		screen.setIsExiting(false);

		if (isInitialized) {
			screen.loadContent();
		}

		screens.add(screen);
	}
	
	public void removeScreen(Screen screen) {
		if (isInitialized) {
			screen.unloadContent();
		}

		screens.remove(screen);
		screensToUpdate.remove(screen);
	}
	
	public Screen[] getScreens() {
		return Arrays.copyOf(screens.toArray(), screens.toArray().length,
				Screen[].class);
	}
	
	public GameInput getInput() {
		return input;
	}
	
}
