package deadmarslib.Game;

// <editor-fold defaultstate="collapsed" desc="Imports">
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
// </editor-fold>

/**
 * DeadMarsLib GameBase Class
 * <p>
 * This is a modified GamePanel class from the book 'Killer Game Programming in Java'
 * written by Andrew Davison.
 * <p>
 * Killer Game Programming in Java
 * <br/>
 * Andrew Davison
 * <br/>O'Reilly, May 2005
 * <br/>ISBN: 0-596-00730-2
 * <br/>http://www.oreilly.com/catalog/killergame/
 * <br/>Web Site for the book: http://fivedots.coe.psu.ac.th/~ad/jg
 * <p>
 * This also uses some elements and techniques from Notch's Ludum Dare 21 Entry 
 * Prelude of the Chambered, which is open source.
 * <br/>
 * http://www.mojang.com
 * <br/>
 * http://www.ludumdare.com/compo/ludum-dare-21/?action=preview&uid=398
 * 
 * @author Daniel Cecil
 */
public class GameBase extends Canvas implements Runnable {
    
    // <editor-fold defaultstate="expanded" desc="Fields">
    
    private ArrayList<GameComponent> components = new ArrayList<>();
    
    //private Thread thread;
    private boolean running = false;
    private boolean isPaused = false;
    private boolean gameOver = false;
    private boolean isApplet = false;
    private boolean sizeChanged = false;
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
    
    // </editor-fold>

    // <editor-fold defaultstate="expanded" desc="Properties">
            
    @Override
    public final void setSize(int width, int height) {
        super.setSize(width, height);
        sizeChanged = true;
    }
    
    @Override
    public final void setSize(Dimension size) {
        super.setSize(size);
        sizeChanged = true;
    }
    
    /**
     * Wrapper for the setSize method.
     * 
     * @param width
     * @param height 
     */
    public final void setResolution(int width, int height) {
        this.setSize(width, height);
    }
    
    /**
     * Wrapper for the setSize method.
     * 
     * @param size 
     */
    public final void setResolution(Dimension size) {
        this.setSize(size);
    }
    
    /**
     * Retrieves the dimensions of the draw surface.
     * 
     * @return Dimensions of window.
     */
    public Dimension getResolution() {
        return new Dimension(this.dbImage.getWidth(), this.dbImage.getHeight());
    }
    
    /**
     * Retrieves the active status of the game.
     * <p>
     * The window is active when it is in focus.
     * 
     * @return whether or not the game is currently active.
     */
    public boolean getIsActive() {
        return !isPaused;
    }

    /**
     * Retrieves whether or not this game is an Applet.
     * 
     * @return whether or not this game is an Applet.
     */
    public boolean getIsApplet() {
        return isApplet;
    }

    /**
     * Sets this games applet status.
     * <p>
     * A non-applet game can be manually set to be an 'applet'
     * although it will not actually turn the game into an applet.
     * 
     * @param flag new applet state.
     */
    public void setIsApplet(boolean flag) {
        isApplet = flag;
    }
    
    /**
     * Retrieves the average frames per second this game is running at.
     * 
     * @return average frames per second this game is running at.
     */
    public double getStatsAvgFps() {
        return averageFPS;
    }
    
