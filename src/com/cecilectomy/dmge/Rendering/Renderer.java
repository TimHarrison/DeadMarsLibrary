package com.cecilectomy.dmge.Rendering;

import java.awt.Dimension;

import com.cecilectomy.dmge.Core.GameObject;

public class Renderer {
	
	private Dimension resolution = new Dimension(800,600);
	private boolean resChanged = false;
	
	public Dimension getResolution() {
		return resolution;
	}

	public void setResolution(Dimension resolution) {
		this.resolution = resolution;
		this.setResChanged(true);
	}

	public void setResolution(int width, int height) {
		this.resolution = new Dimension(width, height);
		this.setResChanged(true);
	}

	public boolean isResChanged() {
		return resChanged;
	}

	public void setResChanged(boolean resChanged) {
		this.resChanged = resChanged;
	}

	public void render(GameObject gameObject) {
		gameObject.render(this);
	}
	
	public void initialize(){
	}
	
	public void cleanUp() {
	}
	
}
