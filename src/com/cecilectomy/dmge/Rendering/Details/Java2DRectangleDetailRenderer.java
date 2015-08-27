package com.cecilectomy.dmge.Rendering.Details;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;

public class Java2DRectangleDetailRenderer implements DetailRenderer {
	
	@Override
	public void render(Renderer renderer, RenderDetails renderDetails) {

		String style = (String)renderDetails.details.get("style");
		style = style != null ? style : "line";
		Rectangle rect = (Rectangle)renderDetails.details.get("rect");
		rect = rect != null ? rect : new Rectangle(0,0,1,1);
		Color color = (Color)renderDetails.details.get("color");
		color = color != null ? color : Color.magenta;
		Double rot = (Double)renderDetails.details.get("rotation");
		rot = rot != null ? rot : 0.0;
		Double scale = (Double)renderDetails.details.get("scale");
		scale = scale != null ? scale : 1.0;
		Integer alpha = (Integer)renderDetails.details.get("alpha");
		alpha = alpha != null ? alpha : 255;
		
		Graphics2D g = (Graphics2D)((Java2DRenderer)renderer).getGraphics();
		
		Composite ogComposite = g.getComposite();
		AlphaComposite composite = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, (float)(alpha / 255.0));
		g.setComposite(composite);
		
		g.setColor(color);
		
		AffineTransform transform = new AffineTransform();
		transform.translate(rect.getX() + rect.width/2, rect.getY() + rect.height/2);
		transform.scale(scale, scale);
		transform.rotate(rot);
		transform.translate(-(rect.getX() + rect.width/2), -(rect.getY() + rect.height/2));
		Shape transformed = transform.createTransformedShape(rect);
		
		if(style.equals("line")) {
			g.draw(transformed);
		}
		else if(style.equals("fill")) {
			g.fill(transformed);
		}
		
		g.setComposite(ogComposite);
	}

}
