package com.cecilectomy.dmge.Rendering.Renderers;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

import java.awt.Dimension;

import org.lwjgl.opengl.Display;

import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Rendering.Renderer;

public class OpenGLRenderer implements Renderer {
	
	@Override
	public void initialize() {
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
		
		//super.render(gameObject);
		gameObject.render(this);
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
	}

	@Override
	public Dimension getResolution() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResolution(Dimension res) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setResolution(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setResolutionChanged(boolean flag) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean resolutionChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		Display.update();
	}

}
