package com.cecilectomy.dmge.Rendering;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import java.awt.Dimension;

import org.lwjgl.opengl.Display;

public class OpenGLRenderer extends Renderer {
	
	@Override
	public void initialize() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_FRAMEBUFFER_SRGB);
	}

//	@Override
//	public void render(List<RenderDetails> renderDetails) {
//		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//		
//		//super.render(gameComponent);
//		//gameComponent.render(this);
//	}

	@Override
	public void cleanUp() {
	}

	@Override
	public Dimension getResolution() {
		return null;
	}

	@Override
	public void setResolution(Dimension res) {
	}

	@Override
	public void setResolution(int width, int height) {
	}

	@Override
	public void setResolutionChanged(boolean flag) {
	}

	@Override
	public boolean resolutionChanged() {
		return false;
	}

	@Override
	public void update() {
		Display.update();
	}

	@Override
	public void clear() {
	}

}
