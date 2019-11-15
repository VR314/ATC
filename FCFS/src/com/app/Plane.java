package com.app;

public class Plane {
    public Status status;
    private double orientation;
    private double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    private final double timePass = 5; // number of minutes that passes each refresh
    private double dist;
    private double angleFromRunway; //RADIANS - POLAR COORDS
    private double distx;
    private double disty;
    private double[] meteredGate; //r = 35

    public Plane() {
        status = Status.AIRSPACE;
        spawn();
    }

    private void spawn(){
         angleFromRunway = Math.toRadians(Math.random() * 45 + 45);
        if(Math.random() < 0.5){
            angleFromRunway = Math.PI + angleFromRunway;
        }
        orientation = 0 - angleFromRunway;
        distx = dist * Math.cos(angleFromRunway);
        disty = dist * Math.sin(angleFromRunway);
        speed = 150;
        dist = 100;
    }

    private void calcDistXY() {
        distx = dist * Math.cos(angleFromRunway);
        disty = dist * Math.sin(angleFromRunway);
    }

    public void fixOrientation() {
        orientation = -Math.atan((35 * (Math.sin(meteredGate[1])) + dist * (Math.sin(angleFromRunway))) / (35 * (Math.cos(meteredGate[1])) + dist * (Math.cos(angleFromRunway))));
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

    public void move(){ //https://docs.google.com/drawings/d/1WxiXywrkvn3znghXam1VOWA2t7k-e1AVioBpkq5uCCE/edit?usp=sharing
        double theta = 90 - orientation;
        double move = speed / 60 * timePass;
        double movex = move * Math.cos(theta);
        double movey = move * Math.sin(theta);
        dist = Math.sqrt(movex * movex + movey * movey);
    }

    //TODO
    @Override
    public String toString(){
        return "";
    }
}
