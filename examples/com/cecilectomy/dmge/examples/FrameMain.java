package com.cecilectomy.dmge.examples;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Rendering.GameRenderer;
import com.cecilectomy.dmge.Window.GameWindowFrame;

public class FrameMain extends GameWindowFrame {

	public FrameMain() {
		super("GameWindowFrame",new Dimension(640,480));
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		(new GameBase(new GameRenderer()){
			FrameMain frame;
			
			@Override
			protected void initialize() {
				super.initialize();
				
				final GameBase thisGame = this;
				
				frame = new FrameMain();
				frame.getFrame().addWindowListener(new WindowListener(){
					@Override
					public void windowActivated(WindowEvent e) {}
					@Override
					public void windowClosed(WindowEvent e) {}
					@Override
					public void windowClosing(WindowEvent e) {
						thisGame.stopThreaded();
					}
					@Override
					public void windowDeactivated(WindowEvent e) {}
					@Override
					public void windowDeiconified(WindowEvent e) {}
					@Override
					public void windowIconified(WindowEvent e) {}
					@Override
					public void windowOpened(WindowEvent e) {}
				});
				
				frame.setTitle("Foobar");
				frame.setViewport(new Dimension(800,600));
				frame.getGraphics().setColor(Color.BLACK);
				frame.getGraphics().drawString("Hello, World! ", 700, 10);
			}
		}).startThreaded();
	}
}