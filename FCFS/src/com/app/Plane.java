package com.app;

import java.awt.*;

public abstract class Plane {
    public int id;
    public Airport airport;
    public Status status;
    public double orientation;
    public double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    public int[] target;

    public abstract void spawn();

    public abstract void move();

    public abstract void paint(Graphics2D g) throws InterruptedException;
}
