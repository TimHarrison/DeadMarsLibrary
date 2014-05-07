import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import com.cecilectomy.dmge.Core.GameBase;
import com.cecilectomy.dmge.Core.GameComponent;
import com.cecilectomy.dmge.Core.GameInput;
import com.cecilectomy.dmge.Core.GameTime;
import com.cecilectomy.dmge.Window.GameWindowFrame;

public class FrameMain extends GameWindowFrame {

	public FrameMain() {
		super("GameWindowFrame",new Dimension(640,480),60);
	}
	
	public static void main(String[] args) {
		FrameMain frame = new FrameMain();
		frame.addComponent(new FrameComponent(frame));
		frame.start();
	}
}

class FrameComponent extends GameComponent {

	private GameInput input;

	public FrameComponent(GameBase game) {
		super(game);

		this.input = new GameInput(game);
	}

	@Override
	public void update(GameTime gameTime) {
		if(input.isKeyDown(KeyEvent.VK_ESCAPE)) {
			Window w = ((GameWindowFrame)this.game).getFrame();
            w.getToolkit().getSystemEventQueue().postEvent(new WindowEvent(w, WindowEvent.WINDOW_CLOSING));
		}
	}
	
}