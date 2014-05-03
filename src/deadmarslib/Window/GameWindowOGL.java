package deadmarslib.Window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import deadmarslib.Core.GameBase;
import deadmarslib.Core.GameComponent;

/**
 * DeadMarsLib GameFrame Class
 * 
 * @author Daniel Cecil
 */
public class GameWindowOGL extends GameBase implements GameWindow {
	
	public static String title = "";
	private Dimension screenSize = new Dimension();
	private Dimension resolution = new Dimension();
	private boolean displayChange = false;
	
	public void setViewport(int width, int height) {
		this.screenSize = new Dimension(width, height);
		this.displayChange = true;
	}
	
	public void setViewport(Dimension size) {
		this.screenSize = size;
		this.displayChange = true;
	}

	public Dimension getViewport() {
		return this.screenSize;
	}
	
	public void setResolution(int width, int height) {
		this.resolution = new Dimension(width, height);
		this.displayChange = true;
	}

	public void setResolution(Dimension res) {
		this.resolution = res;
		this.displayChange = true;
	}

	public Dimension getResolution() {
		return this.resolution;
	}

	public GameWindowOGL(String title, Dimension size, long fps) {
		_init(title, size, size, fps);
	}

	private void _init(String title, Dimension size, Dimension res, long fps) {
		GameWindowOGL.title = title;
		this.setPreferredFPS(fps);
		this.setViewport(size);
		this.setResolution(res);
	}
	
	@Override
	protected void render() {
		if(displayChange) {
			displayChange = false;
			this.createOpenGLWindow();
		}
		
		for (int i = 0; i < this.components.size(); i++) {
			GameComponent gc = this.components.get(i);
			gc.render(this.gameTime);//, this.dbg);
		}
		
		Display.update();
	}
	
	@Override
	protected void update() {
		super.update();
		
		if(Display.isCloseRequested()) {
			this.stop();
		}
	}
	
	@Override
	protected void initialize() {
		this.createOpenGLWindow();
	}
	
	@Override
	protected void cleanup() {
		Display.destroy();
	}
	
	private void createOpenGLWindow() {
		Display.destroy();
		Display.setTitle(title);
		try {
			Display.setDisplayMode(new DisplayMode(screenSize.width, screenSize.height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			this.stop();
		}
	}
}
