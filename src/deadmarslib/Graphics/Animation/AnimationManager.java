package deadmarslib.Graphics.Animation;

import deadmarslib.Game.GameTime;
import java.awt.Graphics;
import java.util.HashMap;

public class AnimationManager {
    
    HashMap<String, Animation> animationList = new HashMap<>();
    Animation currentAnimation = null;
    
    public void addAnimation(Animation anim) {
        animationList.put(anim.getName(), anim);
    }
    
    public void removeAnimation(String name) {
        animationList.remove(name);
    }
    
    public void setCurrentAnimation(String name) {
        Animation temp = animationList.get(name);
        if(temp != null)
            currentAnimation = temp;
    }
    
    public void updateAnimation(GameTime gameTime) {
        if(currentAnimation != null)
            currentAnimation.Update(gameTime);
    }
    
    public void renderAnimation(Graphics g, int x, int y) {
        if(currentAnimation != null)
            currentAnimation.Render(g, x, y);
    }
    
    public AnimationManager() {
        
    }
    
}
