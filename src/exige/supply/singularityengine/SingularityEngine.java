package exige.supply.singularityengine;

import exige.supply.singularityengine.entities.Player;
import exige.supply.singularityengine.entities.PlayerCharacter;
import exige.supply.singularityengine.graphics.Screen;
import exige.supply.singularityengine.levels.Level;
import exige.supply.singularityengine.levels.RandomLevel;
import exige.supply.singularityengine.modules.Bars;
import exige.supply.singularityengine.modules.Overwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class SingularityEngine extends Canvas implements Runnable {

    public static final String ENGINE_NAME = "Singularity Engine";
    public static final double VERSION = 0.1;

    private static final long serialVersionUID = 1L;

    public final static int SCALE = 4;
    public final static int WIDTH = 300;
    public final static int HEIGHT = WIDTH / 16 * 9;

    public final static double UP = 60;

    private String title = ENGINE_NAME + " v" + VERSION + " | ";
    private Thread thread;
    private JFrame frame;
    private boolean running;

    private Screen screen;
    private Level level;
    private Player[] players;
    private Overwatch overwatch;
    private Bars bars;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Access image from image raster

    public SingularityEngine() {
        init(); // Init SingularityEngine
    }

    public SingularityEngine(String title) {
        init(); // Init SingularityEngine
        this.title += title + " | "; // Append title
        frame.setTitle(this.title); // Set game title
    }

    private void init() {
        screen = new Screen(WIDTH, HEIGHT);
        overwatch = new Overwatch(screen, players);
        bars = new Bars(screen, players);

        // DEFAULT VALUES
        setLevel(new RandomLevel(64, 100)); // Set level to Random by default
        Player[] defaultPlayers = new Player[1]; // Singeplayer JACK by default
        defaultPlayers[0] = new Player(PlayerCharacter.JACK, level,1);
        setPlayers(defaultPlayers); // Load default players

        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size); // Set window dimensions
        frame = new JFrame();
        frame.setResizable(false); // Disable resize
        frame.add(this); // Add game engine to JFrame
        frame.pack(); // Fill entire window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close When the exit button is pressed
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true); // Show window
    }

    @Override
    public void run() {
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

        stop(); // Stop game thread
    }

    public void update() {
        level.update();
    }

    public void render() { // Render Game
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
    }

    public void setTitle(String title) {
        this.title = title + " |";
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setPlayers(Player[] players){
        this.players = players; // Overwrite player array
        for (Player p : players){ // For every player
            addKeyListener(p.getCharacter().getKeys()); // Enable keyboard input
        }
        overwatch.setPlayers(players); // Introduce new players to Overwatch
        bars.setPlayers(players); // Introduce new players to Bars
    }

    public Level getLevel() {
        return level;
    }

    public Screen getScreen(){
        return screen;
    }
}