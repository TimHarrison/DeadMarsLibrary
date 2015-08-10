package com.cecilectomy.dmge.examples;
import java.awt.Dimension;

import javax.swing.JApplet;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.Rendering.Renderers.Java2DRenderer;
import com.cecilectomy.dmge.Window.GameWindowApplet;

@SuppressWarnings("serial")
public class AppletMain extends JApplet {
	
	GameBase gameBase;
	
    @Override
    public void init() {
    	GameWindowApplet applet = new GameWindowApplet(this,new Dimension(640,480));
    	Renderer gameRenderer = new Java2DRenderer(applet);
		gameRenderer.setResolution(640,480);
    	gameBase = new GameBase(gameRenderer);
    	gameBase.startThreaded();
    }
    
    @Override
    public void stop() {
    	gameBase.stopThreaded();
    }
}
