package exige.supply.singularityengine;

import java.util.Random;

/**
 * Utility Method Class
 *
 * @author Ali Shariatmadari
 */

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

    /**
     * Rounds floating point error results to a certain precision degree.
     *
     * @param input     - the input
     * @param precision - the precision degree
     * @return FP-Corrected input at the desired precision degree
     */
    public static double roundFloatingPointError(double input, int precision) {
        input *= Math.pow(10, precision); // Multiply the FP-Errored input by 10 to the precision degree
        input = Math.round(input); // Round the result to the nearest integer
        input /= Math.pow(10, precision); // Shift decimal to original point
        return input; // Return corrected input
    }

    /**
     * Returns the bigger int between the two.
     *
     * @param input1 - the 1st input
     * @param input2 - the 2nd input
     * @return Bigger integer.
     */
    public static int getBiggerNumber(int input1, int input2) {
        if (input1 >= input2) return input1;
        return input2;
    }
}
