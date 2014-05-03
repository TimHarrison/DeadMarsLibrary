package deadmarslib.Window;

import java.awt.Dimension;

public interface GameWindow {
	public void setViewport(int width, int height);
	public void setViewport(Dimension size);
	public Dimension getViewport();
	
	public void setResolution(int width, int height);
	public void setResolution(Dimension size);
	public Dimension getResolution();
}
