package com.cecilectomy.dmge.Rendering.Details;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;

public class Java2DImageDetailRenderer implements DetailRenderer {
	
	@Override
	public void render(Renderer renderer, RenderDetails renderDetails) {
		BufferedImage image = (BufferedImage)renderDetails.details.get("image");
		if(image == null) { return; }
		Rectangle dest = (Rectangle)renderDetails.details.get("dest");
		Rectangle src = (Rectangle)renderDetails.details.get("src");
		src = src != null ? src : new Rectangle(0,0,image.getWidth(),image.getHeight());
		Point orig = (Point)renderDetails.details.get("origin");
		orig = orig != null ? orig : new Point(0,0);
		Double rot = (Double)renderDetails.details.get("rotation");
		rot = rot != null ? rot : 0.0;
		Double scale = (Double)renderDetails.details.get("scale");
		scale = scale != null ? scale : 1.0;
		Integer alpha = (Integer)renderDetails.details.get("alpha");
		alpha = alpha != null ? alpha : 255;
		
		Graphics2D g = (Graphics2D)((Java2DRenderer)renderer).getGraphics();
        
		Composite ogComposite = g.getComposite();
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(alpha/255.0));
		
        g.setComposite(composite);
        
        if(dest != null) {
        	g.drawImage(image, dest.x, dest.y, dest.width, dest.height, src.x, src.y, src.width, src.height, null);
        } else {
            AffineTransform transform = new AffineTransform();
    		transform.translate(orig.getX(), orig.getY());
    		transform.scale(scale,scale);
    		transform.rotate(rot);
    		transform.translate(-(orig.getX()), -(orig.getY()));
        	g.drawImage(image, transform, null);
        }
        
        g.setComposite(ogComposite);
	}

}
