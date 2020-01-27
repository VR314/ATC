package com.app;

import java.awt.*;

public class APlane extends Plane {
    private double angleFromRunway;
    public GATE mGate;
    enum GATE {
        NORTH,
        SOUTH,
    }

    public APlane(double a, Airport airport, Airspace airspace) {
        angleFromRunway = a;
        coords = new double[]{(100 * Math.cos(Math.toRadians(angleFromRunway))), (100 * Math.sin(Math.toRadians(angleFromRunway)))};
        this.airspace = airspace;
        airspace.planes.add(this);
        this.airport = airport;
        gate = 1;
        //TODO: in algorithm, set gate on spawn
        spawn();
    }

    public APlane(Plane p, double a) {
        angleFromRunway = a;
        coords = new double[]{(100 * Math.cos(Math.toRadians(angleFromRunway))), (100 * Math.sin(Math.toRadians(angleFromRunway)))};
        this.airport = p.airport;
        this.airspace = p.airspace;
        airspace.planes.add(this);
        gate = 1;
        //TODO: in algorithm, set gate on spawn
        //TODO: set departing status, make plane leave
    }

    private void turn() {
        //double oldOrientation = orientation;
        if (mGate == GATE.NORTH) {
            orientation = angleOf(coords[0], coords[1], 0, 35);
        } else if (mGate == GATE.SOUTH) {
            orientation = angleOf(coords[0], coords[1], 0, -35);
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
    protected void spawn() {
        orientation = 0 - angleFromRunway;
        while (angleFromRunway < 0) {
            angleFromRunway += 360;
        }
        if (angleFromRunway < 180) {
            mGate = GATE.NORTH;
        } else {
            mGate = GATE.SOUTH;
        }
        speed = 15;
    }

    @Override
    protected void move() {
        turn();
        coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 10;
        coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 10;
    }

    @Override
    public void paint(Graphics2D g2d) {
        move();
        g2d.drawOval((int) coords[0], (int) coords[1], 5, 5);
        if (mGate == GATE.NORTH) {
            g2d.drawLine((int) coords[0], (int) coords[1], 0, 35);
        } else if (mGate == GATE.SOUTH) {
            g2d.drawLine((int) coords[0], (int) coords[1], 0, -35);
        }
    }

    public void toGPlane() {
        airspace.planes.remove(this);
        if (mGate == GATE.NORTH)
            new GPlane(this, GPlane.Direction.SOUTH);
        else
            new GPlane(this, GPlane.Direction.NORTH);
    }
}

