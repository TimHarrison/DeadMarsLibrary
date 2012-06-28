package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
// </editor-fold>

/**
 * DeadMarsLib GameInput Class
 * 
 * @author Daniel Cecil
 */
public class GameInput extends GameComponent {

    // <editor-fold defaultstate="expanded" desc="Fields">
	
    ArrayList<Integer> kbdInputs = new ArrayList<>();
    ArrayList<Integer> mseInputs = new ArrayList<>();
    
    ArrayList<Integer> lastKey = new ArrayList<>();
    
    int mouseX;
    int mouseY;
    
    final GameBase thisGame;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Initialization">
    
    /**
     * Constructor
     * 
     * @param game Game to attach this input component to.
     */
    public GameInput(GameBase game) {
        super(game);
        
        thisGame = game;
        
        game.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {                
                if(!kbdInputs.contains(e.getKeyCode())) {
                    lastKey.clear();
                    lastKey.add(e.getKeyCode());
                    kbdInputs.add(e.getKeyCode());
                    System.out.println("Keyboard Key Added: " + e.getKeyCode());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(kbdInputs.contains(e.getKeyCode())) {
                    kbdInputs.remove((Integer)e.getKeyCode());
                    System.out.println("Keyboard Key Removed: " + e.getKeyCode());
                }
            }
        });
        
        game.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                thisGame.setFocusable(true);
                thisGame.requestFocus();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(!mseInputs.contains(e.getButton())) {
                    mseInputs.add(e.getButton());
                    System.out.println("Mouse Button Added: " + e.getButton());
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                if(mseInputs.contains(e.getButton())) {
                    mseInputs.remove((Integer)e.getButton());
                    System.out.println("Mouse Button Removed: " + e.getButton());
                }
            }
        });

        game.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseX = e.getX();
                mouseY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Public Methods">
    
    /**
     * Retrieves the {@link Integer} code of the last key that was pressed.
     * 
     * @return key code.
     */
    public Integer lastKeyPressed() {
        if(lastKey.size() > 0) {
            Integer key = lastKey.get(lastKey.size() - 1);
            lastKey.clear();
            return key;
        } else {
            return null;
        }
    }
    
    /**
     * Checks if a specified key is currently pressed down.
     * 
     * @param keyCode Key to check.
     * @return Whether or not the specified key is being pressed.
     */
    public boolean isKeyDown(Integer keyCode) {
        if(kbdInputs.contains(keyCode)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Checks if a specified mouse button is currently pressed down.
     * 
     * @param buttonCode Mouse button to check.
     * @return Whether or not the specified mouse button is being pressed.
     */
    public boolean isMouseDown(Integer buttonCode) {
        if(mseInputs.contains(buttonCode)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Remove a key from the list of keys currently being pressed.
     * 
     * @param keyCode Key to remove.
     */
    public void removeKeyDown(Integer keyCode) {
        kbdInputs.remove(keyCode);
        System.out.println("Keyboard Key Removed: " + keyCode);
    }
    
    /**
     * Remove a mouse button from the list of mouse buttons currently being pressed.
     * 
     * @param keyCode Mouse button to remove.
     */
    public void removeMouseDown(Integer keyCode) {
        mseInputs.remove(keyCode);
        System.out.println("Mouse Button Removed: " + keyCode);
    }

    /**
     * Retrieves the mouse's x coordinate within the window.
     * 
     * @return X coordinate of the mouse.
     */
    public int getMouseX() {
        return mouseX;
    }
    
    /**
     * Retrieves the mouse's y coordinate within the window.
     * 
     * @return Y coordinate of the mouse.
     */
    public int getMouseY() {
        return mouseY;
    }
	
	/**
     * Retrieves a {@link Point} that represents the mouse's coordinates within the window.
     * 
     * @return The mouse's xy coordinates.
     */
    public Point getMouseCoords() {
        return new Point(mouseX, mouseY);
    }
    
    // </editor-fold>
}
