package deadmarslib.Core;

import java.awt.Canvas;
import java.util.ArrayList;

/**
 * DeadMarsLib GameBase Class
 * <p>
 * This is a modified GamePanel class from the book 'Killer Game Programming in
 * Java' written by Andrew Davison.
 * <p>
 * Killer Game Programming in Java <br/>
 * Andrew Davison <br/>
 * O'Reilly, May 2005 <br/>
 * ISBN: 0-596-00730-2 <br/>
 * http://www.oreilly.com/catalog/killergame/ <br/>
 * Web Site for the book: http://fivedots.coe.psu.ac.th/~ad/jg
 * <p>
 * This also uses some elements and techniques from Notch's Ludum Dare 21 Entry
 * Prelude of the Chambered, which is open source. <br/>
 * http://www.mojang.com <br/>
 * http://www.ludumdare.com/compo/ludum-dare-21/?action=preview&uid=398
 * 
 * @author Daniel Cecil
 */
public class GameBase extends Canvas implements Runnable {
	
	public static long SECOND = 1000000000L;
	private static int NUM_FPS = 10;
	
	protected ArrayList<GameComponent> components = new ArrayList<>();
	
	private boolean isRunning = false;
	private boolean isPaused = false;
	protected GameTime gameTime = new GameTime();
	
	private long gameStartTime;
	private long gelapsedbefore = System.nanoTime();
	private long gelapsedafter = System.nanoTime();
	
	private double frameRate = 5000.0;
	private long updates;
	private long statsCount = 0;
	private long frameCount = 0;
	private double fpsStore[];
	private double averageFPS = 0.0;
	private double upsStore[];
	private double averageUPS = 0.0;
	
	public GameBase() {
		this.fpsStore = new double[NUM_FPS];
		this.upsStore = new double[NUM_FPS];
		for (int i = 0; i < NUM_FPS; i++) {
			this.fpsStore[i] = 0.0;
			this.upsStore[i] = 0.0;
		}
	}

	/**
	 * Retrieves the active status of the game.
	 * <p>
	 * The window is active when it is in focus.
	 * 
	 * @return whether or not the game is currently active.
	 */
	public final boolean getIsActive() {
		return !this.isPaused;
	}
	
	/**
	 * Retrieves the average frames per second this game is running at.
	 * 
	 * @return average frames per second this game is running at.
	 */
	public final double getCurrentFps() {
		return this.fpsStore[(int) this.statsCount % NUM_FPS];
	}

	/**
	 * Retrieves the current updates per second this game is running at.
	 * 
	 * @return current updates per second this game is running at.
	 */
	public final double getCurrentUps() {
		return this.upsStore[(int) this.statsCount % NUM_FPS];
	}

	/**
	 * Retrieves the average frames per second this game is running at.
	 * 
	 * @return average frames per second this game is running at.
	 */
	public final double getAverageFps() {
		return this.averageFPS;
	}

	/**
	 * Retrieves the average updates per second this game is running at.
	 * 
	 * @return average updates per second this game is running at.
	 */
	public final double getAverageUps() {
		return this.averageUPS;
	}
	
	/**
	 * Start the game thread.
	 * <p>
	 * Returns immediately if the game thread is already started. No game thread
	 * will be started.
	 */
	public synchronized void start() {
		if(this.isRunning) {
			return;
		}
		
		this.isRunning = true;
		new Thread(this).start();
	}
	
	/**
	 * Stop the game thread.
	 * <p>
	 * Sets the games running flag to false. All logic will be executed and the
	 * thread will break out of its loop and stop.
	 */
	public synchronized void stop() {
		if(!this.isRunning) {
			return;
		}
		
		this.isRunning = false;
	}
	
	protected void render() {
	}
	
	protected void update() {
		this.gelapsedafter = System.nanoTime();

		this.gameTime.elapsedGameTime.setSpan(this.gelapsedbefore,
				this.gelapsedafter);
		this.gameTime.elapsedRealTime.setSpan(this.gelapsedbefore,
				this.gelapsedafter);

		this.gameTime.totalGameTime.setSpan(this.gameStartTime,
				this.gelapsedafter);
		this.gameTime.totalRealTime.setSpan(this.gameStartTime,
				this.gelapsedafter);

		this.gelapsedbefore = System.nanoTime();

		if (this.isRunning && !this.isPaused) {
			for (int i = 0; i < this.components.size(); i++) {
				GameComponent gc = this.components.get(i);
				gc.update(gameTime);
			}
		}
		
		this.updates++;
	}
	
