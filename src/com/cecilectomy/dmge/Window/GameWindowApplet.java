package com.cecilectomy.dmge.Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;

/**
 * DeadMarsLib GameApplet Class.
 * 
 * @author Daniel Cecil
 */
public class GameWindowApplet extends GameBase implements GameWindow {

	private Dimension resolution = new Dimension();
	private boolean resChange = false;
	private Graphics dbg;
	private BufferedImage dbImage;
	
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
	public GameWindowApplet(JApplet applet, Dimension size, long fps) {
		this._init(applet, size, size, fps);
	}

	/**
	 * GameApplet Constructor.
	 * 
	 * @param applet
	 *            JApplet reference. Required.
	 * @param size
	 *            Size of applet window frame.
	 * @param res
	 *            Resolution of applet window frame.
	 * @param fps
	 *            Desired game update speed.
	 */
	public GameWindowApplet(JApplet applet, Dimension size, Dimension res, long fps) {
		this._init(applet, size, res, fps);
	}

	private void _init(JApplet applet, Dimension size, Dimension res, long fps) {
		this.setPreferredFPS(fps);
		this.setViewport(size);
		this.setResolution(res);
		
		if (applet != null) {
			applet.getContentPane().add(this);
			applet.setSize(size);
			applet.setFocusable(true);
			applet.requestFocus();
		}
	}
	
	@Override
	protected void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			requestFocus();
			return;
		}
	
		this.dbg = this.getGraphics();
		this.dbg.setColor(Color.black);
		this.dbg.fillRect(0, 0, this.getResolution().width,
				this.getResolution().height);
	
		for (int i = 0; i < this.components.size(); i++) {
			GameComponent gc = this.components.get(i);
			gc.render(this.gameTime);//, this.dbg);
		}
	
		Graphics g = bs.getDrawGraphics();
		g.drawImage(this.dbImage, 0, 0, this.getWidth(), this.getHeight(), null);
		g.dispose();
		bs.show();
		
	}
	
	@Override
	public Graphics getGraphics() {
		if (this.dbImage == null || this.resChange) {
			this.resChange = false;
			this.dbImage = new BufferedImage(this.resolution.width,
					this.resolution.height, BufferedImage.TYPE_INT_RGB);
		}
		return dbImage.getGraphics();
	}

	public void setViewport(int width, int height) {
		this.setSize(width, height);
	
		Dimension size = new Dimension(width, height);
	
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
	}
	
	public void setViewport(Dimension size) {
		this.setSize(size);
	
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
	}

	public Dimension getViewport() {
		return new Dimension(this.getWidth(), this.getHeight());
	}
	
	public void setResolution(int width, int height) {
		this.resolution = new Dimension(width, height);
		this.resChange = true;
	}

	public void setResolution(Dimension res) {
		this.resolution = res;
		this.resChange = true;
	}

	public Dimension getResolution() {
		return this.resolution;
	}

}
