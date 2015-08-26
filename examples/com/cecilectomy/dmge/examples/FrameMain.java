package com.cecilectomy.dmge.examples;

import java.awt.Color;
import java.awt.Font;
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
				
				{
					RenderDetails detail = new RenderDetails();
					detail.details.put("type", "Rectangle");
					detail.details.put("color", Color.white);
					detail.details.put("style", "fill");
					detail.details.put("rect", new Rectangle(100,100,100,100));
					details.add(detail);
				}
				
				{
					RenderDetails detail = new RenderDetails();
					detail.details.put("type", "Text");
					detail.details.put("justification", "center");
					detail.details.put("text", message);
					detail.details.put("color", Color.white);
					detail.details.put("font", new Font("Arial", Font.BOLD, 12));					
					detail.details.put("x", WIDTH/2);
					detail.details.put("y", HEIGHT/2);
					details.add(detail);
				}
				
				return details;
			}			
		});
		
		gameBase.startThreaded();
	}
}