package app;

import java.awt.*;

public abstract class Plane {
    public Time time;
    public double[] coords;
    public int gate;
    public Airspace airspace;
    public int id;
    public Airport airport;
    public int[] pTimes; //{LANDtime, atGatetime, leaveGatetime, TOtime, LEAVEtime}
    public int[] actualTimes;
    public double[] target;
    protected int speed;
    protected double orientation;
    
    public static double dist(double[] first, double[] sec) {
        return Math.hypot(Math.abs((int) (first[0] - sec[0])), Math.abs((int) (first[1] - sec[1])));
    }
    
    protected abstract void spawn();
    
    protected abstract void move();
    
    protected abstract void paint(Graphics2D g2d);
    
    protected final double angleOf(double x1, double y1, double x2, double y2) {
        final double deltaY = (y2 - y1);
        final double deltaX = (x2 - x1);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX));
        return (result < 0) ? (360 + result) : result;
    }
}