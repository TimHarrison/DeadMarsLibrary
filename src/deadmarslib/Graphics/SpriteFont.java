package deadmarslib.Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;

public class SpriteFont {
    
    private HashMap<String, Integer> characterMap = new HashMap<String, Integer>();
    private SpriteSheet characterSheet;
    
    public SpriteFont(SpriteSheet cSheet, HashMap<String, Integer> cMap) {
        characterSheet = cSheet;
        characterMap = cMap;
    }
    
    public void drawMessage(String msg, Graphics g, int x, int y) {
        int chrIndx = 0;
        for(char c : msg.toCharArray()) {
            Integer chr = characterMap.get(((Character)c).toString());
            if(chr!=null) {
                characterSheet.renderSprite(g, chr.intValue(), new Dimension(1,1), x + chrIndx * characterSheet.getCellWidth(), y);
            }
            chrIndx++;
        }
    }
    
}
