package com.cecilectomy.dmge.examples;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import com.cecilectomy.dmge.Assets.AssetManager;
import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.Rendering.Renderers.Java2DRenderer;
import com.cecilectomy.dmge.Window.GameWindowFrame;

public class FrameMain extends GameWindowFrame {

	private static final long serialVersionUID = 1L;
	
	public static final String title = "GameWindowFrame";
	public static final long frameRate = 60L;
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	
	public FrameMain() {
		super(title, WIDTH, HEIGHT);
	}
	
	public static void main(String[] args) throws IOException {
		final FrameMain frame = new FrameMain();
		frame.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Java2DRenderer renderer = new Java2DRenderer(frame);
		renderer.setResolution(WIDTH, HEIGHT);
		
		GameBase gameBase = new GameBase(renderer);
		gameBase.setPreferredFPS(frameRate);
		
		gameBase.addGameObject(new GameObject(gameBase){
			
			String message = "Hello, World!";
			
			@Override
			public void render(Renderer renderer) {
				super.render(renderer);
				
				Graphics g = ((Java2DRenderer)renderer).getGraphics();
				
				int mHalfWidth = g.getFontMetrics().stringWidth(message) / 2;
				int mHalfHeight = g.getFontMetrics().getHeight() / 2;
				
				g.setColor(Color.orange);
				g.drawString(message, WIDTH/2 - mHalfWidth, HEIGHT/2 - mHalfHeight);
			}
			
		});
		
		gameBase.startThreaded();
	}
}