package com.app;

import java.awt.geom.Rectangle2D;

public class Taxiway extends Drivable {
    /* INHERITED:
    public LinkedList<Plane> planes;
    public boolean full;
    public int[] target;
    public Rectangle2D rect;
     */

    public Taxiway(Rectangle2D rectangle, int[] start, int[] tar) {
        full = false;
        this.target = tar;
        this.rect = rectangle;
        //this.start = start;
    }
}
