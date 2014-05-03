import java.awt.Dimension;

import deadmarslib.Window.GameWindowOGL;

public class OpenGLMain extends GameWindowOGL {

	public OpenGLMain() {
		super("GameWindowOGL",new Dimension(640,480),60);
	}
	
	public static void main(String[] args) {
		OpenGLMain frame = new OpenGLMain();
		frame.start();
	}
}
