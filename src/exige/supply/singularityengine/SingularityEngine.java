package exige.supply.singularityengine;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.RandomLevel;
import exige.supply.singularityengine.modules.Bars;
import exige.supply.singularityengine.modules.Overwatch;
import exige.supply.vortex.VortexGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Singularity Engine Class.
 * Responsible for managing and holding all other engine classes together.
 *
 * @author Ali Shariatmadari
 * @version 1.0
 */

public class SingularityEngine extends Canvas implements Runnable {

    public static final String ENGINE_NAME = "Singularity Engine";
    public static final double VERSION = 1.01;

    private static final long serialVersionUID = 1L;

    public final static int SCALE = 5;
    public final static int WIDTH = 300;
    public final static int HEIGHT = WIDTH / 16 * 9;
    public boolean fullscreen;

    public final static int UP = 60;

    private String title = ENGINE_NAME + " v" + VERSION + " | ";
    private Thread thread;
    private JFrame frame;
    private boolean running;
    private boolean paused = false;

    private Screen screen;
    private Level level;
    private Player[] players;
    private Overwatch overwatch;
    private Bars bars;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create buffered image based on vortex height and width
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Access image data from image raster and store in engine pixels array

    public SingularityEngine() {
        init(false); // Init SingularityEngine
    }

    public SingularityEngine(boolean fullscreen) {
        init(false); // Init SingularityEngine
    }

    public SingularityEngine(String title, boolean fullscreen) {
        init(fullscreen); // Init SingularityEngine
        this.title += title + " | "; // Append title
        frame.setTitle(this.title); // Set game title
    }

    public SingularityEngine(String title) {
        init(false); // Init SingularityEngine
        this.title += title + " | "; // Append title
        frame.setTitle(this.title); // Set game title
    }

    private void init(boolean fullscreen) {
        screen = new Screen(WIDTH, HEIGHT);
        overwatch = new Overwatch(screen, players);
        bars = new Bars(screen, players);
        this.fullscreen = fullscreen;

        // DEFAULT VALUES
        setLevel(new RandomLevel(64, 100)); // Set level to Random by default
        Player[] defaultPlayers = new Player[1]; // Singeplayer JACK by default
        defaultPlayers[0] = new Player(PlayerCharacter.JACK, level, 1);
        setPlayers(defaultPlayers); // Load default players

        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size); // Set window dimensions
        frame = new JFrame(); // Instantiate game frame

        if (fullscreen) { // If the frame is desired to be full screen
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize area
            frame.setUndecorated(true); // Remove top bar
        }

        frame.setResizable(false); // Disable resize
        frame.add(this); // Add game engine to JFrame
        frame.pack(); // Fill entire window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close When the exit button is pressed
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true); // Show window
    }

    @Override
    public void run() { // Thread run
        // Setup timer
        long lastTime = System.nanoTime(); // Retrieve precise system time, pre render loop
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / UP; // Refresh constant - How many nano seconds must pass for one refresh cycle(N / UPS)
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus(); // Put game in focus
        while (running) {
            long now = System.nanoTime(); // Current time
            delta += (now - lastTime) / ns; // DeltaTime divided by refresh const
            lastTime = now; // Reset lastTime
            while (delta >= 1) { // If time elapsed is passed one update cycle or more
                update(); // Update game
                updates++; // Increment update counter
                delta--; // Decrement delta, if delta is still >=, rerun loop for the update() to catch up
            }
            render(); // Render frame
            frames++; // Increment frames per frame render

            if (System.currentTimeMillis() - timer > 1000) { // Once a second has passed
                timer += 1000; // Increment timer by one second
                frame.setTitle(title + frames + " FPS - " + updates + " UPS");
                updates = 0; // Reset updates calculation
                frames = 0; // Reset frames calculation
            }
        }
    }

    // update method run every update cycle to update the game state
    public void update() {
        if (paused) return;
        level.update();
    }

    // render method run every render cycle to render game state
    public void render() {
        if (paused) return;
        if (getBufferStrategy() == null) { // if buffer strategy is non-existent,
            createBufferStrategy(3); // Create triple buffer
        }

        BufferStrategy bs = getBufferStrategy(); // Retrieve the buffer strategy
        screen.clear(); // Clear screen
        level.render(overwatch.getX(), overwatch.getY(), screen); // Render current screen
        bars.render(); // Render bars

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixels()[i]; // Write screen pixels to buffered pixels
        }
        Graphics g = bs.getDrawGraphics(); // Link graphics object to buffer strategy
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Draw image to Graphics object
        g.dispose(); // Empty rendered graphics from memory
        bs.show(); // Show calculated buffer
    }

    // SingularityEngine thread start
    public synchronized void start() {
        System.out.println("Starting Game Thread!");
        running = true;
        thread = new Thread(this, title);
        thread.start();
    }

    // SingularityEngine thread stop
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        frame.dispose(); // Close game window
    }

    /**
     * Should be used by outside classes to pause
     * Sets Game pause
     * displays pause menu
     */
    public void pause() {
        if (!paused) setPaused(true);
    }

    /**
     * Sets Game pause
     * displays pause menu
     *
     * @param state
     */
    public void setPaused(boolean state) {
        if (state) VortexGame.showPauseMenu();
        this.paused = state;
    }

    /**
     * @return pause state
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * Sets Game state
     *
     * @param state
     */
    public void setRunning(boolean state) {
        this.running = state;
    }

    /**
     * @return game state
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets Game title
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title + " |";
    }

    /**
     * Sets Game @{@link Level}
     *
     * @param level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Sets Game players
     *
     * @param players
     */
    public void setPlayers(Player[] players) {
        this.players = players; // Overwrite player array
        for (Player p : players) { // For every player
            addKeyListener(p.getCharacter().getKeys()); // Enable keyboard input
        }
        overwatch.setPlayers(players); // Introduce new players to Overwatch
        bars.setPlayers(players); // Introduce new players to Bars
    }

    /**
     * @return game @{@link Level}
     */
    public Level getLevel() {
        return level;
    }

    /**
     * @return game @{@link Player}s
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * @return game @{@link Screen}
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * @return fullscreen state
     */
    public boolean isFullscreen() {
        return fullscreen;
    }

}