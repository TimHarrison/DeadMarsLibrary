package com.cecilectomy.dmge.Window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameWindowFrame extends Canvas implements GameWindow {

	private JFrame WINDOW;
	private int WIDTH;
	private int HEIGHT;
	private String TITLE;
	
	public GameWindowFrame() {
		this("", 640, 480);
	}
	
	public GameWindowFrame(String title, int width, int height) {
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.initialize();
	}

	@Override
	public void initialize() {
		this.deInitialize();
		
		Dimension size = new Dimension(this.WIDTH, this.HEIGHT);
		
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setFocusable(true);
		this.requestFocus();
		
		this.WINDOW = new JFrame();
		this.WINDOW.add(this);
		this.WINDOW.pack();
		this.WINDOW.setResizable(false);
		this.WINDOW.setLocationRelativeTo(null);
		this.WINDOW.setTitle(this.TITLE);
		this.WINDOW.setVisible(true);
		
		this.WINDOW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void deInitialize() {
		if(this.WINDOW != null) {
			this.WINDOW.setVisible(false);
			this.WINDOW.dispose();
		}
	}

	@Override
	public Dimension getViewport() {
		return new Dimension(this.getSize());
	}

	@Override
	public void setViewport(int width, int height) {
		Dimension size = new Dimension(width, height);
		this.setViewport(size);
	}

	@Override
	public void setViewport(Dimension size) {
		this.setSize(size);
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);

		this.WINDOW.pack();
		this.WINDOW.setLocationRelativeTo(null);
	}
	
	public final JFrame getFrame() {
		return this.WINDOW;
	}
	
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
			device.setFullScreenWindow(this.WINDOW);
		} else {
			GraphicsDevice device = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			device.setFullScreenWindow(null);

		}
	}

	private final void rebuildWindow(boolean decorated) {
		this.WINDOW.setVisible(false);
	    if(this.WINDOW.isDisplayable()) this.WINDOW.dispose(); 

		//window = new JFrame(title);		
		//window.getContentPane().add(this);
		//window.setResizable(false);
	    this.WINDOW.setUndecorated(!decorated);
	    this.WINDOW.setVisible(true);
	    this.WINDOW.pack();
	    this.WINDOW.setLocationRelativeTo(null);
	}
	
	public String getTitle() {
		return this.WINDOW.getTitle();
	}
	
	public void setTitle(String title) {
		this.WINDOW.setTitle(title);
	}

}
