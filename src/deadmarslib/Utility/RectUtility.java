package deadmarslib.Utility;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Point;
import java.awt.Rectangle;
// </editor-fold>

/**
 * DeadMarsLib RectUtility Class
 *
 * @author Cecil
 */
public class RectUtility {
    
    /**
     * Retrieves the Y value of the top of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return Y value of top of rectangle.
     */
    public static int getTop(Rectangle r) {
        return r.y;
    }
    
    /**
     * Retrieves the Y value of the bottom of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return Y value of bottom of rectangle.
     */
    public static int getBottom(Rectangle r) {
        return r.y + r.height;
    }
    
    /**
     * Retrieves the X value of the left of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return X value of left of rectangle.
     */
    public static int getLeft(Rectangle r) {
        return r.x;
    }
    
    /**
     * Retrieves the X value of the right of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return X value of right of rectangle.
     */
    public static int getRight(Rectangle r) {
        return r.x + r.width;
    }
    
    /**
     * Retrieves the XY Coordinate value of the top left of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return XY Coordinate value of top left of rectangle.
     */
    public static Point getTopLeft(Rectangle r) {
        return new Point(getTop(r), getLeft(r));
    }
    
    /**
     * Retrieves the XY Coordinate value of the top right of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return XY Coordinate value of top right of rectangle.
     */
    public static Point getTopRight(Rectangle r) {
        return new Point(getTop(r), getRight(r));
    }
    
    /**
     * Retrieves the XY Coordinate value of the bottom left of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return XY Coordinate value of bottom left of rectangle.
     */
    public static Point getBottomLeft(Rectangle r) {
        return new Point(getBottom(r), getLeft(r));
    }
    
    /**
     * Retrieves the XY Coordinate value of the bottom right of a rectangle.
     * 
     * @param r Rectangle to use.
     * @return XY Coordinate value of bottom right of rectangle.
     */
    public static Point getBottomRight(Rectangle r) {
        return new Point(getBottom(r), getRight(r));
    }
}
