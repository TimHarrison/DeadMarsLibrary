package deadmarslib.Window;

import java.awt.Dimension;

import javax.swing.JApplet;

import deadmarslib.Core.GameBase;

/**
 * DeadMarsLib GameApplet Class.
 * 
 * @author Daniel Cecil
 */
public class GameApplet extends GameBase {

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
	// TODO: Self created JApplet?
	public GameApplet(JApplet applet, Dimension size, long fps) {
		super(size, fps);
		this._init(applet, size);
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
	// TODO: Self created JApplet?
	public GameApplet(JApplet applet, Dimension size, Dimension res, long fps) {
		super(size, res, fps);
		this._init(applet, size);
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
