package com.cecilectomy.dmge.Window;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JApplet;
@SuppressWarnings("serial")
public class GameWindowApplet extends Canvas implements GameWindow {
	
	private JApplet WINDOW;
	private int WIDTH;
	private int HEIGHT;
	
	public GameWindowApplet(JApplet applet) {
		this(applet, 640, 480);
	}
	
	public GameWindowApplet(JApplet applet, int width, int height) {
		this.WINDOW = applet;
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.initialize();
	}

	@Override
	public void initialize() {
		Dimension size = new Dimension(this.WIDTH, this.HEIGHT);
		this.setViewport(size);
		
		if (this.WINDOW != null) {
			this.WINDOW.getContentPane().add(this);
			this.WINDOW.setSize(size);
			this.WINDOW.setFocusable(true);
			this.WINDOW.requestFocus();
		}
	}

	@Override
	public void deInitialize() {
	}

	public Dimension getViewport() {
		return new Dimension(this.getSize());
	}

	public void setViewport(int width, int height) {
		Dimension size = new Dimension(width, height);
		this.setViewport(size);
	}
	
	public void setViewport(Dimension size) {
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
	}
	
	public JApplet getJApplet() {
		return this.WINDOW;
	}

}
