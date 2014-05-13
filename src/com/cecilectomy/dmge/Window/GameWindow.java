package com.cecilectomy.dmge.Window;

import java.awt.Dimension;

public interface GameWindow {
	public void initialize(String title, int width, int height);
	public void cleanUp();
	public String getTitle();
	public void setTitle(String title);
	public Dimension getViewport();
	public void setViewport(int width, int height);
	public void setViewport(Dimension size);
	public void setFullScreen(boolean fullscreen);
}
