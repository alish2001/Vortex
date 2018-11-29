package exige.supply.vortex.renderer;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import exige.supply.vortex.input.Keyboard;

public class Renderer extends Canvas implements Runnable {

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
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // Create image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // Access image from image raster

    public Renderer() {
        init(); // Init Renderer
    }

    public Renderer(String title) {
        init(); // Init Renderer
        this.title = title + " | "; // Append title
        frame.setTitle(this.title); // Set game title
    }

    private void init(){
        screen = new Screen(WIDTH, HEIGHT);
        keys = new Keyboard();
        addKeyListener(keys); // Enable keyboard input
        
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
        setPreferredSize(size); // Set window dimensions
        frame = new JFrame();
        frame.setResizable(false); // Disable resize
        frame.add(this); // Add game renderer to JFrame
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

        while (running) {
            long now = System.nanoTime(); // Current time
            delta += (now - lastTime) / ns; // DeltaTime divided by refresh const
            lastTime = now; // Reset lastTime
            while (delta >= 1){ // update UP times a second
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

        stop(); // Stop thread
    }
    
    int x = 0;
    int y = 0;
    public void update() {
    	keys.update();
    	if (keys.up) y--;
    	if (keys.down) y++;
    	if (keys.right) x++;
    	if (keys.left) x--;
    }

    public void render() { // Render Game
        BufferStrategy bs = getBufferStrategy(); // Retrieve the buffer strategy
        if (bs == null) { // if buffer strategy is non-existent,
            createBufferStrategy(3); // Create triple buffer
            return;
        }

        screen.clear(); // Clear screen
        screen.render(x, y); // Render current screen

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixels()[i]; // Write screen to buffered image
        }
        Graphics g = bs.getDrawGraphics(); // Link graphics to buffer
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // Draw image to Graphics object
        g.dispose(); // Empty rendered graphics from memory
        bs.show(); // Show calculated buffer
    }

    // Renderer thread start
    public synchronized void start() {
        running = true;
        thread = new Thread(this, title + "Renderer");
        thread.start();
    }

    // Renderer thread stop
    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setTitle(String title){
        this.title = title + " |";
    }
}