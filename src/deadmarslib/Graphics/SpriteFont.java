package deadmarslib.Graphics;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
// </editor-fold>

/**
 * DeadMarsLibrary SpriteFont class
 * 
 * @author Daniel Cecil
 */
public class SpriteFont {
    
    private HashMap<String, Integer> characterMap = new HashMap<>();
    private SpriteSheet characterSheet;
    
    /**
     * Constructor
     * 
     * @param cSheet {@link SpriteSheet} to use for the font.
     * @param cMap Character {@link SpriteSheet} cell mappings.
     */
    public SpriteFont(SpriteSheet cSheet, HashMap<String, Integer> cMap) {
        characterSheet = cSheet;
        characterMap = cMap;
    }
    
    /**
     * Draws a message to a graphics context using the {@link SpriteSheet} and character mappings.
     * <p>
     * If a character is not mapped, a rectangular box is drawn in the current graphics context color.
     * 
     * @param msg Message to draw.
     * @param g Graphics context to draw to.
     * @param x X position to start drawing.
     * @param y Y position to start drawing.
     */
    public void drawMessage(String msg, Graphics g, int x, int y) {
        int chrIndx = 0;
        for(char c : msg.toCharArray()) {
            Integer chr;
            if(characterMap != null) {
                chr = characterMap.get(((Character)c).toString());
            }
            else {
                chr = null;
            }
            
            if(chr!=null) {
                renderCharacter(g, chr.intValue(), new Dimension(1,1), new Point(x + chrIndx * characterSheet.getCellWidth(), y));
            } else {
                g.drawRect(x + chrIndx * characterSheet.getCellWidth() + 1, y + 1, characterSheet.getCellWidth() - 2, characterSheet.getCellHeight() - 2);
            }
            chrIndx++;
        }
    }

    public void renderCharacter(Graphics g, int sCell, Dimension gCells, Point dest) {
        int sGridX = (sCell % characterSheet.getXCells());
        int sGridY = ((sCell - sGridX) / characterSheet.getXCells());
        characterSheet.renderSprite(g, sGridX, sGridY, gCells.width, gCells.width, dest.x, dest.y, 1.0);
    }
}
