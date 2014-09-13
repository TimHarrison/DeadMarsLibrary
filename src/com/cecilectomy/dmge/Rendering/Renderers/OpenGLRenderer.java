package com.cecilectomy.dmge.Rendering.Renderers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Rendering.Renderer;

public class OpenGLRenderer extends Renderer {
	
	@Override
	public void initialize() {
		super.initialize();
		
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	@Override
	public void render(GameObject gameObject) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		super.render(gameObject);
	}

}
