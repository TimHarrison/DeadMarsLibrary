package com.cecilectomy.dmge.Rendering.Renderers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Rendering.Renderer;

public class Java2DRenderer implements Renderer {
	
	private Canvas canvas;
	private BufferedImage dbImage;
	
	public Java2DRenderer(Canvas canvas) {
		this.canvas = canvas;
	}
	
	protected BufferStrategy getBufferStrategy() {
		BufferStrategy bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			canvas.requestFocus();
			bs = canvas.getBufferStrategy();
		}
		return bs;
	}
	
	public Graphics getGraphics() {
		if (this.dbImage == null || this.resolutionChanged()) {
			this.setResolutionChanged(false);
			this.dbImage = new BufferedImage(this.getResolution().width,
					this.getResolution().height, BufferedImage.TYPE_INT_RGB);
		}
		return dbImage.getGraphics();
	}

	@Override
	public void render(GameObject gameObject) {
		Graphics graphics = this.getGraphics();
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, this.getResolution().width,
				this.getResolution().height);
		
		gameObject.render(this);
	}
	
	@Override
	public void update() {
		BufferStrategy bs = this.getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		g.drawImage(this.dbImage, 0, 0, this.canvas.getWidth(), this.canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}

	@Override
	public void initialize() {
	}

	@Override
	public void cleanUp() {
	}
	
	private Dimension resolution = new Dimension(800, 600);
	
	public Dimension getResolution() {
		return this.resolution;
	}

	@Override
	public void setResolution(Dimension res) {
		this.resolution = new Dimension(res.width, res.height);
		this.resChanged = true;
	}

	@Override
	public void setResolution(int width, int height) {
		this.resolution = new Dimension(width, height);
		this.resChanged = true;
	}

	private boolean resChanged = false;
	
	@Override
	public void setResolutionChanged(boolean flag) {
		this.resChanged = flag;
	}

	@Override
	public boolean resolutionChanged() {
		return this.resChanged;
	}

}
