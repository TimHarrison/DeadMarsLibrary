package com.cecilectomy.dmge.examples;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;
import com.cecilectomy.dmge.Rendering.Java2DRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
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
		
		gameBase.addGameComponent(new GameComponent(gameBase){
			
			String message = "Hello, World!";
			
			@Override
			public List<RenderDetails> getRenderDetails() {
				ArrayList<RenderDetails> details = new ArrayList<RenderDetails>();
				RenderDetails detail = new RenderDetails();
				detail.details.put("type", "Rectangle");
				detail.details.put("color", Color.white);
				detail.details.put("style", "fill");
				detail.details.put("rect", new Rectangle(100,100,100,100));
				details.add(detail);
				return details;
			}
			
//			@Override
//			public void render(Renderer renderer) {
//				super.render(renderer);
//				
//				Graphics g = ((Java2DRenderer)renderer).getGraphics();
//				
//				int mHalfWidth = g.getFontMetrics().stringWidth(message) / 2;
//				int mHalfHeight = g.getFontMetrics().getHeight() / 2;
//				
//				g.setColor(Color.orange);
//				g.drawString(message, WIDTH/2 - mHalfWidth, HEIGHT/2 - mHalfHeight);
//			}
			
		});
		
		gameBase.startThreaded();
	}
}