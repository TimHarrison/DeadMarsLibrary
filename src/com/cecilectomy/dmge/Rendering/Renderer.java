package com.cecilectomy.dmge.Rendering;

import java.awt.Dimension;

import com.cecilectomy.dmge.Core.GameObject;

public interface Renderer {

	public Dimension getResolution();
	public void setResolution(Dimension res);
	public void setResolution(int width, int height);
	public void setResolutionChanged(boolean flag);
	public boolean resolutionChanged();
	public void render(GameObject gameObject);
	public void update();
	public void initialize();
	public void cleanUp();
	
}
