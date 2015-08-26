package com.cecilectomy.dmge.Rendering.Details;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Rendering.Renderer;

public class Java2DImageDetailRenderer implements DetailRenderer {
	
	@Override
	public void render(Renderer renderer, RenderDetails renderDetails) {
		BufferedImage image = (BufferedImage)renderDetails.details.get("image");
		int alpha = (Integer)renderDetails.details.get("alpha");
		Rectangle dest = (Rectangle)renderDetails.details.get("dest");
		Rectangle src = (Rectangle)renderDetails.details.get("src");
		
		Graphics g = ((Java2DRenderer)renderer).getGraphics();
        Graphics2D g2 = (Graphics2D)g;
        
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)(alpha/255.0));
        g2.setComposite(composite);
        
        g2.drawImage(image, dest.x, dest.y, dest.width, dest.height, src.x, src.y, src.width, src.height, null);
	}

}
