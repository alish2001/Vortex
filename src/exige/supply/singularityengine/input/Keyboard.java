package exige.supply.singularityengine.input;

import exige.supply.vortex.VortexGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

/**
 * Keyboard Class. Tracks assigned keys.
 *
 * @author Ali Shariatmadari
 */

public class Keyboard implements KeyListener {

    private HashMap<Integer, Boolean> keyTracker; // HashMap of each key and its pressed state

    public Keyboard(int[] trackedKeys) {
        keyTracker = new HashMap<Integer, Boolean>(); // Instantiate hashmap
        for (int i = 0; i < trackedKeys.length; i++) { // Loop through the trackedKeys array
            keyTracker.put(trackedKeys[i], false); // Store each key value that needs to be tracked
        }
    }

    // Checks to see if one of the tracked keys is currently pressed
    public boolean isPressed(int key) {
        if (keyTracker.containsKey(key)) { // If the keytracker hashmap contains an entry for the requested key
            return keyTracker.get(key); // return the entry value
        } else { // Otherwise
            return false; // Return false (not pressed) by default since the key isn't being tracked
        }
    }

    // Key is pressed event
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) VortexGame.vortex.pause(); // If esc is pressed, exit game
        if (keyTracker.containsKey(e.getKeyCode())) // If there is an entry in the keytracker hashmap for this key
            keyTracker.replace(e.getKeyCode(), true); // Set key pressed state to true
    }

    // Key is released event
    public void keyReleased(KeyEvent e) {
        if (keyTracker.containsKey(e.getKeyCode())) // If there is an entry in the keytracker hashmap for this key
            keyTracker.replace(e.getKeyCode(), false); // Set key pressed state to false
    }

    public void keyTyped(KeyEvent e) {

    }
}