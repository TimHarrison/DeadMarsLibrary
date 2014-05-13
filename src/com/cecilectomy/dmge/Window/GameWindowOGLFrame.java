package com.cecilectomy.dmge.Window;

import java.awt.Dimension;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * DeadMarsLib GameFrame Class
 * 
 * @author Daniel Cecil
 */
public class GameWindowOGLFrame implements GameWindow {
	
	public void setViewport(int width, int height) {
		this.initialize(this.getTitle(), width, height);
	}
	
	public void setViewport(Dimension size) {
		this.initialize(this.getTitle(), size.width, size.height);
	}

	public Dimension getViewport() {
		return new Dimension(Display.getWidth(), Display.getHeight());
	}

	public GameWindowOGLFrame(String title, Dimension size) {
		initialize(title, size.width, size.height);
	}

	@Override
	public void initialize(String title, int width, int height) {
		Display.destroy();
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void cleanUp() {
		Display.destroy();		
	}

	@Override
	public String getTitle() {
		return Display.getTitle();
	}

	@Override
	public void setTitle(String title) {
		Display.setTitle(title);
	}

	@Override
	public void setFullScreen(boolean fullscreen) {
	}
}
