package exige.supply.vortex;

import java.util.Random;

/** @author Ali Shariatmadari
 *  Utility Method Class */

public class U {
	
	/**
	 * Returns a random number between min and max inclusive.
	 * 
	 * @param min - Min Number
	 * @param max - Max Number
	 * @return rand - random int between min and max
	 */
	public static int getRandomInt(int min, int max) {
		Random rand = new Random(); // Instantiate Random
		return rand.nextInt((max - min) + 1) + min; // Generate random int
	}
}
