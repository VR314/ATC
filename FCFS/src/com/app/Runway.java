package com.app;

import java.awt.geom.Rectangle2D;

public class Runway extends Drivable {
    /* INHERITED:
    public int maxPlanes;
    public LinkedList<Plane> planes;
    public boolean full;
    public int[] target;
    public Rectangle2D rect;
     */

    public Runway() {
        full = false;
        start = new int[]{330, 60};
        target = new int[]{330, 385};
        rect = new Rectangle2D.Double(300, 50, 75, 350); //TODO: REMOVE HARDCODED COORDS - ALL MUST BE SET IN RUN CLASS
    }
}
