package com.app;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Drivable {
    public boolean full;
    public LinkedList<GPlane> planes;
    protected Drivable before;
    protected Drivable after;
    protected Rectangle2D rect;
    protected Color color;
    protected boolean fill;
    protected int[] target;

    public Drivable(Drivable before, Rectangle2D rect, int[] target, Drivable after) {
        this.rect = rect;
        this.target = target;
        this.before = before;
        this.after = after;
    }

    public Drivable(Rectangle2D rect, int[] target, Drivable after) { //FIRST
        this.rect = rect;
        this.target = target;
        this.after = after;
    }

    public Drivable(Drivable before, Rectangle2D rect, int[] target) { //LAST
        this.rect = rect;
        this.target = target;
        this.before = before;
    }

    public Drivable(Rectangle2D rect, int[] target) {
        this.rect = rect;
        this.target = target;
    }

    public Drivable(Rectangle2D rect) {
        this.rect = rect;
    }

    public final void paint(Graphics2D g2d) {
        g2d.setColor(color);
        if (fill)
            g2d.fill(rect);
        else
            g2d.draw(rect);

        if (color == Color.darkGray) {
            g2d.setColor(Color.green);
            g2d.drawLine(before.target[0], before.target[1], this.target[0], this.target[1]);
        }

        g2d.setColor(Color.blue);
        g2d.fillOval(target[0], target[1], 4, 4);
    }

    public final String type() {
        return this.getClass().getName() + " {" + target[0] + ", " + target[1] + "}";
    }
}
