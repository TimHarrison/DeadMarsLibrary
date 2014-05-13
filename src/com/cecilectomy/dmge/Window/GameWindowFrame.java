package com.cecilectomy.dmge.Window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class GameWindowFrame extends Canvas implements GameWindow {

	private JFrame window;
	
	public GameWindowFrame(String title, Dimension size) {
		this.initialize(title, size.width, size.height);
	}

	@Override
	public void initialize(String title, int width, int height) {
		Dimension size = new Dimension(width, height);
		
		this.setSize(size);
		this.setPreferredSize(size);
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.requestFocus();
		
		window = new JFrame(title);
		window.getContentPane().add(this);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
	}

	@Override
	public void cleanUp() {
	}

	@Override
	public String getTitle() {
		return this.window.getTitle();
	}

	@Override
	public void setTitle(String title) {
		this.window.setTitle(title);
	}

	@Override
	public void setViewport(int width, int height) {
		Dimension size = new Dimension(width, height);
		this.setSize(size);
		this.setPreferredSize(size);

		window.pack();
		window.setLocationRelativeTo(null);
	}

	@Override
	public void setViewport(Dimension size) {
		this.setSize(size);
		this.setPreferredSize(size);

		window.pack();
		window.setLocationRelativeTo(null);
	}

	@Override
	public Dimension getViewport() {
		return this.getSize();
	}
	
	public final JFrame getFrame() {
		return window;
	}

	@Override
	public final void setFullScreen(boolean fullscreen) {
		setFullScreen(fullscreen, false);
	}

	public final void setFullScreen(boolean fullscreen, boolean rebuild) {
		if (rebuild) {
			rebuildWindow(!fullscreen);
		}

		if (fullscreen) {
			GraphicsDevice device = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			device.setFullScreenWindow(window);
		} else {
			GraphicsDevice device = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			device.setFullScreenWindow(null);

		}
	}

	private final void rebuildWindow(boolean decorated) {
		window.setVisible(false);
	    if(window.isDisplayable())window.dispose(); 

		//window = new JFrame(title);		
		//window.getContentPane().add(this);
		//window.setResizable(false);
		window.setUndecorated(!decorated);
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);
	}

}
