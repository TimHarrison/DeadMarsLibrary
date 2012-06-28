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
     * @param applet JApplet reference. Required.
     * @param size Size of applet window frame.
     * @param fps Desired game update speed.
     */
    public GameApplet(JApplet applet, Dimension size, long fps) {
        super(size, fps);
        this.setIsApplet(true);
        
        if(applet != null) {
            applet.getContentPane().add(this);
            applet.setSize(size);
            applet.setFocusable(true);
            applet.requestFocus();
        }
    }
    
    // </editor-fold>

}
