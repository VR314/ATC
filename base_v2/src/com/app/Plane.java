package com.app;

import java.awt.*;
import java.util.LinkedList;

public abstract class Plane { //ALGORITHMS MAINLY LOOK @ THIS CLASS
    public int[] coords;
    public int gate;
    protected int[] target;
    protected int id;
    protected int speed;
    protected double orientation;
    protected LinkedList<Drivable> parts; //in spawn, reorder parts list depending on gate
    public Airport airport;

    public abstract void spawn();

    public abstract void move();

    public abstract void paint(Graphics2D g2d);

    public final double angleOf(Point p1, Point p2) {
        final double deltaY = (p2.y - p1.y);
        final double deltaX = (p2.x - p1.x);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0) ? (360d + result) : result;
    }

}
