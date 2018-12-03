package exige.supply.vortex.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keyboard implements KeyListener {
	
    // TODO MAKE OBJECT
	private VortexKeyboard gameKeys;
	private HashMap<Integer, Boolean> keys; 
	public boolean up_W, down_S, left_A, right_D, up_ARROW, down_ARROW, left_ARROW, right_ARROW;
	
	public Keyboard() {
		keys = new HashMap<Integer, Boolean>();
	}
	
	public void update() {
		for (int i = 0; i < keys.length; i++) {
			if ([keys])
		}
	}
	
	public boolean isPressed() {
		
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true; // Key is pressed
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false; // Key is not pressed
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
}