package com.cecilectomy.dmge.Rendering.Renderers;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Rendering.GameRenderer;

public class Java2DGameRenderer extends GameRenderer {
	
	private Canvas canvas;
	private BufferedImage dbImage;
	
	public Java2DGameRenderer(Canvas canvas) {
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
		if (this.dbImage == null || this.isResChanged()) {
			this.setResChanged(false);
			this.dbImage = new BufferedImage(this.getResolution().width,
					this.getResolution().height, BufferedImage.TYPE_INT_RGB);
		}
		return dbImage.getGraphics();
	}
	
	@Override
	public void render(GameObject gameObject) {
		BufferStrategy bs = this.getBufferStrategy();
		Graphics graphics = this.getGraphics();
		
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, this.getResolution().width,
				this.getResolution().height);
		
		gameObject.render(this);
	
		Graphics g = bs.getDrawGraphics();
		g.drawImage(this.dbImage, 0, 0, this.canvas.getWidth(), this.canvas.getHeight(), null);
		g.dispose();
		bs.show();
	}

}
