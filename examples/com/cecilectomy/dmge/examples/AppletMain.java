package com.cecilectomy.dmge.examples;
import javax.swing.JApplet;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.Window.GameWindowApplet;

@SuppressWarnings("serial")
public class AppletMain extends JApplet {
	
	GameBase gameBase;
	
    @Override
    public void init() {
    	GameWindowApplet applet = new GameWindowApplet(this, 640, 480);
    	Renderer gameRenderer = new Java2DRenderer(applet);
		gameRenderer.setResolution(640,480);
    	gameBase = new GameBase(gameRenderer);
    	gameBase.setPreferredFPS(60L);
    	gameBase.startThreaded();
    }
    
    @Override
    public void stop() {
    	gameBase.stopThreaded();
    }
}
