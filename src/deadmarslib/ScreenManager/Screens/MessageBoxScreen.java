package deadmarslib.ScreenManager.Screens;

// <editor-fold default="collapsed" desc="Imports">
import deadmarslib.Game.GameInput;
import deadmarslib.Game.GameTime;
import deadmarslib.ScreenManager.Screen;
import deadmarslib.System.TimeSpan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
// </editor-fold>

/**
 * DeadMarsLibrary MessageBoxScreen Class
 * 
 * @author Daniel Cecil
 */
public class MessageBoxScreen extends Screen {

    // <editor-fold defaultstate="expanded" desc="Fields">
    String message;
    String optionOK;
    String optionCancel;
    String gradTexPath;
    BufferedImage gradientTexture;
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Events">
    protected void Accepted() { }

    protected void Cancelled() { }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Initialization">
    public MessageBoxScreen(String message, BufferedImage tex)
    {
        this.optionOK = "Enter = ok";
        this.optionCancel = "Escape = cancel";
        this.message = message;
        this.gradientTexture = tex;

        setIsPopup(true);

        setTransitionOnTime(TimeSpan.fromSeconds(0.2));
        setTransitionOffTime(TimeSpan.fromSeconds(0.2));
    }

    @Override
    public void LoadContent() {
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Handle Input">
    @Override
    public void HandleInput(GameInput input)
    {
        if(input.isKeyDown(KeyEvent.VK_ENTER)) {
            input.removeKeyDown(KeyEvent.VK_ENTER);
            Accepted();
            ExitScreen();
        } else if (input.isKeyDown(KeyEvent.VK_ESCAPE)) {
            input.removeKeyDown(KeyEvent.VK_ESCAPE);
            Cancelled();
            ExitScreen();
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Render">
    @Override
    public void Render(GameTime gameTime, Graphics g)
    {
        g.setFont(new Font("Arial Black", Font.BOLD, 12));
        this.getScreenManager().FadeBackBufferToBlack(g, this.getTransitionAlpha() * 2 / 3);

        int posx, posy;
        int textWidth = g.getFontMetrics().stringWidth(message);
        int textHeight = g.getFontMetrics().getHeight();

        posx = this.getScreenManager().Game.getResolution().width / 2;
        posy = this.getScreenManager().Game.getResolution().height / 2;

        final int hPad = 32;
        final int vPad = 16;

        Rectangle br = new Rectangle((int)posx - textWidth / 2 - hPad, (int)posy - textHeight - vPad, (int)posx + textWidth / 2 + hPad, (int)posy + textHeight * 2 + textHeight / 2 + vPad);

        g.setColor(Color.white);
        g.drawImage(gradientTexture, br.x, br.y, br.width, br.height, 0, 0, gradientTexture.getWidth(null), gradientTexture.getHeight(null), null);
        g.drawString(message, posx - textWidth / 2, posy);
        g.drawString(optionOK, posx - textWidth / 2, posy + textHeight);
        g.drawString(optionCancel, posx - textWidth / 2, posy + textHeight * 2);
    }
    // </editor-fold>

}
