package deadmarslib.Game;

import java.awt.Dimension;
import javax.swing.JApplet;

/**
 * DeadMarsLib GameApplet Class
 * 
 * @author Daniel Cecil
 */
public class GameApplet extends GameBase {

	/**
	 * Constructor
	 * 
	 * @param applet JApplet reference. Required.
	 * @param size Size of applet window frame.
	 * @param fps Desired game update speed.
	 */
	public GameApplet(JApplet applet, Dimension size, long fps) {
		super(size, fps);
		_init(applet, size);
	}

	public GameApplet(JApplet applet, Dimension size, Dimension res, long fps) {
		super(size, res, fps);
		_init(applet, size);
	}

	private void _init(JApplet applet, Dimension size) {
		if (applet != null) {
			applet.getContentPane().add(this);
			applet.setSize(size);
			applet.setFocusable(true);
			applet.requestFocus();
		}
	}

}
