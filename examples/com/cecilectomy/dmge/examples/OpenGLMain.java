package com.cecilectomy.dmge.examples;

import java.awt.Dimension;

import org.lwjgl.opengl.Display;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Math.Transform;
import com.cecilectomy.dmge.OpenGL.Mesh;
import com.cecilectomy.dmge.OpenGL.Shader;
import com.cecilectomy.dmge.Rendering.Renderer;
import com.cecilectomy.dmge.Rendering.Renderers.OpenGLRenderer;
import com.cecilectomy.dmge.Window.GameWindowOGLFrame;

public class OpenGLMain extends GameWindowOGLFrame {

	public OpenGLMain() {
		super("GameWindowOGL",new Dimension(640,480));
	}
	
	public static void main(String[] args) {
		(new GameBase(new OpenGLRenderer()){
			OpenGLMain frame;

			@Override
			protected void initialize() {
				super.initialize();
				
				frame = new OpenGLMain();
				
				this.addGameObject(new PyramidMesh(this));
			}
			
			@Override
			protected void render() {
				super.render();

				if(Display.isCloseRequested()) {
					this.stopThreaded();
				}
				
				Display.update();
			}
			
			@Override
			protected void cleanUp() {
				super.cleanUp();
				
				frame.cleanUp();
			}
		}).startThreaded();
		
	}
	
}

class PyramidMesh extends GameObject {
	
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
		
		temp += (float)gameTime.elapsedGameTime.getNanoseconds() / 1000000000f; 
		
		float sinTemp = (float)Math.sin(temp);
		float cosTemp = (float)Math.cos(temp);
		
		transform.setTranslation(sinTemp, cosTemp, 3)
		.setRotation(0, sinTemp*180, 0)
		.setScale(	((float)Math.abs(sinTemp) + 0.5f) * 0.5f,
					((float)Math.abs(sinTemp) + 0.5f) * 0.5f,
					((float)Math.abs(sinTemp) + 0.5f) * 0.5f);
	}
	
	@Override
	public void render(Renderer renderer) {
		super.render(renderer);
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		mesh.draw();
	}
	
}