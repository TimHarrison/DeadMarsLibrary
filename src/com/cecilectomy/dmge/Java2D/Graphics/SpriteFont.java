package com.cecilectomy.dmge.Java2D.Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;

public class SpriteFont {

	private HashMap<String, Point> characterMap = new HashMap<>();
	private SpriteSheet characterSheet;
	
	public SpriteFont(SpriteSheet cSheet, HashMap<String, Point> cMap) {
		characterSheet = cSheet;
		characterMap = cMap;
	}
	
	public void drawMessage(String msg, Graphics g, int x, int y) {
		int chrIndx = 0;
		for (char c : msg.toCharArray()) {
			Point chr;
			if (characterMap != null) {
				chr = characterMap.get(((Character) c).toString());
			} else {
				chr = null;
			}

			if (chr != null) {
				characterSheet.renderSprite(g, chr, new Dimension(1, 1),
						new Point(x + chrIndx * characterSheet.getCellWidth(),
								y));
			} else {
				g.drawRect(x + chrIndx * characterSheet.getCellWidth() + 1,
						y + 1, characterSheet.getCellWidth() - 2,
						characterSheet.getCellHeight() - 2);
			}
			chrIndx++;
		}
	}
}
