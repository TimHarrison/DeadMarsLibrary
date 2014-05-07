import java.awt.Dimension;

import javax.swing.JApplet;

import com.cecilectomy.dmge.Window.GameWindowApplet;

public class AppletMain extends JApplet {
	
	GameWindowApplet applet;
	
    @Override
    public void init() {
    	applet = new GameWindowApplet(this,new Dimension(640,480),60L);
    	applet.start();
    }
    
    @Override
    public void stop() {
    	applet.stop();
    }
}
