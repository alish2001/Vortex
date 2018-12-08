package exige.supply.vortex.engine.physics;

import exige.supply.vortex.U;

/** @author Ali Shariatmadari
 *  2D Vector Class */

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
        calculateTrajectoryAngle();
    }

    public void setDegreeAngle(double angle) {
        this.angle = U.roundFloatingPointError(Math.toRadians(angle), 2); // Convert to radians while account for floating point errors
        calculateTrajectoryAngle(); // Calculate trajectory according to angle
    }

    private void calculateTrajectoryXY(){
        angle = U.roundFloatingPointError(Math.atan2(y, x), FP_PRECISION); // Calculate trajectory angle based on x y
        trajectory = Math.sqrt((x * x) + (y * y)); // Calculate trajectory based on x y
    }

    private void calculateTrajectoryAngle(){
        x = U.roundFloatingPointError(Math.cos(this.angle), FP_PRECISION); // Recalculate x
        y = U.roundFloatingPointError(Math.sin(this.angle), FP_PRECISION); // Recalculate y
        trajectory = Math.sqrt((x * x) + (y * y)); // Recalculate Trajectory
    }

    public void setXY(double x, double y){
        this.y = y;
        this.x = x;
        angle = U.roundFloatingPointError(Math.atan2(y, x), FP_PRECISION); // Recalculate Trajectory angle
        trajectory = Math.sqrt((y * y) + (x * x)); // Recalculate Trajectory
    }

    public void setX(double x){
        this.x = x;
        this.y = Math.sqrt((trajectory * trajectory) - (x * x));
    }

    public void setY(double y){
        this.y = y;
        this.x = Math.sqrt((trajectory * trajectory) - (y * y));
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public double getTrajectory() {
        return trajectory * speed;
    }

    public double getRawTrajectory() {
        return trajectory;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getXComponent() {
        return x * speed;
    }

    public double getYComponent() {
        return y * speed;
    }
}