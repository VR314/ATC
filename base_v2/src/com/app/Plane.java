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

    public abstract void spawn(Airport airport);

    public abstract void move();

    public abstract void paint(Graphics2D g2d);

}
