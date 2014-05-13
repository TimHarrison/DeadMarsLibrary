package com.cecilectomy.dmge.Java2D.Graphics.Animation;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Java2D.Graphics.SpriteSheet;
import com.cecilectomy.dmge.Math.MathEx;

/**
 * DeadMarsLibrary Animation Class
 * <p>
 * This is a basic animation class based off of a now unknown tutorial from the
 * Internet. No idea where it came from, as it was ported over a year ago from a
 * C# tutorial. This will however be replaced with a much more robust set of
 * Animation classes so it is pointless to try and find and credit the original
 * author. If you recognize the code and it is truly yours, let me know (and
 * give me credible source proof) and i will credit you.
 * 
 * @author Daniel Cecil
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

	/**
	 * Retrieve the name given to this animation.
	 * 
	 * @return animation name.
	 */
	public String getName() {
		return aName;
	}

	/**
	 * Retrieve the X position this animation is being drawn at.
	 * 
	 * @return X position.
	 */
	public int getX() {
		return this.aScreenX;
	}

	/**
	 * Set the X position that this animation is to be drawn at.
	 * 
	 * @param x New X position.
	 */
	public void setX(int x) {
		this.aScreenX = x;
	}

	/**
	 * Retrieve the Y position this animation is being drawn at.
	 * 
	 * @return Y position.
	 */
	public int getY() {
		return this.aScreenY;
	}

	/**
	 * Set the Y position that this animation is to be drawn at.
	 * 
	 * @param y New Y position.
	 */
	public void setY(int y) {
		this.aScreenY = y;
	}

	/**
	 * Retrieve the current frame of the animstion.
	 * 
	 * @return current frame.
	 */
	public int getCurrentFrame() {
		return aCurrentFrame;
	}

	/**
	 * Specify a frame number to set the animatio to.
	 * 
	 * @param f new frame number.
	 */
	public void setCurrentFrame(int f) {
		aCurrentFrame = (int) MathEx.clamp(f, 0, aFrameCount);
	}

	/**
	 * Retrieve the rate at which this animation is animating.
	 * 
	 * @return frame rate.
	 */
	public float getFrameRate() {
		return aFrameRate;
	}

	/**
	 * Specify the rate at which to animate this animation.
	 * 
	 * @param fr new frame rate.
	 */
	public void setFrameRate(float fr) {
		aFrameRate = Math.max(fr, 0f);
	}

	/**
	 * Find out if this animation is currently animating.
	 * 
	 * @return if animation is animating.
	 */
	public boolean getIsAnimating() {
		return aIsAnimating;
	}

	/**
	 * Tell this animation to animate or not.
	 * 
	 * @param flag animate or not.
	 */
	public void setIsAnimating(boolean flag) {
		aIsAnimating = flag;
	}

	/**
	 * Retrieves the source {@link Rectangle} of this animation.
	 * <p>
	 * The source {@link Rectangle} specifies where on the image texture to draw
	 * from based on dimensions and current frame number.
	 * 
	 * @return source rectangle.
	 */
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

	/**
	 * Constructor.
	 * 
	 * @param sheet Image texture.
	 * @param name Animation name.
	 * @param frame Cell position of starting frame and cell dimensions of frames.
	 * @param count Number of frames.
	 * @param rate Rate of animation.
	 */
	public Animation(SpriteSheet sheet, String name, Rectangle frame,
			int count, float rate) {
		this.sourceSpriteSheet = sheet;
		this.aName = name;
		this.aFrame = frame;
		this.aFrameCount = count;
		this.aFrameRate = rate;
	}

	/**
	 * Update the animation.
	 * <p>
	 * You should call this once per game update.
	 * 
	 * @param gameTime reference to a {@link GameTime} object.
	 */
	public void update(GameTime gameTime) {
		if (aIsAnimating) {
			aElapsed += (float) (gameTime.elapsedGameTime.getMilliseconds() / 1000.0);

			if (aElapsed > aFrameRate) {
				aCurrentFrame = (aCurrentFrame + 1) % aFrameCount;

				aElapsed = 0.0f;
			}
		}
	}

	/**
	 * Renders the animation to a graphics context.
	 * <p>
	 * You should call this once per Game Render.
	 * 
	 * @param g Graphics context to draw to.
	 * @param x X position to draw to.
	 * @param y Y position to draw to.
	 */
	public void render(Graphics g, int x, int y) {
		Rectangle source = this.getSourceRect();
		if (sourceSpriteSheet != null) {
			this.sourceSpriteSheet.renderSprite(g, source.x, source.y,
					source.width, source.height, x - aOrigin.x, y - aOrigin.y);
		}
	}

}
