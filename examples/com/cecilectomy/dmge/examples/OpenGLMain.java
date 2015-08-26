package com.cecilectomy.dmge.examples;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.awt.Dimension;
import java.util.List;

import org.lwjgl.opengl.Display;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Math.Transform;
import com.cecilectomy.dmge.OpenGL.Mesh;
import com.cecilectomy.dmge.OpenGL.Shader;
import com.cecilectomy.dmge.Rendering.OpenGLRenderer;
import com.cecilectomy.dmge.Rendering.RenderDetails;
import com.cecilectomy.dmge.Window.GameWindowOGLFrame;

public class OpenGLMain extends GameWindowOGLFrame {

	public OpenGLMain() {
		super("GameWindowOGL",new Dimension(640,480));
	}
	
	public static void main(String[] args) {
		GameBase game = (new GameBase(new OpenGLRenderer()){
			OpenGLMain frame;

			@Override
			protected void initialize() {
				super.initialize();
				
				frame = new OpenGLMain();
				
				this.addGameComponent(new PyramidMesh(this));
			}
			
			@Override
			protected void render() {
				if(Display.isCloseRequested()) {
					this.stopThreaded();
				}
				
				super.render();
			}
			
			@Override
			protected void cleanUp() {
				super.cleanUp();
				
				frame.cleanUp();
			}
		});
		game.setPreferredFPS(60L);
		game.startThreaded();
		
	}
	
}

class PyramidMesh extends GameComponent {
	
	Mesh mesh;
	Shader shader;
	Transform transform;
	float temp = 0f;

	public PyramidMesh(GameBase game) {
		super(game);
	}
	
	@Override
	public void initialize() {
		super.initialize();
		
		Transform.setProjection(70, 640, 480, 0.1f, 1000);
		
		mesh = Mesh.load("monkey.obj");
		shader = new Shader();
		transform = new Transform();
		
		shader.addVertexShader(Shader.load("basicVertex.vs"));
		shader.addFragmentShader(Shader.load("basicFragment.fs"));
		shader.compileProgram();
		shader.addUniform("transform");
	}
	
	@Override
	public void update(GameTime gameTime) {
		super.update(gameTime);
		
		temp += (float)gameTime.getElapsedGameTime().getNanoseconds() / 1000000000f; 
		
		float sinTemp = (float)Math.sin(temp);
		float cosTemp = (float)Math.cos(temp);
		
		transform.setTranslation(sinTemp, cosTemp, 3)
		.setRotation(0, sinTemp*180, 0)
		.setScale(	((float)Math.abs(sinTemp) + 0.5f) * 0.5f,
					((float)Math.abs(sinTemp) + 0.5f) * 0.5f,
					((float)Math.abs(sinTemp) + 0.5f) * 0.5f);
	}
	
	@Override
	public List<RenderDetails> getRenderDetails() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		mesh.draw();
		
		return super.getRenderDetails();
	}
	
}