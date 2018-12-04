package exige.supply.vortex.engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.security.Key;

import javax.swing.JFrame;

import exige.supply.vortex.entities.ExecutionerBullet;
import exige.supply.vortex.entities.Player;
import exige.supply.vortex.entities.Projectile;
import exige.supply.vortex.input.Keyboard;
import exige.supply.vortex.levels.Level;

public class GameEngine extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public final static int SCALE = 3;
    public final static int WIDTH = 300;
    public final static int HEIGHT = WIDTH / 16 * 9;

    public final static double UP = 60;

    private String title = "";
    private Thread thread;
    private JFrame frame;
    private boolean running;

    private Screen screen;
    private Keyboard keys;
    private Level level;
    private Player[] players;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Access image from image raster

    public GameEngine() {
        init(); // Init GameEngine
    }

    public GameEngine(String title) {
        init(); // Init GameEngine
        this.title = title + " | "; // Append title
        frame.setTitle(this.title); // Set game title
    }

    private void init() {
        level = new Level("res/textures/PeachyRuins.png");
        keys = new Keyboard(new int[]{KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F}); // TODO: IMPLEMENT KEYS PER PLAYER
        addKeyListener(keys); // Enable keyboard input
        players = new Player[2];
        players[0] = new Player(level, keys, 1); // TODO: IMPLEMENT KEYS PER PLAYER OR SOMETHING
        players[1] = new Player(level, keys, 1); // TODO: IMPLEMENT KEYS PER PLAYER
        screen = new Screen(WIDTH, HEIGHT);

        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size); // Set window dimensions
        frame = new JFrame();
        frame.setResizable(false); // Disable resize
        frame.add(this); // Add game engine to JFrame
        frame.pack(); // Fill entire window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true); // Show window
    }

    @Override
    public void run() {
        // Setup timer
        long lastTime = System.nanoTime(); // Retrieve precise system time, pre render loop
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / UP; // Refresh constant (N / UPS)
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus(); // Put game in focus
        while (running) {
            long now = System.nanoTime(); // Current time
            delta += (now - lastTime) / ns; // DeltaTime divided by refresh const
            lastTime = now; // Reset lastTime
            while (delta >= 1) { // update UP times a second
                update();
                updates++; // Increment update counter
                delta--; // Decrement delta, if delta is still >= wait for the update() to catch up
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

        stop(); // Stop game thread
    }

    public void update() {
        players[0].update();
        level.update();
    }

    public void render() { // Render Game
        BufferStrategy bs = getBufferStrategy(); // Retrieve the buffer strategy
        if (bs == null) { // if buffer strategy is non-existent,
            createBufferStrategy(3); // Create triple buffer
            return;
        }

        screen.clear(); // Clear screen
        level.render(players[0].x - screen.getWidth() / 2, players[0].y - screen.getHeight() / 2, screen); // Render current screen
        players[0].render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixels()[i]; // Write screen pixels to buffered pixels
        }
        Graphics g = bs.getDrawGraphics(); // Link graphics object to buffer strategy
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Draw image to Graphics object
        g.dispose(); // Empty rendered graphics from memory
        bs.show(); // Show calculated buffer
    }

    // GameEngine thread start
    public synchronized void start() {
        running = true;
        thread = new Thread(this, title + "GameEngine");
        thread.start();
    }

    // GameEngine thread stop
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String title) {
        this.title = title + " |";
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}