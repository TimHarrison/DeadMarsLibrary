package deadmarslib.Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
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

	private ArrayList<GameComponent> components = new ArrayList<>();

	private boolean running = false;
	private boolean isPaused = false;
	private boolean gameOver = false;
	private Dimension resolution = new Dimension();
	private boolean resChange = false;
	private Graphics dbg;
	private BufferedImage dbImage;
	private GameTime gameTime = new GameTime();
	private long updates = 0;

	private static final int NO_DELAYS_PER_YIELD = 16;
	private static int MAX_FRAME_SKIPS = 5;
	private long period;
	private long gameStartTime;
	private long framesSkipped = 0L;

	private long gelapsedbefore = System.nanoTime();
	private long gelapsedafter = System.nanoTime();

	private static long MAX_STATS_INTERVAL = 1000000000L;
	private static int NUM_FPS = 10;
	private long statsInterval = 0L;
	private long prevStatsTime;
	private long totalElapsedTime = 0L;
	private int timeSpentInGame = 0;
	private long frameCount = 0;
	private double fpsStore[];
	private long statsCount = 0;
	private double averageFPS = 0.0;
	private long totalFramesSkipped = 0L;
	private double upsStore[];
	private double averageUPS = 0.0;
	private DecimalFormat df = new DecimalFormat("0.##");

	/**
	 * Set the size of the games viewport.
	 * 
	 * @param width
	 *            Viewport width.
	 * @param height
	 *            Viewport height.
	 */
	public final void setViewport(int width, int height) {
		super.setSize(width, height);

		Dimension size = new Dimension(width, height);

		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
	}

	/**
	 * Set the size of the games viewport.
	 * 
	 * @param size
	 *            Viewport size.
	 */
	public final void setViewport(Dimension size) {
		super.setSize(size);

		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
	}

	/**
	 * Get the size of the game sviewport.
	 * 
	 * @return Dimension representation of the games viewport.
	 */
	public final Dimension getViewport() {
		return new Dimension(this.getWidth(), this.getHeight());
	}

	/**
	 * Set the viewport render resolution.
	 * 
	 * @param width
	 *            Resolution width.
	 * @param height
	 *            Resolution height.
	 */
	public final void setResolution(int width, int height) {
		this.resolution = new Dimension(width, height);
		this.resChange = true;
	}

	/**
	 * Set the viewport render resolution.
	 * 
	 * @param res
	 *            Resolution of viewport.
	 */
	public final void setResolution(Dimension res) {
		this.resolution = res;
		this.resChange = true;
	}

	/**
	 * Retrieves the dimensions of the draw surface.
	 * 
	 * @return Dimensions of window.
	 */
	public final Dimension getResolution() {
		return this.resolution;
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
	 * GameBase Constructor.
	 * 
	 * @param size
	 *            Preferred game window size.
	 * @param fps
	 *            Preferred game update rate.
	 */
	public GameBase(Dimension size, long fps) {
		this._init(size, size, fps);
	}

	/**
	 * GameBase Constructor.
	 * 
	 * @param size
	 *            Preferred game window size.
	 * @param res
	 *            Preferred game resolution.
	 * @param fps
	 *            Preferred game update rate.
	 */
	public GameBase(Dimension size, Dimension res, long fps) {
		this._init(size, res, fps);
	}

	private void _init(Dimension size, Dimension res, long fps) {
		this.setSize(size);
		this.setResolution(res);

		this.setBackground(Color.white);

		this.setFocusable(true);
		this.requestFocus();

		this.setPreferredFPS(fps);

		this.fpsStore = new double[NUM_FPS];
		this.upsStore = new double[NUM_FPS];
		for (int i = 0; i < NUM_FPS; i++) {
			this.fpsStore[i] = 0.0;
			this.upsStore[i] = 0.0;
		}
	}

	@Override
	public void run() {
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;
		long excess = 0L;

		this.gameStartTime = System.nanoTime();
		this.prevStatsTime = this.gameStartTime;
		beforeTime = this.gameStartTime;

		this.running = true;

		while (this.running) {
			this.update();
			this.render();

			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (this.period - timeDiff) - overSleepTime;

			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime / 1000000L);
				} catch (InterruptedException ex) {
				}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			} else {
				excess -= sleepTime;
				overSleepTime = 0L;

				if (++noDelays >= NO_DELAYS_PER_YIELD) {
					Thread.yield();
					noDelays = 0;
				}
			}

			beforeTime = System.nanoTime();

			int skips = 0;
			while ((excess > this.period) && (skips < MAX_FRAME_SKIPS)) {
				excess -= this.period;
				this.update();
				skips++;
			}
			this.framesSkipped += skips;

			this.storeStats();
		}
	}

	private void storeStats() {
		this.frameCount++;
		this.statsInterval += this.period;

		if (this.statsInterval >= MAX_STATS_INTERVAL) {
			long timeNow = System.nanoTime();
			this.timeSpentInGame = (int) ((timeNow - this.gameStartTime) / 1000000000L);

			long realElapsedTime = timeNow - this.prevStatsTime;
			this.totalElapsedTime += realElapsedTime;

			this.totalFramesSkipped += this.framesSkipped;

			double actualFPS = 0;
			double actualUPS = 0;
			if (this.totalElapsedTime > 0) {
				actualFPS = (((double) this.frameCount / this.totalElapsedTime) * 1000000000L);
				actualUPS = (((double) (this.frameCount + this.totalFramesSkipped) / this.totalElapsedTime) * 1000000000L);
			}

			this.fpsStore[(int) this.statsCount % NUM_FPS] = actualFPS;
			this.upsStore[(int) this.statsCount % NUM_FPS] = actualUPS;
			this.statsCount = this.statsCount + 1;

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

			this.framesSkipped = 0;
			this.prevStatsTime = timeNow;
			this.statsInterval = 0L;
		}
	}

	private void update() {
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

		if (this.running && !this.isPaused && !this.gameOver) {
			for (int i = 0; i < this.components.size(); i++) {
				GameComponent gc = this.components.get(i);
				gc.update(gameTime);
			}
			this.updates++;
		}
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}

		if (this.dbImage == null || this.resChange) {
			this.resChange = false;
			this.dbImage = new BufferedImage(this.resolution.width,
					this.resolution.height, BufferedImage.TYPE_INT_RGB);
		}

		this.dbg = this.dbImage.getGraphics();
		this.dbg.setColor(Color.black);
		this.dbg.fillRect(0, 0, this.getResolution().width,
				this.getResolution().height);

		for (int i = 0; i < this.components.size(); i++) {
			GameComponent gc = this.components.get(i);
			gc.render(this.gameTime, this.dbg);
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(this.dbImage, 0, 0, this.getWidth(), this.getHeight(), null);
		g.dispose();
		bs.show();
	}

	/**
	 * Start the game thread.
	 * <p>
	 * Returns immediately if the game thread is already started. No game thread
	 * will be started.
	 */
	public synchronized void start() {
		if (this.running) {
			return;
		}
		this.running = true;
		new Thread(this).start();
	}

	/**
	 * Stop the game thread.
	 * <p>
	 * Sets the games running flag to false. All logic will be executed and the
	 * thread will break out of its loop and stop.
	 */
	public synchronized void stop() {
		this.running = false;
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
	public final void setPreferredFPS(long fps) {
		this.period = ((long) 1000.0 / fps) * 1000000L;
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
