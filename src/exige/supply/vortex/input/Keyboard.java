package exige.supply.vortex.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    // TODO MAKE OBJECT
	private boolean keys[] = new boolean[120];
	public boolean up_W, down_S, left_A, right_D, up_ARROW, down_ARROW, left_ARROW, right_ARROW;
	
	public void update() {
		up_W = keys[KeyEvent.VK_W];
		up_ARROW = keys[KeyEvent.VK_UP];

		down_S = keys[KeyEvent.VK_S];
        down_ARROW = keys[KeyEvent.VK_DOWN];

        left_A = keys[KeyEvent.VK_A];
        left_ARROW = keys[KeyEvent.VK_LEFT];

        right_D = keys[KeyEvent.VK_D];
		right_ARROW = keys[KeyEvent.VK_RIGHT];
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
