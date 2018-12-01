package exige.supply.vortex.levels.pack;

import exige.supply.vortex.U;
import exige.supply.vortex.levels.Level;

public class RandomLevel extends Level {


    public RandomLevel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void generate() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int tileIndex = x + y * width;
                tiles[tileIndex] = U.getRandomInt(0, 3);
            }
        }
    }
}