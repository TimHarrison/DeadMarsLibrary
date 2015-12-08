package com.cecilectomy.dmge.Screens.SystemScreens;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Screens.Screen;
import com.cecilectomy.dmge.Utility.TimeSpan;

public class MessageBoxScreen extends Screen {

	String message;
	String optionOK;
	String optionCancel;
	BufferedImage gradientTexture;
	Font mbFont = new Font("Verdana", Font.BOLD, 12);
	
	public void setFont(Font font) {
		this.mbFont = font;
	}

	protected void accepted() {
	}

	protected void cancelled() {
	}

	public MessageBoxScreen(String message, BufferedImage tex) {
		this.optionOK = "Enter = ok";
		this.optionCancel = "Escape = cancel";
		this.message = message;
		this.gradientTexture = tex;

		setIsPopup(true);

		setTransitionOnTime(TimeSpan.fromSeconds(0.2));
		setTransitionOffTime(TimeSpan.fromSeconds(0.2));
	}

	@Override
	public void loadContent() {
	}

	@Override
	public void handleInput(GameInput input) {
		if (input.isKeyDown(KeyEvent.VK_ENTER)) {
			input.removeKeyDown(KeyEvent.VK_ENTER);
			accepted();
			exitScreen();
		} else if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			input.removeKeyDown(KeyEvent.VK_ESCAPE);
			cancelled();
			exitScreen();
		}
	}
	
	@Override
	public List<RenderDetails> getRenderDetails() {
		ArrayList<RenderDetails> details = new ArrayList<RenderDetails>();
		
		int posx, posy;
		
		Canvas c = new Canvas();
		FontMetrics fm = c.getFontMetrics(mbFont);
		int textWidth = fm.stringWidth(message);
		int textHeight = fm.getHeight();

		posx = this.getScreenManager().getGame().getRenderer().getResolution().width / 2;
		posy = this.getScreenManager().getGame().getRenderer().getResolution().height / 2;

		final int hPad = 32;
		final int vPad = 16;
		
		Rectangle rect = new Rectangle(
			posx - textWidth / 2 - hPad,
			posy - textHeight / 2 - vPad,
			textWidth + hPad * 2,
			textHeight * 4 + vPad * 2
		);
		
		{
			RenderDetails detail = new RenderDetails();
			
			detail.details.put("type", "Rectangle");
			detail.details.put("rect", new Rectangle(
					0,0,
					this.getScreenManager().getGame().getRenderer().getResolution().width,
					this.getScreenManager().getGame().getRenderer().getResolution().height
			));
			detail.details.put("style", "fill");
			detail.details.put("color", Color.black);
			detail.details.put("alpha", this.getTransitionAlpha() * 2 / 3);
			details.add(detail);
		}
		
		{
			RenderDetails detail = new RenderDetails();
			
			detail.details.put("type", "Rectangle");
			detail.details.put("rect", rect);
			detail.details.put("style", "fill");
			detail.details.put("color", Color.blue);
			details.add(detail);
		}
		
		// TODO (Daniel): Need to render textured background
//		g.drawImage(gradientTexture, br.x, br.y, br.width, br.height, 0, 0,
//		gradientTexture.getWidth(null),
//		gradientTexture.getHeight(null), null);
		
		{
			RenderDetails detail = new RenderDetails();
			
			detail.details.put("type", "Text");
			detail.details.put("text", this.message);
			detail.details.put("color", Color.white);
			detail.details.put("font", mbFont);
			detail.details.put("x", posx - textWidth / 2);
			detail.details.put("y", posy);
			
			details.add(detail);
		}
		
		{
			RenderDetails detail = new RenderDetails();
			
			detail.details.put("type", "Text");
			detail.details.put("text", optionOK);
			detail.details.put("color", Color.white);
			detail.details.put("font", mbFont);
			detail.details.put("x", posx - textWidth / 2);
			detail.details.put("y", posy + textHeight * 2);
			
			details.add(detail);
		}
		
		{
			RenderDetails detail = new RenderDetails();
			
			detail.details.put("type", "Text");
			detail.details.put("text", optionCancel);
			detail.details.put("color", Color.white);
			detail.details.put("font", mbFont);
			detail.details.put("x", posx - textWidth / 2);
			detail.details.put("y", posy + textHeight * 3);
			
			details.add(detail);
		}
		
		return details;
	}

}
