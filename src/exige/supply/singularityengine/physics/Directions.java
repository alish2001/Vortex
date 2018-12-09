package exige.supply.singularityengine.physics;

public enum Directions {

    NORTH(270, 0, 1),
    NORTH_EAST(315, -1, 1),
    NORTH_WEST(225, 1, 1),
    SOUTH(90, 0, -1),
    SOUTH_EAST(45, 1, -1),
    SOUTH_WEST(135, -1, -1),
    EAST(0, -1, 0),
    WEST(180, 1, 0);

    private double angle;
    private int xOffset, yOffset; // intended placement offsets (up, down, left, right)

    Directions(double angle, int xOffset, int yOffset) {
        this.angle = angle;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public double getAngle() {
        return this.angle;
    }

    public int getXPlacementOffset() {
        return xOffset;
    }

    public int getYPlacementOffset() {
        return yOffset;
    }

    // Get directions based on x and y coords
    public static Directions getDirection(int x, int y) {
        int dir = x + y * 3; // 8-way direction calculation - by multiplying the sum of x and y by 3, we get an integer value for all 8 main directions

        // Complimentary calculations
        if (dir == 2) return SOUTH_WEST;
        if (dir == 4) return SOUTH_EAST;
        if (dir == -2) return NORTH_EAST;
        if (dir == -4) return NORTH_WEST;

        // Y axis calculation
        if (dir == 3) return SOUTH;
        if (dir == -3) return NORTH;

        // X axis calculation
        if (dir == 1) return EAST;
        if (dir == -1) return WEST;

        return SOUTH; //<---- FAIL-SAFE
    }

    // Get directions based on angle intervals
    public static Directions getDirection(double angle) {
        angle = Math.abs(angle); // Make the angle positive
        if (angle < 45 && angle >= 0) return EAST;
        if (angle < 90 && angle >= 45) return SOUTH_EAST;
        if (angle < 135 && angle >= 90) return SOUTH;
        if (angle < 180 && angle >= 135) return SOUTH_WEST;
        if (angle < 225 && angle >= 180) return WEST;
        if (angle < 270 && angle >= 225) return NORTH_WEST;
        if (angle < 315 && angle >= 270) return NORTH;
        if (angle < 360 && angle >= 315) return NORTH_EAST;

        return EAST; //<---- FAIL-SAFE
    }

    // Retrieve the direction (angle) directly opposite of the given input by subtracting 180
    public static Directions getOppositeDirection(Directions dir) {
        double angle = dir.getAngle();
        if (angle >= 180) { // If the angle is over 180, subtract to find opposite angle
            angle -= 180;
        } else {  // If the angle is less than 180, add to find opposite angle
            angle += 180;
        }

        return getDirection(angle);
    }

}
