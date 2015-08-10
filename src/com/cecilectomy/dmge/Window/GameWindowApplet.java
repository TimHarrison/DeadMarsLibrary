package com.cecilectomy.dmge.Window;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JApplet;

/**
 * DeadMarsLib GameApplet Class.
 * 
 * @author Daniel Cecil
 */
@SuppressWarnings("serial")
public class GameWindowApplet extends Canvas implements GameWindow {
	
	/**
	 * GameApplet Constructor.
	 * 
	 * @param applet
	 *            JApplet reference. Required.
	 * @param size
	 *            Size of applet window frame.
	 * @param fps
	 *            Desired game update speed.
	 */
	public GameWindowApplet(JApplet applet, Dimension size) {
		this._init(applet, size);
	}

	private void _init(JApplet applet, Dimension size) {
		this.setViewport(size);
		
		if (applet != null) {
			applet.getContentPane().add(this);
			applet.setSize(size);
			applet.setFocusable(true);
			applet.requestFocus();
		}
	}

	public void setViewport(int width, int height) {
		this.setSize(width, height);
		Dimension size = new Dimension(width, height);
		this.setPreferredSize(size);
	}
	
	public void setViewport(Dimension size) {
		this.setSize(size);
		this.setPreferredSize(size);
	}

	public Dimension getViewport() {
		return this.getSize();
	}

	@Override
	public void initialize(String title, int width, int height) {
	}

	@Override
	public void cleanUp() {
	}

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public void setTitle(String title) {
	}

	@Override
	public void setFullScreen(boolean fullscreen) {
	}

}
