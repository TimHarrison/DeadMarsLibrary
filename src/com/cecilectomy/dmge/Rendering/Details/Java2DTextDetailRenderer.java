package com.cecilectomy.dmge.Rendering.Details;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;

public class Java2DTextDetailRenderer implements DetailRenderer {
	
	@Override
	public void render(Renderer renderer, RenderDetails renderDetails) {
		// TODO (Daniel): Add text justification.
		// TODO (Daniel): Add multiline text rendering.
		// TODO (Daniel): Add text scaling around center of all text.
		// TODO (Daniel): Add text rotation around center of all text.
		
		String text = (String)renderDetails.details.get("text");
		Color color = (Color)renderDetails.details.get("color");
		Font font = (Font)renderDetails.details.get("font");
		int x = (Integer)renderDetails.details.get("x");
		int y = (Integer)renderDetails.details.get("y");
		
		Graphics g = ((Java2DRenderer)renderer).getGraphics();
		
		g.setFont(font);
		g.setColor(color);

		g.drawString(text, x, y);
	}

}
