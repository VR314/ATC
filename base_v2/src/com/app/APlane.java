package com.app;

import java.awt.*;

public class APlane extends Plane {
    private double angleFromRunway;
    private double dist;
    private double x;
    private double y;
    public GATE mGate;

    enum GATE { // degrees = 180 + num
        NORTH,
        SOUTH,
    }

    public APlane(double a) {
        angleFromRunway = a;
        spawn();
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
        dist = 100;
        x = dist * Math.cos(Math.toRadians(angleFromRunway));
        y = dist * Math.sin(Math.toRadians(angleFromRunway));
    }

    private void turn() {
        double oldOrientation = orientation;
        if (mGate == GATE.NORTH) {
            orientation = angleOf(new Point((int) x, (int) y), new Point(0, 35));
        } else if (mGate == GATE.SOUTH) {
            orientation = angleOf(new Point((int) x, (int) y), new Point(0, -35));
        }

        if(oldOrientation - orientation > 10)
            orientation = oldOrientation - 10;
        else if(oldOrientation - orientation < -10)
            orientation = oldOrientation + 10;
        orientation += 90;
        while (orientation < 0) {
            orientation += 360;
        }
        while (orientation > 360) {
            orientation -= 360;
        }
        angleFromRunway = 0 - orientation;
    }

    @Override
    public void move() {
        turn();
        final double move = speed / 60 * 5; // 5 = timePass
        x += Math.cos(Math.toRadians(orientation - 90)) * move;
        y += Math.sin(Math.toRadians(orientation - 90)) * move;
        if (Math.abs(this.y) - 35 < 1 && Math.abs(this.x) < 1) {
            //TODO: at metered gate
        }
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

