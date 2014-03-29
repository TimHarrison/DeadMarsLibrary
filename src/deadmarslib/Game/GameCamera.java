package deadmarslib.Game;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * DeadMarsLib GameCamera Class
 * 
 * @author Daniel Cecil
 */
public class GameCamera {

	private int X;
	private int Y;
	private int width;
	private int height;

	/**
	 * Retrieve the X position of the camera.
	 * 
	 * @return X position.
	 */
	public int getX() {
		return this.X;
	}

	/**
	 * Set the X position of camera.
	 * 
	 * @param x
	 *            new X position.
	 */
	public void setX(int x) {
		this.X = x;
	}

	/**
	 * Retrieve the Y position of the camera.
	 * 
	 * @return Y position.
	 */
	public int getY() {
		return this.Y;
	}

	/**
	 * Set the Y position of the camera.
	 * 
	 * @param y
	 *            new Y position.
	 */
	public void setY(int y) {
		this.Y = y;
	}

	/**
	 * Retrieve the width of the camera.
	 * 
	 * @return width.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Sets the width of the camera.
	 * 
	 * @param w
	 *            new width.
	 */
	public void setWidth(int w) {
		this.width = w;
	}

	/**
	 * Retrieve the height of the camera.
	 * 
	 * @return height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Sets the height of the camera.
	 * 
	 * @param h
	 *            new height.
	 */
	public void setHeight(int h) {
		this.height = h;
	}

	/**
	 * Gets the camera's X and Y position.
	 * 
	 * @return X and Y coordinates of the camera.
	 */
	public final Point getPosition() {
		return new Point(this.X, this.Y);
	}

	/**
	 * Sets the camera's X and Y position.
	 * 
	 * @param x
	 *            new X Position.
	 * @param y
	 *            new Y Position.
	 */
	public final void setPosition(int x, int y) {
		this.X = x;
		this.Y = y;
	}

	/**
	 * Sets the camera's X and Y position.
	 * 
	 * @param position
	 *            Coordinates to set position to.
	 */
	public final void setPosition(Point position) {
		this.X = position.x;
		this.Y = position.y;
	}

	/**
	 * Retrieves the rectangular bounds of the camera.
	 * 
	 * @return {@link Rectangle} of camera bounds.
	 */
	public Rectangle getBounds() {
		return new Rectangle(this.X, this.Y, this.width, this.height);
	}

	/**
	 * Retrieves the rectangular bounds of the camera.
	 * <p>
	 * Allows for parallax coefficients.
	 * 
	 * @param pXCoef
	 * @param pYCoef
	 * @return {@link Rectangle} of camera bounds from coefficients.
	 */
	public Rectangle getBounds(double pXCoef, double pYCoef) {
		return new Rectangle((int) (this.X * pXCoef), (int) (this.Y * pYCoef),
				this.width, this.height);
	}

	/**
	 * Sets the camera's X and Y position as well as width and height
	 * dimensions.
	 * 
	 * @param x
	 *            new X Position.
	 * @param y
	 *            new Y Position.
	 * @param w
	 *            new Width.
	 * @param h
	 *            new Height.
	 */
	public final void setBounds(int x, int y, int w, int h) {
		this.X = x;
		this.Y = y;
		this.width = w;
		this.height = h;
	}

	/**
	 * Sets the camera's position as well as dimensions.
	 * 
	 * @param rect
	 *            Coordinates and Size to set position and dimensions to.
	 */
	public final void setBounds(Rectangle rect) {
		this.X = rect.x;
		this.Y = rect.y;
		this.width = rect.width;
		this.height = rect.height;
	}

	/**
	 * Default constructor.
	 * <p>
	 * Creates a size 0 camera at position (0, 0).
	 */
	public GameCamera() {
		this.setBounds(0, 0, 0, 0);
	}

	/**
	 * Constructor.
	 * <p>
	 * Creates a size 0 camera at position (x, y).
	 * 
	 * @param x
	 *            X position of created GameCamera.
	 * @param y
	 *            Y position of created GameCamera.
	 */
	public GameCamera(int x, int y) {
		this.setBounds(x, y, 0, 0);
	}

	/**
	 * Constructor.
	 * <p>
	 * Creates a size (w, h) camera at position (x, y).
	 * 
	 * @param x
	 *            X position of created GameCamera.
	 * @param y
	 *            Y position of created GameCamera.
	 * @param w
	 *            Width of created GameCamera.
	 * @param h
	 *            Height of created GameCamera.
	 */
	public GameCamera(int x, int y, int w, int h) {
		this.setBounds(x, y, w, h);
	}

	/**
	 * Constructor.
	 * <p>
	 * Creates a camera with position and size of given Rectangle.
	 * 
	 * @param rect
	 *            Rectangle to create GameCamera from.
	 */
	public GameCamera(Rectangle rect) {
		this.setBounds(rect);
	}

	/**
	 * Locks the game camera into a specified area.
	 * 
	 * @param rect
	 *            area to lock camera into.
	 */
	public void lockTo(Rectangle rect) {
		this.X = this.X < rect.x ? rect.x
				: (this.X + this.width) > (rect.x + rect.width) ? rect.x
						+ rect.width - this.width : this.X;
		this.Y = this.Y < rect.y ? rect.y
				: (this.Y + this.height) > (rect.y + rect.height) ? rect.y
						+ rect.height - this.height : this.Y;
	}
}
