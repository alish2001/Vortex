package exige.supply.singularityengine.physics;

import exige.supply.singularityengine.U;

/**
 * 2D Vector Class.
 * Used to calculate trajectories and movement velocity.
 *
 * @author Ali Shariatmadari
 */

public class Vector {

    private final int FP_PRECISION = 1; // Floating point precision constant

    private double x, y, angle, speed = 0;
    private double trajectory;

    public Vector() {
        this.speed = 0;
    }

    public Vector(double speed, double xComponent, double yComponent) {
        this.speed = speed;
        this.x = xComponent;
        this.y = yComponent;
        calculateTrajectoryXY(); // Calculate missing values based on x y
    }

    public Vector(double speed, double angle, boolean radians) {
        if (!radians) { // If the angles are not in radians
            this.angle = U.roundFloatingPointError(Math.toRadians(angle), FP_PRECISION); // Convert to radians while account for floating point errors
        } else {
            this.angle = angle;
        }

        this.speed = speed;
        calculateTrajectoryAngle(); // Calculate missing values based on trajectory angle
    }

    public void setAngle(double radAngle) {
        this.angle = angle;
        calculateTrajectoryAngle(); // Recalculate trajectory based on new angle
    }

    /**
     * Sets the angle based on angles in degrees
     *
     * @param angle
     */
    public void setDegreeAngle(double angle) {
        this.angle = U.roundFloatingPointError(Math.toRadians(angle), 2); // Convert to radians while account for floating point errors
        calculateTrajectoryAngle(); // Calculate trajectory according to angle
    }

    // Calculates trajectory based on x and y component
    private void calculateTrajectoryXY() {
        angle = U.roundFloatingPointError(Math.atan2(y, x), FP_PRECISION); // Calculate trajectory angle based on x y
        trajectory = Math.sqrt((x * x) + (y * y)); // Calculate trajectory based on x y
    }

    // Calculates trajectory based on angle
    private void calculateTrajectoryAngle() {
        x = U.roundFloatingPointError(Math.cos(this.angle), FP_PRECISION); // Recalculate x
        y = U.roundFloatingPointError(Math.sin(this.angle), FP_PRECISION); // Recalculate y
        trajectory = Math.sqrt((x * x) + (y * y)); // Recalculate Trajectory
    }

    /**
     * Sets the x and y component and recalculates trajectory taking both into account
     *
     * @param x
     * @param y
     */
    public void setXY(double x, double y) {
        this.y = y;
        this.x = x;
        angle = U.roundFloatingPointError(Math.atan2(y, x), FP_PRECISION); // Recalculate Trajectory angle
        trajectory = Math.sqrt((y * y) + (x * x)); // Recalculate Trajectory
    }

    /**
     * Sets the x recalculates trajectory
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
        this.y = Math.sqrt((trajectory * trajectory) - (x * x));
    }

    /**
     * Sets the y recalculates trajectory
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
        this.x = Math.sqrt((trajectory * trajectory) - (y * y));
    }

    /**
     * Sets the speed of the vector
     *
     * @param speed
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return Trajectory speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @return Trajectory with speed
     */
    public double getTrajectory() {
        return trajectory * speed;
    }

    /**
     * @return Trajectory without speed
     */
    public double getRawTrajectory() {
        return trajectory;
    }

    /**
     * @return angle in radians
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return @{@link Directions} based on angle
     */
    public Directions getDirection() {
        // Get direction after converting angle from radians to degrees and accounting for floating point errors
        return Directions.getDirection(U.roundFloatingPointError(Math.toDegrees(angle), FP_PRECISION));
    }

    /**
     * @return x component devoid of speed
     */
    public double getX() {
        return x;
    }

    /**
     * @return y component devoid of speed
     */
    public double getY() {
        return y;
    }

    /**
     * @return x component with speed multiplier
     */
    public double getXComponent() {
        return x * speed;
    }

    /**
     * @return y with speed multiplier
     */
    public double getYComponent() {
        return y * speed;
    }
}