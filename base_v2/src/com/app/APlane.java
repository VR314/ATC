package com.app;

import java.awt.*;

public class APlane extends Plane {
    private double angleFromRunway;
    public double x;
    public double y;
    public GATE mGate;

    public APlane(double a, Airport airport) {
        angleFromRunway = a;
        this.airport = airport;
        gate = 1;
        //TODO: in algorithm, set gate on spawn
        spawn();
    }

    public APlane(Plane p, double a) {
        angleFromRunway = a;
        this.airport = p.airport;
        gate = 1;
        //TODO: in algorithm, set gate on spawn
        //TODO: set departing status, make plane leave
    }

    private void turn() {
        //double oldOrientation = orientation;
        if (mGate == GATE.NORTH) {
            orientation = angleOf(new Point((int) x, (int) y), new Point(0, 35));
        } else if (mGate == GATE.SOUTH) {
            orientation = angleOf(new Point((int) x, (int) y), new Point(0, -35));
        }

        orientation += 90;
        while (orientation < 0) {
            orientation += 360;
        }
        while (orientation > 360) {
            orientation -= 360;
        }
        angleFromRunway = 0 - orientation;

//        System.out.println(orientation);
    }

    @Override
    public void spawn() {
        orientation = 0 - angleFromRunway;
        while (angleFromRunway < 0) {
            angleFromRunway += 360;
        }
        System.out.println(angleFromRunway);
        if (angleFromRunway < 180) {
            mGate = GATE.NORTH;
        } else {
            mGate = GATE.SOUTH;
        }
        speed = 15;
        x = 100 * Math.cos(Math.toRadians(angleFromRunway));
        y = 100 * Math.sin(Math.toRadians(angleFromRunway));
    }

    @Override
    public void move() {
        turn();
        x += Math.cos(Math.toRadians(orientation - 90)) * speed / 10;
        y += Math.sin(Math.toRadians(orientation - 90)) * speed / 10;

    }

    enum GATE {
        NORTH,
        SOUTH,
    }

    @Override
    public void paint(Graphics2D g2d) {
        move();
        g2d.drawOval((int) x, (int) y, 5, 5);
        //draws a line to target for now... TODO: remove eventually
        if (mGate == GATE.NORTH) {
            g2d.drawLine((int) x, (int) y, 0, 35);
        } else if (mGate == GATE.SOUTH) {
            g2d.drawLine((int) x, (int) y, 0, -35);
        }
        //System.out.println(x + ", " + y + "   " + (orientation));
    }
}

