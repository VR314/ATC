package com.app;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Drivable {
    //public int maxPlanes; //inherits
    public LinkedList<Plane> planes; //inherits
    public boolean full; //inherits
    public int[] start;
    public int[] target; //inherits
    public Rectangle2D rect;

    public void addPlane(Plane p) {
        planes.addLast(p);
    }

    public void handoff(Drivable next, Plane p) {
        next.addPlane(p);
        planes.remove(p);
    }

    public void paint(Graphics2D g2d) {

        if (this.getClass().getName().equals("com.app.Runway")) {
            g2d.setColor(Color.black);
        } else {
            g2d.setColor(Color.gray);
        }
        g2d.fill(rect);

        g2d.setColor(Color.red);
        g2d.fillOval(target[0], target[1], 3, 3);

        g2d.setColor(Color.green);
        g2d.fillOval(start[0], start[1], 3, 3);

    }
}
