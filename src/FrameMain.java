import java.awt.Dimension;

import deadmarslib.Window.GameWindowFrame;

public class FrameMain extends GameWindowFrame {

	public FrameMain() {
		super("GameWindowFrame",new Dimension(640,480),60);
	}
	
	public static void main(String[] args) {
		FrameMain frame = new FrameMain();
		frame.start();
	}
}
