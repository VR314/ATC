package app;

import java.awt.*;

public class APlane extends Plane {
    private double angleFromRunway;
    public boolean goAround = false; //controllable by algorithm
    private boolean depart = false; //controllable by algorithm
    public GATE mGate;

    enum GATE {
        NORTH,
        SOUTH,
    }

    public APlane(double a, Airport airport, Airspace airspace, int id) {
        angleFromRunway = a;
        coords = new double[]{(100 * Math.cos(Math.toRadians(angleFromRunway))), (100 * Math.sin(Math.toRadians(angleFromRunway)))};
        this.airspace = airspace;
        this.airport = airport;
        gate = 1;
        this.id = id;
    }

    public APlane(Plane p, GATE g) { //gate determines direction of takeoff
        this.mGate = g;
        coords = new double[]{0, 0};
        this.airport = p.airport;
        this.airspace = p.airspace;
        depart = true;
        if (g == GATE.NORTH)
            target = new double[]{0, 1000};
        else
            target = new double[]{0, -1000};

        orientation = angleOf(coords[0], coords[1], target[0], target[1]);
        System.out.println(this.toString());
        airspace.planes.add(this);
        speed = 10; //TEMP
        //TODO: in algorithm, set gate on spawn
    }

    private void turn() { //TODO: make turning more smooth, gradual
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
    }

    @Override
    public void spawn() { //call spawn() to spawn in airspace - algorithm
        orientation = 0 - angleFromRunway;
        airspace.planes.add(this);
        while (angleFromRunway < 0) {
            angleFromRunway += 360;
        }
        if (angleFromRunway < 180) {
            mGate = GATE.NORTH;
        } else {
            mGate = GATE.SOUTH;
        }
        speed = 15;
        //turn();
    }

    @Override
    protected void move() {
        if (!goAround) {
            if (!depart) {
                turn();
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 10;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 10;
            } else {
                if (mGate == GATE.NORTH)
                    coords[1] -= speed / 10.0;
                else
                    coords[1] += speed / 10.0;
            }
        } else {
            //TODO: implement go-around
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        move();
        g2d.drawOval((int) coords[0], (int) coords[1], 5, 5);
        if (!depart) {
            if (mGate == GATE.NORTH) {
                g2d.drawLine((int) coords[0], (int) coords[1], 0, 35);
            } else if (mGate == GATE.SOUTH) {
                g2d.drawLine((int) coords[0], (int) coords[1], 0, -35);
            }
        }
    }

    @Override
    public String toString() {
        return "APlane {" + coords[0] + ", " + coords[1] +
                "}\n\theading " + orientation +
                "\n\ttowards {" + target[0] + ", " + target[1];
    }

    public void toGPlane() {
        if (!depart) {
            airspace.planes.remove(this);
            if (mGate == GATE.NORTH)
                new GPlane(this, GPlane.Direction.SOUTH);
            else
                new GPlane(this, GPlane.Direction.NORTH);
        }
    }
}

