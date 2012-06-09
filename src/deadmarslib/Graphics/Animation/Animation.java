package deadmarslib.Graphics.Animation;

import deadmarslib.Game.GameTime;
import deadmarslib.Graphics.SpriteSheet;
import deadmarslib.Utility.MathUtility;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Animation {
    
    SpriteSheet sourceSpriteSheet;
    
    String aName;
    
    float aFrameRate = 0.0167f;
    float aElapsed = 0.0f;
    
    Rectangle aFrame = new Rectangle(0,0,4,4);
    
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
        aCurrentFrame = (int) MathUtility.clamp(f, 0, aFrameCount);
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
        return new Rectangle(this.aFrame.x + this.aFrame.width * this.aCurrentFrame, this.aFrame.y, this.aFrame.width, this.aFrame.height);
    }
    
    public Animation(SpriteSheet sheet, String name, Rectangle frame, int count, float rate) {
        this.sourceSpriteSheet = sheet;
        this.aName = name;
        this.aFrame = frame;
        this.aFrameCount = count;
        this.aFrameRate = rate;
    }
    
    public void Update(GameTime gameTime) {
        if (aIsAnimating) {
            aElapsed += (float) (gameTime.elapsedGameTime.getMilliseconds() / 1000.0);

            if (aElapsed > aFrameRate) {
                aCurrentFrame = (aCurrentFrame + 1) % aFrameCount;

                aElapsed = 0.0f;
            }
        }
    }
    
    public void Render(Graphics g, int x, int y) {
        Rectangle source = this.getSourceRect();
        if(sourceSpriteSheet != null)
            this.sourceSpriteSheet.renderSprite(g, source.x, source.y, source.width, source.height, x, y);
    }
    
}
