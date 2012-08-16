package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
// </editor-fold>

/**
 * DeadMarsLib GameFrame Class
 * 
 * @author Daniel Cecil
 */
public class GameFrame extends GameBase implements WindowListener {

    private JFrame window = null;
            
    // <editor-fold defaultstate="expanded" desc="Initialize">

    /**
     * Constructor
     * 
     * @param title
     * @param size
     * @param fps 
     */
    public GameFrame(String title, Dimension size, long fps) {
        super(size, fps);
        
        GameFrame thisFrame = this;
        
        window = new JFrame(title);
        window.getContentPane().add(thisFrame);
        window.addWindowListener(thisFrame);
        window.setResizable(false);
        window.setVisible(true);
        window.pack();
        window.setLocationRelativeTo(null);
    }
    
    /**
     * Retrieves the {@link JFrame} associated with this game.
     * 
     * @return {@link JFrame}.
     */
    public final JFrame getFrame() {
        return window;
    }
    
    /**
     * Attempts to create a full-screen window.
     * <p>
     * Does not decorate or undecorate window. Use overloaded method.
     * 
     * @param fullscreen 
     */
    public final void setFullScreen(boolean fullscreen) {
        setFullScreen(fullscreen, false);
    }
    
    /**
     * Attempts to create a full-screen window.
     * <p>
     * Can specify whether the window should be rebuilt or not. If the window
     * is rebuilt, it will undecorate it for fullscreen, and decorate it for windowed.
     * @param fullscreen
     * @param rebuild 
     */
    public final void setFullScreen(boolean fullscreen, boolean rebuild) {
        if(rebuild) {
            rebuildWindow(!fullscreen);
        }
        
        if(fullscreen) {
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            device.setFullScreenWindow(window);
        } else {
            GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            device.setFullScreenWindow(null);
            
        }
    }
    
    public final void rebuildWindow(boolean decorated) {
            String title = window.getTitle();
            
            window.dispose();
            
            window = new JFrame(title);
            window.getContentPane().add(this);
            window.addWindowListener(this);
            window.setResizable(false);
            window.setUndecorated(!decorated);
            window.setVisible(true);
            window.pack();
            window.setLocationRelativeTo(null);
    }
    
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Window Listener Methods">
    
    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        stopGame();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
        pauseGame();
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        resumeGame();
    }

    @Override
    public void windowActivated(WindowEvent e) {
        resumeGame();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        pauseGame();
    }
    
    // </editor-fold>
}
