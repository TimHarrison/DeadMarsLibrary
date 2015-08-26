package com.cecilectomy.dmge.Rendering.Details;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;

public class Java2DRectangleDetailRenderer implements DetailRenderer {
	
	@Override
	public void render(Renderer renderer, RenderDetails renderDetails) {
		String style = (String)renderDetails.details.get("style");
		Rectangle rect = (Rectangle)renderDetails.details.get("rect");
		Color color = (Color)renderDetails.details.get("color");
		Graphics g = ((Java2DRenderer)renderer).getGraphics();
		
		g.setColor(color);
		
		if(style.equals("line")) {
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
		else if(style.equals("fill")) {
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
		}
	}

}
