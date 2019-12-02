package com.app;

import java.awt.*;

public class Plane {
    public Status status;
    private double orientation;
    private double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    private final double timePass = 5; // number of minutes that passes each refresh
    private double dist;
    private double angleFromRunway; //DEGREES
    private double x; //in cartesian
    private double y; //in cartesian
    private GATE mGate;
    private enum GATE { // degrees = 180 + num
        NORTH,
        EAST,
        SOUTH,
        WEST,
    }


    public Plane() {
        status = Status.AIRSPACE;
        angleFromRunway = (Math.random() * 360);
        spawn();
    }

    public Plane(double a){
        status = Status.AIRSPACE;
        angleFromRunway = a;
        spawn();
    }

    private void spawn(){
        orientation = 0 - angleFromRunway;
        while(angleFromRunway < 0){
            angleFromRunway += 360;
        }
        System.out.println(angleFromRunway);
        if(angleFromRunway > 180){
            mGate = GATE.SOUTH;
        } else {
            mGate = GATE.NORTH;
        }
        speed = 10;
        dist = 100;
        x = dist * Math.cos(Math.toRadians(angleFromRunway));
        y = dist * Math.sin(Math.toRadians(angleFromRunway));
    }

    private void fixOrientation() {
        if(mGate == GATE.NORTH) {
            double delta_y = y - 35;
            orientation = angleOf(new Point((int)x,(int)y), new Point(0,35));
        } else if(mGate == GATE.SOUTH) {
            double delta_y = y + 35;
            orientation = angleOf(new Point((int)x,(int)y), new Point(0,35));
        }
        orientation += 90;
        while(orientation < 0){
            orientation += 360;
        }
        while (orientation>360){
            orientation -= 360;
        }
        angleFromRunway = 0 - orientation;

    }

    //TODO
    public double getETA() {
        double ETA = 0.0;
        return ETA;
    }

    /* public void turn(double d){
        orientation = d;
    }
    */
        //TODO: THIS DOESNT WORK
    private void move(){ //https://docs.google.com/drawings/d/1WxiXywrkvn3znghXam1VOWA2t7k-e1AVioBpkq5uCCE/edit?usp=sharing
        fixOrientation();
        final double move = speed / 60 * timePass;
        x += Math.cos(Math.toRadians(orientation - 90)) * move;
        y += Math.sin(Math.toRadians(orientation - 90)) * move;

    }

    private double angleOf(Point p1, Point p2) {
        final double deltaY = (p2.y - p1.y);
        final double deltaX = (p2.x - p1.x);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0) ? (360d + result) : result;
    }

    public void paint(Graphics2D g) throws InterruptedException {
        move();
        g.drawOval(0,0,10,10);
        g.drawRect((int) x, (int) y, 10, 10);
        if(mGate == GATE.NORTH) {
            g.drawLine((int) x, (int) y, 0, 35);
        } else if(mGate == GATE.SOUTH){
            g.drawLine((int) x, (int) y, 0, -35);
        }

        Thread.sleep(10);
        //System.out.println(x + ", " + y + "   " + (orientation));
    }

    //TODO
    @Override
    public String toString(){
        return "";
    }
}
