package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Dimension;
import javax.swing.JApplet;
// </editor-fold>

/**
 * DeadMarsLib GameApplet Class
 * 
 * @author Daniel Cecil
 */
public class GameApplet extends GameBase {

    // <editor-fold defaultstate="expanded" desc="Initialize">
    
    /**
     * Constructor
     * 
     * @param winName Name of window. Not used in Applet, only for consistency.
     * @param fps Desired game update speed.
     */
    public GameApplet(JApplet applet, Dimension size, long fps) {
        super(size, fps);
        this.setIsApplet(true);
        
        applet.getContentPane().add(this);
        applet.setSize(size);
        applet.setFocusable(true);
        applet.requestFocus();
    }
    
    // </editor-fold>

}