    /**
     * Retrieves the average updates per second this game is running at.
     * 
     * @return average updates per second this game is running at.
     */
    public double getStatsAvgUps() {
        return averageUPS;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Initialization">
    
    /**
     * Constructor
     * 
     * @param size Preferred game size.
     * @param fps Preferred game update rate.
     */
    public GameBase(Dimension size, long fps) {
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        
        setBackground(Color.white);
        
        setFocusable(true);
        requestFocus();
        
        setPreferredFPS(fps);
        
        dbImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        
        fpsStore = new double[NUM_FPS];
        upsStore = new double[NUM_FPS];
        for (int i = 0; i < NUM_FPS; i++) {
            fpsStore[i] = 0.0;
            upsStore[i] = 0.0;
        }
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Update and Render">

    @Override
    public void run() {
        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;

        gameStartTime = System.nanoTime();
        prevStatsTime = gameStartTime;
        beforeTime = gameStartTime;        
        
        running = true;

        while (running) {
            gameUpdate();
            gameRender();
            //paintScreen();
            
            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (period - timeDiff) - overSleepTime;

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
            while ((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                excess -= period;
                gameUpdate();
                skips++;
            }
            framesSkipped += skips;
            
            storeStats();
        }

        printStats();
        System.exit(0);
    }
    
    private void storeStats() {
        frameCount++;
        statsInterval += period;

        if (statsInterval >= MAX_STATS_INTERVAL) {
            long timeNow = System.nanoTime();
            timeSpentInGame = (int) ((timeNow - gameStartTime) / 1000000000L);

            long realElapsedTime = timeNow - prevStatsTime;
            totalElapsedTime += realElapsedTime;

            //double timingError =
            //        ((double) (realElapsedTime - statsInterval) / statsInterval) * 100.0;

            totalFramesSkipped += framesSkipped;

            double actualFPS = 0;
            double actualUPS = 0;
            if (totalElapsedTime > 0) {
                actualFPS = (((double) frameCount / totalElapsedTime) * 1000000000L);
                actualUPS = (((double) (frameCount + totalFramesSkipped) / totalElapsedTime)
                        * 1000000000L);
            }

            fpsStore[(int) statsCount % NUM_FPS] = actualFPS;
            upsStore[(int) statsCount % NUM_FPS] = actualUPS;
            statsCount = statsCount + 1;

            double totalFPS = 0.0;
            double totalUPS = 0.0;
            for (int i = 0; i < NUM_FPS; i++) {
                totalFPS += fpsStore[i];
                totalUPS += upsStore[i];
            }

            if (statsCount < NUM_FPS) {
                averageFPS = totalFPS / statsCount;
                averageUPS = totalUPS / statsCount;
            } else {
                averageFPS = totalFPS / NUM_FPS;
                averageUPS = totalUPS / NUM_FPS;
            }

            framesSkipped = 0;
            prevStatsTime = timeNow;
            statsInterval = 0L;
        }
    }
    
    private void gameUpdate() {
        gelapsedafter = System.nanoTime();
        
        gameTime.elapsedGameTime.setSpan(gelapsedbefore, gelapsedafter);
        gameTime.elapsedRealTime.setSpan(gelapsedbefore, gelapsedafter);
        
        gameTime.totalGameTime.setSpan(gameStartTime, gelapsedafter);
        gameTime.totalRealTime.setSpan(gameStartTime, gelapsedafter);
        
        gelapsedbefore = System.nanoTime();
        
        if (running && !isPaused && !gameOver) {
            for(int i = 0; i < components.size(); i++) {
                GameComponent gc = components.get(i);
                gc.update(gameTime);
            }
            updates++;
        }
    }
    
    private void gameRender() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
                createBufferStrategy(3);
                requestFocus();
                return;
        }
        
        if (dbImage == null || sizeChanged) {
            Dimension size = new Dimension(getWidth(), getHeight());
            setSize(size);
            setMinimumSize(size);
            setMaximumSize(size);
            setPreferredSize(size);
            
            sizeChanged = false;
            
//            try{
                dbImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//            } catch(Exception e) {
//                dbImage = null;
//                System.out.println("Render Error: " + e);
//                System.out.println("Render Error: Buffer not initialized properly");
//                System.out.println("Render Error: Resolving...");
//                return;
//            }
        }

        dbg = dbImage.getGraphics();
        dbg.setColor(Color.black);
        dbg.fillRect(0, 0, this.getResolution().width, this.getResolution().height);

        for(int i = 0; i < components.size(); i++) {
            GameComponent gc = components.get(i);
            gc.render(gameTime, dbg);
        }
        
        Graphics g = bs.getDrawGraphics();
        //g.fillRect(0, 0, this.getResolution().width, this.getResolution().height);
        g.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }
    
//    private void paintScreen() {
//        BufferStrategy bs = getBufferStrategy();
//        if (bs == null) {
//                createBufferStrategy(3);
//                requestFocus();
//                return;
//        }
//                
//        Graphics g = bs.getDrawGraphics();
//        //g.fillRect(0, 0, this.getResolution().width, this.getResolution().height);
//        g.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
//        g.dispose();
//        bs.show();
//    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="expanded" desc="Public Methods">
    
    /**
     * Attempts to start the game thread.
     */
    public synchronized void startGame() {
        if (running) {
            return;
        }
        running = true;
        new Thread(this).start();
    }
    
    /**
     * Attempts to stop the game thread.
     */
    public synchronized void stopGame() {
        if (!running) {
            return;
        }
        running = false;
    }
    
    /**
     * Changes the preferred frame rate of the game.
     * <p>
     * Frame rate is determined by the update rate. This actually sets
     * the preferred update rate, and the frame rate attempts to match
     * throughout the lifetime of the game.
     * 
     * @param fps new preferred frame rate of the game.
     */
    public final void setPreferredFPS(long fps) {
        this.period = ((long) 1000.0 / fps) * 1000000L;
    }
    
    /**
     * Not exactly sure what this is really for.
     * I Think it's supposed to prevent the game from trying to
     * 'catch up' after doing some loading operations. I don't think
     * it actually does its job though.
     */
    public final void resetElapsedTime() {
        gameTime.elapsedGameTime.setSpan(0);
        gameTime.elapsedRealTime.setSpan(0);
    }
    
    /**
     * Appends the specified {@link GameComponent} to this games component list.
     * 
     * @param gc {@link GameComponent} to add.
     */
    public void addComponent(GameComponent gc) {
        gc.initialize();
        gc.loadContent();
        components.add(gc);
    }

    /**
     * Removes the specified {@link GameComponent} from this games component list.
     * @param gc {@link GameComponent} to remove.
     */
    public void removeComponent(GameComponent gc) {
        gc.unloadContent();
        components.remove(gc);
    }
    
    /**
     * Resumes the game from being paused.
     */
    public void resumeGame() {
        isPaused = false;
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        isPaused = true;
    }
    
    // </editor-fold>
    
    /**
     * Prints some general performance statistics to the console.
     */
    public void printStats() {
        System.out.println("Frame Count/Loss: " + frameCount + " / " + totalFramesSkipped);
        System.out.println("Average FPS: " + df.format(averageFPS));
        System.out.println("Average UPS: " + df.format(averageUPS));
        System.out.println("Time Spent: " + timeSpentInGame + " secs");
        System.out.println("Total Updates: " + updates);
        System.out.println("dbImage: " + dbImage.toString());
    }

}
