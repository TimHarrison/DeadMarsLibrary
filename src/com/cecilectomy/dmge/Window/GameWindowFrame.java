package com.cecilectomy.dmge.Window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;

/**
 * DeadMarsLib GameFrame Class
 * 
 * @author Daniel Cecil
 */
public class GameWindowFrame extends GameBase implements GameWindow, WindowListener {

	private JFrame window = null;
	
	private Dimension resolution = new Dimension();
	private boolean resChange = false;
	private Graphics dbg;
	private BufferedImage dbImage;
	
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

	public GameWindowFrame(String title, Dimension size, long fps) {
		_init(title, size, size, fps);
	}

	/**
	 * GameFrame Constructor.
	 * <p>
	 * Constructs GameFrame instance with specified window title, window size,
	 * window resolution, and framerate.
	 * 
	 * @param title
	 *            Game Window Title.
	 * @param size
	 *            GameFrame Window size.
	 * @param res
	 *            GameFrame Window resolution.
	 * @param fps
	 *            Desired game update speed.
	 */
	public GameWindowFrame(String title, Dimension size, Dimension res, long fps) {
		_init(title, size, res, fps);
	}

	private void _init(String title, Dimension size, Dimension res, long fps) {
		this.setPreferredFPS(fps);
		
		this.setResolution(res);
		this.setSize(size);
		this.setBackground(Color.white);
		this.setFocusable(true);
		this.requestFocus();
		
		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(this);
		window.getContentPane().add(this);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
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

	/**
	 * Retrieves the {@link JFrame} associated with this game.
	 * 
	 * @return {@link JFrame}.
	 */
	public final JFrame getFrame() {
		return window;
	}

	/**
	 * Attempts to create a full-screen window.
	 * <p>
	 * Does not decorate or undecorate window. Use overloaded method.
	 * 
	 * @param fullscreen
	 */
	public final void setFullScreen(boolean fullscreen) {
		setFullScreen(fullscreen, false);
	}

	/**
	 * Attempts to create a full-screen window.
	 * <p>
	 * Can specify whether the window should be rebuilt or not. If the window is
	 * rebuilt, it will undecorate it for fullscreen, and decorate it for
	 * windowed. This is the recommended method for switching full-screen.
	 * 
	 * @param fullscreen
	 * @param rebuild
	 */
	public final void setFullScreen(boolean fullscreen, boolean rebuild) {
		if (rebuild) {
			rebuildWindow(!fullscreen);
		}

		if (fullscreen) {
			GraphicsDevice device = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			device.setFullScreenWindow(window);
		} else {
			GraphicsDevice device = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			device.setFullScreenWindow(null);

		}
	}

	private final void rebuildWindow(boolean decorated) {
		String title = window.getTitle();

		window.dispose();

		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(this);
		window.addWindowListener(this);
		window.setResizable(false);
		window.setUndecorated(!decorated);
		window.setVisible(true);
		window.pack();
		window.setLocationRelativeTo(null);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//this.start();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		this.stop();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		this.stop();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		this.pause();
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		this.unPause();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		this.unPause();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		this.pause();
	}

}
