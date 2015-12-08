package com.cecilectomy.dmge.Window;

import java.awt.Dimension;

public interface GameWindow {
	
	public void initialize();
	public void deInitialize();
	public Dimension getViewport();
	public void setViewport(int width, int height);
	public void setViewport(Dimension size);
	
}
