package com.cecilectomy.dmge.Window;

import java.awt.Dimension;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class GameWindowOpenGLFrame implements GameWindow {
	
	private int WIDTH;
	private int HEIGHT;
	private String TITLE;
	
	public GameWindowOpenGLFrame() {
		this("", 640, 480);
	}
	
	public GameWindowOpenGLFrame(String title, int width, int height) {
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.initialize();
	}

	@Override
	public void initialize() {
		deInitialize();
		
		Display.setTitle(this.TITLE);
		try {
			Display.setDisplayMode(new DisplayMode(this.WIDTH, this.HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void deInitialize() {
		Display.destroy();
	}

	@Override
	public Dimension getViewport() {
		return new Dimension(Display.getWidth(), Display.getHeight());
	}

	@Override
	public void setViewport(int width, int height) {
		this.setViewport(new Dimension(width, height));
	}

	@Override
	public void setViewport(Dimension size) {
		this.WIDTH = size.width;
		this.HEIGHT = size.height;
		
		this.initialize();
	}
	
	public String getTitle() {
		return Display.getTitle();
	}
	
	public void setTitle(String title) {
		Display.setTitle(title);
	}
}
