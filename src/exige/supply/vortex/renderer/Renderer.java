package exige.supply.vortex.renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.Buffer;

public class Renderer extends Canvas implements Runnable {

    public final static int scale = 5;
    public final static int width = 300;
    public final static int height = width / 16 * 9;

    public final static double UP = 60;

    private String title = "";
    private Thread thread;
    private JFrame frame;
    private boolean running;

    private Screen screen;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Create image
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
        screen = new Screen(width, height);
        Dimension size = new Dimension(width * scale, height * scale);
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
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) { // Once a second has passed
                timer += 1000; // Increment timer by one second
                frame.setTitle(title + frames + " FPS - " + updates + " UPS");
                updates = 0; // Reset updates calculation
                frames = 0; // Reset frames calculation
            }
        }

        stop(); // Stop thread
    }

    public void update() {

    }

    public void render() { // Render Game
        BufferStrategy bs = getBufferStrategy(); // Retrieve the buffer strategy
        if (bs == null) { // if buffer strategy is non-existent,
            createBufferStrategy(3); // Create triple buffer
            return;
        }

        screen.clear(); // Clear screen
        screen.render(); // Render current screen

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.getPixels()[i]; // Write screen to buffered image
        }
        Graphics g = bs.getDrawGraphics(); // Link graphics to buffer
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
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