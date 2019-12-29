package com.app;

import java.awt.*;

public abstract class Plane {
    public int id;
    public Airport airport;
    public Status status;
    public double orientation;
    public double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    public int[] target;
    public int gate;

    public abstract void spawn();

    public abstract void move();

    public abstract void paint(Graphics2D g2d) throws InterruptedException;

    public final double angleOf(Point p1, Point p2) {
        final double deltaY = (p2.y - p1.y);
        final double deltaX = (p2.x - p1.x);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0) ? (360d + result) : result;
    }
}
