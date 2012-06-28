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
    int Width;
    int Height;
    
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
        return Width;
    }
    
    /**
     * Sets the width of this {@link GameCamera}.
     * 
     * @param w new width.
     */
    public void setWidth(int w) {
        Width = w;
    }
    
    /**
     * Retrieve the height of this {@link GameCamera}.
     * 
     * @return height.
     */
    public int getHeight() {
        return Height;
    }
    
    /**
     * Sets the height of this {@link GameCamera}.
     * 
     * @param h new height.
     */
    public void setHeight(int h) {
        Height = h;
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
        Width = w;
        Height = h;
    }
    
    /**
     * Retrieves the rectangular area of this {@link GameCamera}.
     * @return {@link Rectangle} of camera.
     */
    public Rectangle getCamera() {
        return new Rectangle(X, Y, Width, Height);
    }
    
}
