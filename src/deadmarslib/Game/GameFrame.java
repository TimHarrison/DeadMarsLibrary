package deadmarslib.Game;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

/**
 * DeadMarsLib GameFrame Class
 * 
 * @author Daniel Cecil
 */
public class GameFrame extends GameBase implements WindowListener {

	private JFrame window = null;

	/**
	 * GameFrame Constructor.
	 * 
	 * @param title Game Window Title.
	 * @param size GameFrame Window size.
	 * @param fps Desired game update speed.
	 */
	public GameFrame(String title, Dimension size, long fps) {
		super(size, fps);
		_init(title);
	}

	/**
	 * GameFrame Constructor.
	 * 
	 * @param title Game Window Title.
	 * @param size GameFrame Window size.
	 * @param res GameFrame Window resolution.
	 * @param fps Desired game update speed.
	 */
	public GameFrame(String title, Dimension size, Dimension res, long fps) {
		super(size, res, fps);
		_init(title);
	}

	private void _init(String title) {
		GameFrame thisFrame = this;

		window = new JFrame(title);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.addWindowListener(thisFrame);
		window.getContentPane().add(thisFrame);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		window.setLocationRelativeTo(null);
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
		//startGame();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		stopGame();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		stopGame();
	}

	@Override
	public void windowIconified(WindowEvent e) {
		pauseGame();
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		resumeGame();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		resumeGame();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		pauseGame();
	}

}
