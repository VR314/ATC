package app;

import java.awt.*;

public abstract class Plane {
    public double[] coords;
    public int gate;
    public Airspace airspace;
    protected double[] target;
    protected int id;
    protected int speed;
    protected double orientation;
    public Airport airport;

    protected abstract void spawn();

    protected abstract void move();

    protected abstract void paint(Graphics2D g2d);

    protected final double angleOf(double x1, double y1, double x2, double y2) {
        final double deltaY = (y2 - y1);
        final double deltaX = (x2 - x1);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0) ? (360 + result) : result;
    }
}