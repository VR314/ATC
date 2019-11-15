package com.app;

public class Plane {
    public Status status;
    private double orientation;
    private double speed;
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

    private void calcDist(){
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

    public void turn(double d){
        orientation = d;
    }

    @Override
    public String toString(){
        return "";
    }
}
