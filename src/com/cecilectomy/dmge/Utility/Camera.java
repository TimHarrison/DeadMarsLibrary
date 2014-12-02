package com.cecilectomy.dmge.Utility;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * Camera Class
 * 
 * @author Daniel Cecil
 */
public class Camera extends Rectangle{

	/**
	 * Set the X position of camera.
	 * 
	 * @param x new X position.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set the Y position of the camera.
	 * 
	 * @param y new Y position.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Sets the width of the camera.
	 * 
	 * @param w new width.
	 */
	public void setWidth(int w) {
		this.width = w;
	}

	/**
	 * Sets the height of the camera.
	 * 
	 * @param h new height.
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
		return new Point(this.x, this.y);
	}

	/**
	 * Sets the camera's X and Y position.
	 * 
	 * @param x new X Position.
	 * @param y new Y Position.
	 */
	public final void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the camera's X and Y position.
	 * 
	 * @param position Coordinates to set position to.
	 */
	public final void setPosition(Point position) {
		this.x = position.x;
		this.y = position.y;
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
		return new Rectangle((int) (this.x * pXCoef), (int) (this.y * pYCoef),
				this.width, this.height);
	}

	/**
	 * Sets the camera's X and Y position as well as width and height
	 * dimensions.
	 * 
	 * @param x new X Position.
	 * @param y new Y Position.
	 * @param w new Width.
	 * @param h new Height.
	 */
	public final void setBounds(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	/**
	 * Sets the camera's position as well as dimensions.
	 * 
	 * @param rect Coordinates and Size to set position and dimensions to.
	 */
	public final void setBounds(Rectangle rect) {
		this.x = rect.x;
		this.y = rect.y;
		this.width = rect.width;
		this.height = rect.height;
	}

	/**
	 * Default constructor.
	 * <p>
	 * Creates a size 0 camera at position (0, 0).
	 */
	public Camera() {
		super(0, 0, 0, 0);
	}

	/**
	 * Constructor.
	 * <p>
	 * Creates a size 0 camera at position (x, y).
	 * 
	 * @param x X position of created GameCamera.
	 * @param y Y position of created GameCamera.
	 */
	public Camera(int x, int y) {
		super(x, y, 0, 0);
	}

	/**
	 * Constructor.
	 * <p>
	 * Creates a size (w, h) camera at position (x, y).
	 * 
	 * @param x X position of created GameCamera.
	 * @param y Y position of created GameCamera.
	 * @param w Width of created GameCamera.
	 * @param h Height of created GameCamera.
	 */
	public Camera(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	/**
	 * Constructor.
	 * <p>
	 * Creates a camera with position and size of given Rectangle.
	 * 
	 * @param rect Rectangle to create GameCamera from.
	 */
	public Camera(Rectangle rect) {
		super(rect);
	}

	/**
	 * Locks the game camera into a specified area.
	 * 
	 * @param rect area to lock camera into.
	 */
	public void lockTo(Rectangle rect) {
		this.x = this.x < rect.x ? rect.x
				: (this.x + this.width) > (rect.x + rect.width) ? rect.x
						+ rect.width - this.width : this.x;
		this.y = this.y < rect.y ? rect.y
				: (this.y + this.height) > (rect.y + rect.height) ? rect.y
						+ rect.height - this.height : this.y;
	}
	
	/**
	 * Looks at the center of the given rectangle bounds.
	 * 
	 * @param rect Bounding rectangle to look at.
	 */
	public void lookAt(Rectangle rect) {
		int centerX = rect.x + rect.width / 2;
		int centerY = rect.y + rect.height / 2;
		
		this.x = centerX - this.width / 2;
		this.y = centerY - this.height / 2;
	}
}
