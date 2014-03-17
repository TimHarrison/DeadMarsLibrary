package deadmarslib.Graphics.Animation;

import deadmarslib.Game.GameTime;
import java.awt.Graphics;
import java.util.HashMap;

/**
 * DeadMarsLibrary AnimationManager Class
 * 
 * @author Daniel Cecil
 */
public class AnimationManager {

	HashMap<String, Animation> animationList = new HashMap<>();
	Animation currentAnimation = null;

	/**
	 * Add an animation to the {@link AnimationManager}.
	 * 
	 * @param anim Animation to add.
	 */
	public void addAnimation(Animation anim) {
		animationList.put(anim.getName(), anim);
		if (currentAnimation == null) {
			currentAnimation = anim;
		}
	}

	/**
	 * Remove an animation from the {@link AnimationManager}.
	 * 
	 * @param name Name of animation to remove.
	 */
	public void removeAnimation(String name) {
		Animation anim = animationList.remove(name);
		if (animationList.isEmpty()) {
			currentAnimation = null;
		} else if (currentAnimation.equals(anim)) {
			currentAnimation = (Animation) animationList.values().toArray()[0];
		}
	}

	/**
	 * Retrieve an animation from the {@link AnimationManager}.
	 * <p>
	 * Useful for making modifications to an animation at runtime.
	 * 
	 * @param name Name of animation to retrieve.
	 * @return Requested Animation.
	 */
	public Animation getAmination(String name) {
		return animationList.get(name);
	}

	/**
	 * Set the current animation of the {@link AnimationManager} to draw during
	 * render.
	 * 
	 * @param name Name of animation to switch to.
	 */
	public void setCurrentAnimation(String name) {
		Animation temp = animationList.get(name);
		if (temp != null) {
			currentAnimation = temp;
		}
	}

	/**
	 * Update the current animation in the {@link AnimationManager}.
	 * <p>
	 * This should be called once per Game update.
	 * 
	 * @param gameTime reference to a {@link GameTime} object.
	 */
	public void updateAnimation(GameTime gameTime) {
		if (currentAnimation != null) {
			currentAnimation.update(gameTime);
		}
	}

	/**
	 * Render the current animation in the {@link AnimationManager}.
	 * <p>
	 * This should be called once per Game render.
	 * 
	 * @param g Graphics context to draw to.
	 * @param x X position to draw to.
	 * @param y Y position to draw to.
	 */
	public void renderAnimation(Graphics g, int x, int y) {
		if (currentAnimation != null) {
			currentAnimation.render(g, x, y);
		}
	}
}
