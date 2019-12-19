package com.app;


import java.awt.*;

public class APlane extends Plane {

    private double dist; //TODO: even needed?
    private double angleFromRunway; //DEGREES
    private double x; //in cartesian
    private double y; //in cartesian
    private GATE mGate;

    public APlane() {
        status = Status.AIRSPACE;
        angleFromRunway = (Math.random() * 360);
        spawn();
    }

    //TODO:
    //  -Implement freeze horizon, only ONE DIRECTION RUNWAY - land & takeoff from south -> north
    public APlane(double a) {
        status = Status.AIRSPACE;
        angleFromRunway = a;
        spawn();
    }

    /**
     * sets position and the metered gate the plane is scheduled to fly to
     */
    private void spawn() {
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

    /**
     * sets the orientation towards each metered gate
     */
    private void fixOrientation() {
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

    }

    /**
     * changes position of the plane using the angle of orientation and speed
     */
    private void move() { //https://docs.google.com/drawings/d/1WxiXywrkvn3znghXam1VOWA2t7k-e1AVioBpkq5uCCE/edit?usp=sharing
        fixOrientation();
        final double move = speed / 60 * 5; // 5 = timePass
        x += Math.cos(Math.toRadians(orientation - 90)) * move;
        y += Math.sin(Math.toRadians(orientation - 90)) * move;
        if (Math.abs(this.y) - 35 < 1 && Math.abs(this.x) < 1) {
            status = Status.MG;
        }
    }


    //TODO
    /*
    public double getETA() {
        double ETA = 0.0; //calculate ETA based on queue and
        return ETA;
    }

     public void turn(double d){
        orientation = d; //no sharp turns, gradual, slow turns
    }
    */

    /**
     * @param p1 source point
     * @param p2 target point
     * @return the angle between the source and target
     */
    private double angleOf(Point p1, Point p2) {
        final double deltaY = (p2.y - p1.y);
        final double deltaX = (p2.x - p1.x);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0) ? (360d + result) : result;
    }

    /**
     * paints the plane in its position
     *
     * @throws InterruptedException obligatory due to <code>Thread.sleep()</code> command
     */
    public void paint(Graphics2D g) throws InterruptedException {
        if (status == Status.AIRSPACE) {
            move();
            g.drawOval((int) x, (int) y, 5, 5);
            //draws a line to target for now... TODO: remove eventually
            if (mGate == GATE.NORTH) {
                g.drawLine((int) x, (int) y, 0, 35);
            } else if (mGate == GATE.SOUTH) {
                g.drawLine((int) x, (int) y, 0, -35);
            }

            Thread.sleep(20);
            //System.out.println(x + ", " + y + "   " + (orientation));
        } else {


        }
    }

    //TODO
    @Override
    public String toString() {
        return "";
    }

    private enum GATE { // degrees = 180 + num
        NORTH,
        SOUTH,
    }
}
