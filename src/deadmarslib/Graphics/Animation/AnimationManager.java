package deadmarslib.Graphics.Animation;

// <editor-fold defaultstate="collapsed" desc="Imports">
import deadmarslib.Game.GameTime;
import java.awt.Graphics;
import java.util.HashMap;
// </editor-fold>

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
    }
    
    /**
     * Remove an animation from the {@link AnimationManager}.
     * @param name Name of animation to remove.
     */
    public void removeAnimation(String name) {
        animationList.remove(name);
    }
    
    /**
     * Set the current animation of the {@link AnimationManager} to draw during render.
     * 
     * @param name Name of animation to switch to.
     */
    public void setCurrentAnimation(String name) {
        Animation temp = animationList.get(name);
        if(temp != null)
            currentAnimation = temp;
    }
    
    /**
     * Update the current animation in the {@link AnimationManager}.
     * <p>
     * This should be called once per Game update.
     * 
     * @param gameTime reference to a {@link GameTime} object.
     */
    public void updateAnimation(GameTime gameTime) {
        if(currentAnimation != null)
            currentAnimation.update(gameTime);
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
        if(currentAnimation != null)
            currentAnimation.render(g, x, y);
    }    
}
