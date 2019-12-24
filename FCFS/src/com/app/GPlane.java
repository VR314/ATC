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
    private int[] gateTW;

    public GPlane(Airport a) {
        this.airport = a;
        spawn();
    }

    public GPlane(Airport a, int gate) {
        this.airport = a;
        this.gate = gate;
        spawn();
    }


    public void spawn() {
        this.target = airport.parts.get(0).target;
        this.status = Status.LAND;
        this.index = -1;
        this.x = 340;
        this.y = 0;
        this.speed = 24;
        switch (gate) {
            case 1:
            case 2:
                gateTW = airport.parts.get(2).target;
                break;
            case 3:
            case 4:
                gateTW = airport.parts.get(3).target;
                break;
            case 5:
            case 6:
                gateTW = airport.parts.get(4).target;
            default:
                break;
        }
        changeTarget();
    }

    public void changeTarget() {
        if (index >= 0 && airport.parts.get(index).target == gateTW) {
            orientation = 270;
            target[0] -= 50;
        } else {
            target = airport.parts.get(++index).target;
            orientation = 90 + angleOf(new Point(x, y), new Point(target[0], target[1]));
        }
    }

    public void move() {
        final double move = speed / 12; // 5 = timePass
        x += Math.cos(Math.toRadians(orientation - 90)) * move;
        y += Math.sin(Math.toRadians(orientation - 90)) * move;
        if (Math.hypot(x - target[0], y - target[1]) <= 2) {
            x = target[0];
            y = target[1];
            changeTarget();
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        move();
        g2d.setColor(Color.green);
        g2d.fillOval(x, y, 3, 3);
    }
}
