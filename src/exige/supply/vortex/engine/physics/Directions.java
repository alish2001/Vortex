package exige.supply.vortex.engine.physics;

public enum Directions {

    NORTH(270),
    NORTH_EAST(315),
    NORTH_WEST(225),
    SOUTH(90),
    SOUTH_EAST(45),
    SOUTH_WEST(135),
    EAST(0),
    WEST(180);

    private double angle;
    Directions(double angle){
        this.angle = angle;
    }

    public double getAngle(){
        return this.angle;
    }

    public static Directions getDirection(int x, int y){
        int dir = x + y * 3; // 8-way direction calculation

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

    public static Directions getDirection(double angle){
        // Get directions based on angle intervals
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

}
