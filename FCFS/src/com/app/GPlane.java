package com.app;

import java.awt.*;

public class GPlane extends Plane {
    /* INHERITED:
    public int id;
    public Airport airport;
    public Status status;
    public double orientation;
    public double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    public int[] target;
     */
    private int index; //index of part of airport
    private int x;
    private int y;

    public GPlane(Airport a) {
        this.airport = a;
        spawn();
    }

    public void spawn() {
        this.target = airport.parts.get(0).target;
        this.status = Status.LAND;
        this.index = 0;
        this.x = 340;
        this.y = 0;
        this.orientation = 180;
    }

    public void move() {


    }

    @Override
    public void paint(Graphics2D g) throws InterruptedException {
        g.setColor(Color.green);
        g.fillOval(x, y, 10, 10);
    }


}
