package deadmarslib.Screens.SystemScreens;

import deadmarslib.Core.GameTime;
import deadmarslib.Input.GameInput;
import deadmarslib.Screens.Screen;
import deadmarslib.Utility.TimeSpan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * DeadMarsLibrary MessageBoxScreen Class
 * 
 * @author Daniel Cecil
 */
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
	public void render(GameTime gameTime, Graphics g) {
		g.setFont(mbFont);
		this.getScreenManager().fadeBackBufferToBlack(g,
				this.getTransitionAlpha() * 2 / 3);

		int posx, posy;
		int textWidth = g.getFontMetrics().stringWidth(message);
		int textHeight = g.getFontMetrics().getHeight();

		posx = this.getScreenManager().game.getResolution().width / 2;
		posy = this.getScreenManager().game.getResolution().height / 2;

		final int hPad = 32;
		final int vPad = 16;

		Rectangle br = new Rectangle((int) posx - textWidth / 2 - hPad,
				(int) posy - textHeight - vPad, (int) posx + textWidth / 2
						+ hPad, (int) posy + textHeight * 2 + textHeight / 2
						+ vPad);

		g.setColor(Color.white);
		g.drawImage(gradientTexture, br.x, br.y, br.width, br.height, 0, 0,
				gradientTexture.getWidth(null),
				gradientTexture.getHeight(null), null);
		g.drawString(message, posx - textWidth / 2, posy);
		g.drawString(optionOK, posx - textWidth / 2, posy + textHeight);
		g.drawString(optionCancel, posx - textWidth / 2, posy + textHeight * 2);
	}

}
