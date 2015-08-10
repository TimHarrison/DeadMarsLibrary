package com.cecilectomy.dmge.Window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindowFrame extends Canvas implements GameWindow {

	private JFrame window;
	
	public GameWindowFrame() {
		this.initialize("New Game Window Frame", 640, 480);
	}
	
	public GameWindowFrame(String title, int width, int height) {
		this.initialize(title, width, height);
	}

	@Override
	public void initialize(String title, int width, int height) {
		this.cleanUp();
		
		Dimension size = new Dimension(width, height);
		
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setFocusable(true);
		this.requestFocus();
		
		window = new JFrame();
		window.add(this);
		window.pack();
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setTitle(title);
		window.setVisible(true);
	}

	@Override
	public void cleanUp() {
		if(window != null) {
			window.setVisible(false);
			window.dispose();
		}
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
		this.setMinimumSize(size);
		this.setMaximumSize(size);

		window.pack();
		window.setLocationRelativeTo(null);
	}

	@Override
	public void setViewport(Dimension size) {
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);

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
