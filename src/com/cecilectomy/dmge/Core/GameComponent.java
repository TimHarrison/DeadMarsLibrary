package com.cecilectomy.dmge.Core;

import com.cecilectomy.dmge.Rendering.GameRenderer;

/**
 * DeadMarsLib GameComponent Class
 * 
 * @author Daniel Cecil
 */
public abstract class GameComponent {
	
	public void initialize() {
	}
	
	public void cleanUp() {
	}
	
	public void input(GameTime gameTime) {
	}
	
	public void update(GameTime gameTime) {
	}
	
	public void render(GameRenderer renderer) {
	}

}
