package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Rectangle;
// </editor-fold>

/**
 * DeadMarsLib GameCamera Class
 * 
 * @author Daniel Cecil
 */
public class GameCamera {
    
    int X;
    int Y;
    int width;
    int height;
    
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
     * Default constructor.
     * <p>
     * Creates a size 0 {@link GameCamera} at position (0, 0).
     */
    public GameCamera() {
        setCamera(0, 0, 0, 0);
    }
    
    /**
     * Basic Constructor.
     * <p>
     * Creates a size 0 {@link GameCamera} at position (x, y).
     * 
     * @param x
     * @param y 
     */
    public GameCamera(int x, int y) {
        setCamera(x, y, 0, 0);
    }
    
    /**
     * Versatile Constructor.
     * <p>
     * Creates a size (w, h) {@link GameCamera} at position (x, y).
     * 
     * @param x
     * @param y
     * @param w
     * @param h 
     */
    public GameCamera(int x, int y, int w, int h) {
        setCamera(x, y, w, h);
    }
    
    /**
     * Sets this {@link GameCamera}'s X and Y position.
     * 
     * @param x
     * @param y 
     */
    public final void setCamera(int x, int y) {
        X = x;
        Y = y;
    }
    
    /**
     * Sets this {@link GameCamera}'s X and Y position as well as width and height dimensions.
     * 
     * @param x
     * @param y 
     * @param w 
     * @param h 
     */
    public final void setCamera(int x, int y, int w, int h) {
        X = x;
        Y = y;
        width = w;
        height = h;
    }
    
    /**
     * Retrieves the rectangular area of this {@link GameCamera}.
     * @return {@link Rectangle} of camera.
     */
    public Rectangle getCamera() {
        return new Rectangle(X, Y, width, height);
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
