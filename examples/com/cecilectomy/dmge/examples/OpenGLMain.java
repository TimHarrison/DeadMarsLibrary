package com.cecilectomy.dmge.examples;

import java.awt.Dimension;

import org.lwjgl.opengl.Display;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameObject;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.OpenGL.Mesh;
import com.cecilectomy.dmge.OpenGL.Shader;
import com.cecilectomy.dmge.OpenGL.Transform;
import com.cecilectomy.dmge.OpenGL.Vector3f;
import com.cecilectomy.dmge.OpenGL.Vertex;
import com.cecilectomy.dmge.Rendering.GameRenderer;
import com.cecilectomy.dmge.Rendering.Renderers.OpenGLGameRenderer;
import com.cecilectomy.dmge.Window.GameWindowOGLFrame;

public class OpenGLMain extends GameWindowOGLFrame {

	public OpenGLMain() {
		super("GameWindowOGL",new Dimension(640,480));
	}
	
	public static void main(String[] args) {
		(new GameBase(new OpenGLGameRenderer()){
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
		
		mesh = new Mesh();
		shader = new Shader();
		transform = new Transform();
		
		Vertex[] verts = new Vertex[]{
				new Vertex(new Vector3f(-1,-1,0)),
				new Vertex(new Vector3f(0,1,0)),
				new Vertex(new Vector3f(1,-1,0)),
				new Vertex(new Vector3f(0,0,1))
		};
		
		int[] inds = new int[]{
				0,1,3,
				3,1,2,
				2,1,0,
				0,3,2
		};
		
		mesh.addVertices(verts, inds);
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
		
		transform.setTranslation(sinTemp*0.5f, cosTemp*0.5f, 0);
		transform.setRotation(0, sinTemp*180, 0);
		transform.setScale(sinTemp, sinTemp, sinTemp);
	}
	
	@Override
	public void render(GameRenderer renderer) {
		super.render(renderer);
		
		shader.bind();
		shader.setUniform("transform", transform.getTransformation());
		mesh.draw();
	}
	
}