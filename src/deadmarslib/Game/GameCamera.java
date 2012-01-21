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
//    int Scale;
    
    public int getX() {
        return X;
    }
    
    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }
    
    public void setY(int y) {
        Y = y;
    }
    
    public int getWidth() {
        return Width;
    }
    
    public void setWidth(int w) {
        Width = w;
    }
    
    public int getHeight() {
        return Height;
    }
    
    public void setHeight(int h) {
        Height = h;
    }
    
//    public int getScale() {
//        return Scale;
//    }
    
//    public void setScale(int s){
//        Scale = s;
//    }
    
    public GameCamera() {
        setCamera(0, 0, 0, 0);
    }
    
    public GameCamera(int x, int y) {
        setCamera(x, y, 0, 0);
    }
	
    public GameCamera(int x, int y, int w, int h) {
        setCamera(x, y, w, h);
    }
    
    public final void setCamera(int x, int y) {
        X = x;
        Y = y;
    }
    
    public final void setCamera(int x, int y, int w, int h) {
        X = x;
        Y = y;
        Width = w;
        Height = h;
    }
    
    public Rectangle getCamera() {
        return new Rectangle(X, Y, Width, Height);
    }
    
}
