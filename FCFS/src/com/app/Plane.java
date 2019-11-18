package com.app;

import java.awt.*;

public class Plane {
    public Status status;
    private double orientation;
    private double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    private final double timePass = 5; // number of minutes that passes each refresh
    private double dist;
    private double angleFromRunway; //RADIANS - POLAR COORDS
    private double distx; //in cartesian
    private double disty; //in cartesian
    private double[] meteredGateR; //r = 35
    private double[] meteredGateD; //C -> cartesian

    public Plane() {
        status = Status.AIRSPACE;
        spawn();
    }

    private void spawn(){
         angleFromRunway = Math.toRadians(Math.random() * 360);
        orientation = 0 - angleFromRunway;
        speed = 100;
        dist = 100;
        distx = dist * Math.cos(angleFromRunway);
        disty = dist * Math.sin(angleFromRunway);
        if(this.angleFromRunway > Math.PI){
            meteredGateR = new double[]{Math.PI * 1.5, 35};
            meteredGateD = new double[]{35* Math.cos(meteredGateR[0]), 35 * Math.sin(meteredGateR[0])};
        } else {
            meteredGateR = new double[]{Math.PI * 0.5 , 35};
            meteredGateD = new double[]{35* Math.cos(meteredGateR[0]), 35 * Math.sin(meteredGateR[0])};
        }
    }

    private void calcDistXY() {
        distx = dist * Math.cos(angleFromRunway);
        disty = dist * Math.sin(angleFromRunway);
    }

    private void fixOrientation() {
        orientation = -1.0/calcRotationAngle(meteredGateD, new double[]{distx, disty});
    }

    private double calcRotationAngle(double[] centerPt, double[] targetPt)
    {
        double theta = Math.atan2(targetPt[1] - centerPt[1], targetPt[0] - centerPt[0]);

        theta += Math.PI/2.0;

        return theta;
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

    //TODO - verify coords after calc (goes negative sometimes)
    private void move(){ //https://docs.google.com/drawings/d/1WxiXywrkvn3znghXam1VOWA2t7k-e1AVioBpkq5uCCE/edit?usp=sharing
        fixOrientation();
        calcDistXY();
        double theta = 90 - orientation;
        double move = speed / 60 * timePass;
        double movex = move * Math.cos(theta);
        double movey = move * Math.sin(theta);
        dist = dist - Math.sqrt(movex * movex + movey * movey);

    }

    public void paint(Graphics2D g) throws InterruptedException {
        move();
        g.drawRect((int)distx, (int)disty, 10, 10);
        g.drawLine((int)distx, (int) disty, (int)meteredGateD[0], (int)meteredGateD[1]);
        Thread.sleep(1500);
        System.out.println(Math.toDegrees(angleFromRunway) + "d, " + dist + "nm   " + Math.toDegrees(orientation));
    }

    //TODO
    @Override
    public String toString(){
        return "";
    }
}
