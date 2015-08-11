package com.cecilectomy.dmge.Core;

import java.util.ArrayList;

import com.cecilectomy.dmge.Rendering.Renderer;

public class GameBase implements Runnable {
	
	private static long SECOND = 1000000000L;
	private static int NUM_FPS = 10;
	
	private ArrayList<GameObject> gameObjects = new ArrayList<>();
	private ArrayList<GameObject> gameObjectsToAdd = new ArrayList<>();
	
	private Renderer renderer;
	private GameTime gameTime = new GameTime();
	
	private boolean isRunning = false;
	private boolean isPaused = false;
	
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

	private GameBase() {
		this.fpsStore = new double[NUM_FPS];
		this.upsStore = new double[NUM_FPS];
		for (int i = 0; i < NUM_FPS; i++) {
			this.fpsStore[i] = 0.0;
			this.upsStore[i] = 0.0;
		}
	}
	
	public GameBase(Renderer renderer) {
		this();
		this.renderer = renderer;
	}

	public final void unPause() {
		this.isPaused = false;
	}

	public final void pause() {
		this.isPaused = true;
	}

	public final void togglePause() {
		this.isPaused = !this.isPaused;
	}

	public final boolean getIsActive() {
		return !this.isPaused;
	}

	public final double getCurrentFps() {
		return this.fpsStore[(int) this.statsCount % NUM_FPS];
	}

	public final double getAverageFps() {
		return this.averageFPS;
	}

	public final double getCurrentUps() {
		return this.upsStore[(int) this.statsCount % NUM_FPS];
	}

	public final double getAverageUps() {
		return this.averageUPS;
	}

	public final void setPreferredFPS(double fps) {
		this.frameRate = fps;
	}
	
	public final GameTime getGameTime() {
		return this.gameTime;
	}
	
	public final Renderer getRenderer() {
		return this.renderer;
	}

	public synchronized void startThreaded() {
		if(this.isRunning) {
			return;
		}
		
		this.isRunning = true;
		new Thread(this).start();
	}

	public synchronized void stopThreaded() {
		if(!this.isRunning) {
			return;
		}
		
		this.isRunning = false;
	}
	
	public void start() {
		if(this.isRunning) {
			return;
		}
		
		this.isRunning = true;
		this.run();
	}

	public void stop() {
		if(!this.isRunning) {
			return;
		}
		
		this.isRunning = false;
	}
	
	protected void render() {
		if (this.isRunning && !this.isPaused) {
			for (int i = 0; i < this.gameObjects.size(); i++) {
				GameObject gc = this.gameObjects.get(i);
				this.renderer.render(gc);
			}
			
			this.renderer.update();
		}
	}
	
	protected void update() {
		this.gelapsedafter = System.nanoTime();

		this.gameTime.getElapsedGameTime().setSpan(this.gelapsedbefore,
				this.gelapsedafter);
		this.gameTime.getElapsedRealTime().setSpan(this.gelapsedbefore,
				this.gelapsedafter);

		this.gameTime.getTotalGameTime().setSpan(this.gameStartTime,
				this.gelapsedafter);
		this.gameTime.getTotalRealTime().setSpan(this.gameStartTime,
				this.gelapsedafter);

		this.gelapsedbefore = System.nanoTime();
		
		while(this.gameObjectsToAdd.size() > 0) {
			this._addGameObject(this.gameObjectsToAdd.get(this.gameObjectsToAdd.size()-1));
		}

		if (this.isRunning && !this.isPaused) {
			for (int i = 0; i < this.gameObjects.size(); i++) {
				GameObject gc = this.gameObjects.get(i);
				gc.update(gameTime);
			}
		}
		
		this.updates++;
	}
	
	protected void initialize() {
	}
	
	protected void cleanUp() {
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
		this.renderer.initialize();
		
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
					Thread.yield();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		this.renderer.cleanUp();
		this.cleanUp();
		System.exit(0);
	}
	
	private void updateFPSStats() {	
		this.fpsStore[(int) this.statsCount % NUM_FPS] = this.frameCount;
		this.upsStore[(int) this.statsCount % NUM_FPS] = this.updates;
		this.statsCount++;
		System.out.println(frameCount);
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

	public final void resetElapsedTime() {
		this.gameTime.getElapsedGameTime().setSpan(0);
		this.gameTime.getElapsedRealTime().setSpan(0);
	}

	public final void addGameObject(GameObject go) {
		this.gameObjectsToAdd.add(go);
	}
	
	private final void _addGameObject(GameObject go) {
		go.initialize();
		this.gameObjectsToAdd.remove(go);
		this.gameObjects.add(go);
	}

	public final void removeGameObject(GameObject go) {
		go.cleanUp();
		this.gameObjects.remove(go);
	}
}