	protected void initialize() {
	}
	
	protected void cleanup() {
	}
	
	@Override
	public void run() {
		this.gameStartTime = System.nanoTime();
		
		final double frameTime = 1.0 / this.frameRate;
		
		this.frameCount = 0;
		long frameCounter = 0;
		
		long lastTime = System.nanoTime();
		double unProcessedTime = 0.0;
		
		this.initialize();
		
		while(isRunning) {
			boolean render = false;
			long startTime = System.nanoTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unProcessedTime += passedTime / (double)SECOND;
			frameCounter += passedTime;
			
			while(unProcessedTime > frameTime) {
				unProcessedTime -= frameTime;
				
				render = true;
				
				this.update();
				
				if(frameCounter >= SECOND) {
					updateFPSStats();
					frameCounter = 0;
				}
			}
			
			if(render) {
				this.render();
				this.frameCount++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		this.cleanup();
		
//		for(int x = 0; x < NUM_FPS; x++) {
//			System.out.println(this.fpsStore[x]);
//			System.out.println(this.upsStore[x]);
//		}
		System.out.println(this.averageFPS);
		System.out.println(this.averageUPS);
	}
	
	private void updateFPSStats() {	
		this.fpsStore[(int) this.statsCount % NUM_FPS] = this.frameCount;
		this.upsStore[(int) this.statsCount % NUM_FPS] = this.updates;
		this.statsCount++;
		this.frameCount = 0;
		this.updates = 0;
	
		double totalFPS = 0.0;
		double totalUPS = 0.0;
		for (int i = 0; i < NUM_FPS; i++) {
			totalFPS += this.fpsStore[i];
			totalUPS += this.upsStore[i];
		}

		if (this.statsCount < NUM_FPS) {
			this.averageFPS = totalFPS / this.statsCount;
			this.averageUPS = totalUPS / this.statsCount;
		} else {
			this.averageFPS = totalFPS / NUM_FPS;
			this.averageUPS = totalUPS / NUM_FPS;
		}
	}
	
	/**
	 * Sets the preferred frame rate of the game.
	 * <p>
	 * Frame rate is determined by the update rate. This actually sets the
	 * preferred update rate, and the frame rate attempts to match throughout
	 * the lifetime of the game.
	 * 
	 * @param fps
	 *            new preferred frame rate of the game.
	 */
	public final void setPreferredFPS(double fps) {
		this.frameRate = fps;
	}

	/**
	 * Not exactly sure what this is really for. I Think it's supposed to
	 * prevent the game from trying to 'catch up' after doing some loading
	 * operations. I don't think it actually does its job though. TODO: Review
	 * and fix this.
	 */
	public final void resetElapsedTime() {
		this.gameTime.elapsedGameTime.setSpan(0);
		this.gameTime.elapsedRealTime.setSpan(0);
	}

	/**
	 * Appends the specified {@link GameComponent} to the games component list.
	 * 
	 * @param gc
	 *            {@link GameComponent} to add.
	 */
	public final void addComponent(GameComponent gc) {
		gc.initialize();
		gc.loadContent();
		this.components.add(gc);
	}

	/**
	 * Removes the specified {@link GameComponent} from the games component
	 * list.
	 * 
	 * @param gc
	 *            {@link GameComponent} to remove.
	 */
	public final void removeComponent(GameComponent gc) {
		gc.unloadContent();
		this.components.remove(gc);
	}

	/**
	 * Resumes the Game from being paused.
	 */
	public final void unPause() {
		this.isPaused = false;
	}

	/**
	 * Pauses the Game.
	 */
	public final void pause() {
		this.isPaused = true;
	}

	/**
	 * Toggle whether the Game is paused.
	 */
	public final void togglePause() {
		this.isPaused = !this.isPaused;
	}
}
