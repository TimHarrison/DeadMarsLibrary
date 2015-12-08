package com.cecilectomy.dmge.Java2D.Graphics.Animation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Java2D.Graphics.SpriteSheet;
import com.cecilectomy.dmge.Math.MathEx;

/*
 * This is a basic animation class based off of a now unknown tutorial from the
 * Internet. No idea where it came from, as it was ported over a year ago from a
 * C# tutorial. This will however be replaced with a much more robust set of
 * Animation classes so it is pointless to try and find and credit the original
 * author. If you recognize the code and it is truly yours, let me know (and
 * give me credible source proof) and i will credit you.
 */
public class Animation {

	SpriteSheet sourceSpriteSheet;

	String aName;

	float aFrameRate = 0.0167f;
	float aElapsed = 0.0f;

	Rectangle aFrame = new Rectangle(0, 0, 4, 4);
	Point aOrigin = new Point(0, 0);

	int aFrameCount = 1;
	int aCurrentFrame = 0;
	int aScreenX = 0;
	int aScreenY = 0;

	boolean aIsAnimating = true;
	
	public String getName() {
		return aName;
	}
	
	public int getX() {
		return this.aScreenX;
	}
	
	public void setX(int x) {
		this.aScreenX = x;
	}
	
	public int getY() {
		return this.aScreenY;
	}
	
	public void setY(int y) {
		this.aScreenY = y;
	}
	
	public int getCurrentFrame() {
		return aCurrentFrame;
	}
	
	public void setCurrentFrame(int f) {
		aCurrentFrame = (int) MathEx.clamp(f, 0, aFrameCount);
	}
	
	public float getFrameRate() {
		return aFrameRate;
	}
	
	public void setFrameRate(float fr) {
		aFrameRate = Math.max(fr, 0f);
	}
	
	public boolean getIsAnimating() {
		return aIsAnimating;
	}
	
	public void setIsAnimating(boolean flag) {
		aIsAnimating = flag;
	}
	
	public Rectangle getSourceRect() {
		return new Rectangle(this.aFrame.x + this.aFrame.width
				* this.aCurrentFrame, this.aFrame.y, this.aFrame.width,
				this.aFrame.height);
	}

	public Point getOrigin() {
		return (Point) aOrigin.clone();
	}

	public void setOrigin(Point origin) {
		aOrigin = (Point) origin.clone();
	}
	
	public Animation(SpriteSheet sheet, String name, Rectangle frame,
			int count, float rate) {
		this.sourceSpriteSheet = sheet;
		this.aName = name;
		this.aFrame = frame;
		this.aFrameCount = count;
		this.aFrameRate = rate;
	}
	
	public void update(GameTime gameTime) {
		if (aIsAnimating) {
			aElapsed += (float) (gameTime.getElapsedGameTime().getMilliseconds() / 1000.0);

			if (aElapsed > aFrameRate) {
				aCurrentFrame = (aCurrentFrame + 1) % aFrameCount;

				aElapsed = 0.0f;
			}
		}
	}
	
	public void render(Graphics g, int x, int y) {
		Rectangle source = this.getSourceRect();
		if (sourceSpriteSheet != null) {
			this.sourceSpriteSheet.renderSprite(g, source.x, source.y,
					source.width, source.height, x - aOrigin.x, y - aOrigin.y);
		}
	}

}
