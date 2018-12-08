package exige.supply.singularityengine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keyboard implements KeyListener {

    private HashMap<Integer, Boolean> keyTracker;

    public Keyboard(int[] trackedKeys) {
        keyTracker = new HashMap<Integer, Boolean>(); // Instantiate hashmap
        for (int i = 0; i < trackedKeys.length; i++) { // Loop through the trackedKeys array
            keyTracker.put(trackedKeys[i], false); // Store each key value that needs to be tracked
        }
    }

    // Checks to see if one of the tracked keys is currently pressed
    public boolean isPressed(int key) {
        if (keyTracker.containsKey(key)) {
            return keyTracker.get(key);
        } else {
            return false;
        }
    }

    public void keyPressed(KeyEvent e) {
        // Key is pressed
        if (keyTracker.containsKey(e.getKeyCode()))
            keyTracker.replace(e.getKeyCode(), true); // Set key pressed state to true
    }

    public void keyReleased(KeyEvent e) {
        // e
        if (keyTracker.containsKey(e.getKeyCode()))
            keyTracker.replace(e.getKeyCode(), false); // Set key pressed state to false
    }

    public void keyTyped(KeyEvent e) {

    }
}