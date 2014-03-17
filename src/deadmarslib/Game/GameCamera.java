package deadmarslib.Game;

//<editor-fold defaultstate="collapsed" desc="Imports">

import java.awt.Point;
import java.awt.Rectangle;

// </editor-fold>

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
     * Retrieve the X position of this {@link GameCamera}.
     * 
     * @return X position.
     */
    public int getX() {
        return X;
    }
    
    /**
     * Set the X position of this {@link GameCamera}.
     * 
     * @param x new X position.
     */
    public void setX(int x) {
        X = x;
    }

    /**
     * Retrieve the Y position of this {@link GameCamera}.
     * 
     * @return Y position.
     */
    public int getY() {
        return Y;
    }
    
    /**
     * Set the Y position of this {@link GameCamera}.
     * 
     * @param y new Y position.
     */
    public void setY(int y) {
        Y = y;
    }
    
    /**
     * Retrieve the width of this {@link GameCamera}.
     * 
     * @return width.
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Sets the width of this {@link GameCamera}.
     * 
     * @param w new width.
     */
    public void setWidth(int w) {
        width = w;
    }
    
    /**
     * Retrieve the height of this {@link GameCamera}.
     * 
     * @return height.
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets the height of this {@link GameCamera}.
     * 
     * @param h new height.
     */
    public void setHeight(int h) {
        height = h;
    }
    
    /**
     * Gets this {@link GameCamera}'s X and Y position.
     * 
     * @return X and Y coordinates of the camera.
     */
    public final Point getPosition() {
        return new Point(X, Y);
    }
    
    /**
     * Sets this {@link GameCamera}'s X and Y position.
     * 
     * @param x new X Position.
     * @param y new Y Position.
     */
    public final void setPosition(int x, int y) {
        X = x;
        Y = y;
    }
    
    /**
     * Sets this {@link GameCamera}'s X and Y position.
     * 
     * @param position Coordinates to set position to.
     */
    public final void setPosition(Point position) {
        X = position.x;
        Y = position.y;
    }
    
    /**
     * Retrieves the rectangular bounds of this {@link GameCamera}.
     * 
     * @return {@link Rectangle} of camera bounds.
     */
    public Rectangle getBounds() {
        return new Rectangle(X, Y, width, height);
    }
    
    /**
     * Retrieves the rectangular bounds of this {@link GameCamera}.
     * <p>
     * Allows for parallax coefficients.
     * 
     * @param pXCoef
     * @param pYCoef
     * @return {@link Rectangle} of camera bounds from coefficients.
     */
    public Rectangle getBounds(double pXCoef, double pYCoef) {
        return new Rectangle((int)(X*pXCoef), (int)(Y*pYCoef), width, height);
    }
    
    /**
     * Sets this {@link GameCamera}'s X and Y position as well as width and height dimensions.
     * 
     * @param x new X Position.
     * @param y new Y Position.
     * @param w new Width.
     * @param h new Height.
     */
    public final void setBounds(int x, int y, int w, int h) {
        X = x;
        Y = y;
        width = w;
        height = h;
    }
    
    /**
     * Sets this {@link GameCamera}'s position as well as dimensions.
     * 
     * @param rect Coordinates and Size to set position and dimensions to.
     */
    public final void setBounds(Rectangle rect) {
        X = rect.x;
        Y = rect.y;
        width = rect.width;
        height = rect.height;
    }
    
    /**
     * Default constructor.
     * <p>
     * Creates a size 0 {@link GameCamera} at position (0, 0).
     */
    public GameCamera() {
    	setBounds(0, 0, 0, 0);
    }
    
    /**
     * Constructor.
     * <p>
     * Creates a size 0 {@link GameCamera} at position (x, y).
     * 
     * @param x X position of created GameCamera.
     * @param y Y position of created GameCamera.
     */
    public GameCamera(int x, int y) {
    	setBounds(x, y, 0, 0);
    }
    
    /**
     * Constructor.
     * <p>
     * Creates a size (w, h) {@link GameCamera} at position (x, y).
     * 
     * @param x X position of created GameCamera.
     * @param y Y position of created GameCamera.
     * @param w Width of created GameCamera.
     * @param h Height of created GameCamera.
     */
    public GameCamera(int x, int y, int w, int h) {
    	setBounds(x, y, w, h);
    }
    
    /**
     * Constructor.
     * <p>
     * Creates a {@link GameCamera} with position and size of given Rectangle.
     * 
     * @param rect Rectangle to create GameCamera from.
     */
    public GameCamera(Rectangle rect) {
    	setBounds(rect);
    }
    
    /**
     * Locks the game camera into a specified area.
     * 
     * @param rect area to lock camera into.
     */
    public void lockTo(Rectangle rect) {
        this.X = this.X < rect.x ? rect.x : this.X + this.width > rect.width ? rect.width - this.width : this.X;
        this.Y = this.Y < rect.y ? rect.y : this.Y + this.height > rect.height ? rect.height - this.height : this.Y;
    }
}
