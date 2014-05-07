import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import java.awt.Dimension;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;
import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.OpenGL.Mesh;
import com.cecilectomy.dmge.OpenGL.Shader;
import com.cecilectomy.dmge.OpenGL.Transform;
import com.cecilectomy.dmge.OpenGL.Vertex;
import com.cecilectomy.dmge.OpenGL.Math.Vector3f;
import com.cecilectomy.dmge.Window.GameWindowOGL;

public class OpenGLMain extends GameWindowOGL {

	public OpenGLMain() {
		super("GameWindowOGL",new Dimension(640,480),60);
	}
	
	@Override
	protected void initOpenGL() {
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	public static void main(String[] args) {
		OpenGLMain frame = new OpenGLMain();
		
		frame.addComponent(new OGLComponent(frame));
		
		frame.start();
	}
	
}

class OGLComponent extends GameComponent {
	
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
	private GameInput input;

	public OGLComponent(GameBase game) {
		super(game);
	}
	
	@Override
	public void loadContent() {
		this.mesh = new Mesh();
		this.shader = new Shader();
		this.transform = new Transform();
		
		this.input = new GameInput(this.game);
		
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
		
		this.mesh.addVertices(verts, inds);
		this.shader.addVertexShader(Shader.load("basicVertex.vs"));
		this.shader.addFragmentShader(Shader.load("basicFragment.fs"));
		this.shader.compileProgram();
		this.shader.addUniform("transform");
	}
	
	float temp = 0.0f;
	
	@Override
	public void update(GameTime gameTime) {
		temp += (float)gameTime.elapsedGameTime.getMilliseconds() / 1000f;
		
		float sinTemp = (float)Math.sin(temp);
		float cosTemp = (float)Math.cos(temp);
		
		transform.setTranslation(sinTemp*0.5f, cosTemp*0.5f, 0);
		transform.setRotation(0, sinTemp*180, 0);
		transform.setScale(sinTemp, sinTemp, sinTemp);
	}
	
	@Override
	public void render(GameTime gameTime) {		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		this.shader.bind();
		this.shader.setUniform("transform", transform.getTransformation());
		mesh.draw();
	}
	
}