package com.cecilectomy.dmge.examples;
import java.awt.Dimension;

import javax.swing.JApplet;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Rendering.GameRenderer;
import com.cecilectomy.dmge.Rendering.Renderers.Java2DGameRenderer;
import com.cecilectomy.dmge.Window.GameWindowApplet;

public class AppletMain extends JApplet {
	
	GameBase gameBase;
	
    @Override
    public void init() {
    	GameWindowApplet applet = new GameWindowApplet(this,new Dimension(640,480));
    	GameRenderer gameRenderer = new Java2DGameRenderer(applet);
		gameRenderer.setResolution(640,480);
    	gameBase = new GameBase(gameRenderer);
    	gameBase.startThreaded();
    }
    
    @Override
    public void stop() {
    	gameBase.stopThreaded();
    }
}
