package com.cecilectomy.dmge.Java2D.Graphics.Animation;

import java.awt.Graphics;
import java.util.HashMap;

import com.cecilectomy.dmge.Core.GameTime;

public class AnimationManager {

	HashMap<String, Animation> animationList = new HashMap<>();
	Animation currentAnimation = null;
	
	public void addAnimation(Animation anim) {
		animationList.put(anim.getName(), anim);
		if (currentAnimation == null) {
			currentAnimation = anim;
		}
	}
	
	public void removeAnimation(String name) {
		Animation anim = animationList.remove(name);
		if (animationList.isEmpty()) {
			currentAnimation = null;
		} else if (currentAnimation.equals(anim)) {
			currentAnimation = (Animation) animationList.values().toArray()[0];
		}
	}
	
	public Animation getAmination(String name) {
		return animationList.get(name);
	}
	
	public void setCurrentAnimation(String name) {
		Animation temp = animationList.get(name);
		if (temp != null) {
			currentAnimation = temp;
		}
	}
	
	public void updateAnimation(GameTime gameTime) {
		if (currentAnimation != null) {
			currentAnimation.update(gameTime);
		}
	}
	
	public void renderAnimation(Graphics g, int x, int y) {
		if (currentAnimation != null) {
			currentAnimation.render(g, x, y);
		}
	}
}
