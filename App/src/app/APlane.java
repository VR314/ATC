package app;

import java.awt.*;
import java.util.Arrays;

public class APlane extends Plane { //TODO: DEBUG LANDING - land set to null?
    /**
     * if set to true - APlane will abort takeoff and go around
     */
    public boolean goAround = false; //controllable by algorithm
    /**
     * the metered gate from which to initiate landing.
     */
    public GATE mGate;
    /**
     * used to set when the APlane is set to leave the Airspace after taking off
     */
    private boolean depart = false;
    
    /**
     * <p>
     * The constructor used by the Scenario class
     * </p>
     *
     * @param a        the angle from the runway to spawn the APlane
     * @param airport  the Airport attached to the object - useful when converting to GPlane on landing
     * @param airspace the Airspace to fly through
     * @param id       the id of the plane, used in toString() and generating it from the Scenario class
     * @param times    the array of plannedTimes (ETAs)
     */
    public APlane(double a, Airport airport, Airspace airspace, int id, int[] times) { //used by Scenario
        coords = new double[]{(150 * Math.cos(Math.toRadians(a))), (150 * Math.sin(Math.toRadians(a)))};
        this.airspace = airspace;
        this.airport = airport;
        gate = 1;
        this.id = id;
        this.pTimes = times;
        this.actualTimes = new int[pTimes.length];
        speed = 300;
    
        if (a < 180) {
            mGate = GATE.NORTH;
        } else {
            mGate = GATE.SOUTH;
        }
    
        if (mGate == GATE.NORTH)
            target = new double[]{0, 35};
        else
            target = new double[]{0, -35};
    
        orientation = 0 - a;
    }
    
    public APlane(Plane p, GATE g) { //gate determines direction of takeoff
        this.mGate = g;
        this.pTimes = p.pTimes;
        this.time = p.time;
        this.actualTimes = p.actualTimes;
        this.id = p.id;
        coords = new double[]{0, 0};
        this.airport = p.airport;
        this.airspace = p.airspace;
        depart = true;
        airspace.planes.add(this);
        speed = 100; //TEMP
        if (mGate == GATE.NORTH) {
            orientation = 0;
        } else if (mGate == GATE.SOUTH) {
            orientation = 180;
        }
    }
    
    private void turn() {
        double old = orientation;
        if (mGate == GATE.NORTH) {
            orientation = angleOf(coords[0], coords[1], 0, 35);
        } else if (mGate == GATE.SOUTH) {
            orientation = angleOf(coords[0], coords[1], 0, -35);
        }
        orientation += 90;
        
        if (old > orientation)
            orientation = (old - orientation) * 0.9 + orientation;
        else
            orientation = (orientation - old) * 0.9 + old;
        
        
        while (orientation < 0) {
            orientation += 360;
        }
        while (orientation > 360) {
            orientation -= 360;
        }
    }
    
    @Override
    public void spawn() {
        airspace.planes.add(this);
    }
    
    @Override
    protected void move() {
        if (!depart) {
            if(goAround) { //if go-around
                if (speed < 250)
                    speed *= 1.05;
                orientation = 180 + angleOf(this.coords[0], this.coords[1], 0, 0);
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 100;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 100;
            } else {
                turn();
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 100;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 100;
                if (speed > 175) {
                    speed *= 0.95;
                }
            }
        } else { //if leaving
            if (speed < 400)
                speed *= 1.05;
            coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 100;
            if (coords[1] > 200 || coords[1] < -250)
                leaveAirspace();
        }
    }
    
    private void leaveAirspace() {
        actualTimes[4] = (int) time.getMins();
        airspace.writePlane(this);
        this.airspace.planes.remove(this);
        System.out.println("APlane #" + id + " has left the airspace");
        System.out.println(Arrays.toString(actualTimes));
        if (airspace.planes.isEmpty() && airport.planes.isEmpty()) {
            System.out.println("Crashes: " + airport.crashes);
            airspace.end();
        }
    }
    
    @Override
    public void paint(Graphics2D g2d) {
        move();
        if (!airport.r.planes.isEmpty()) {
            goAround = true;
        } else if (!depart && Math.abs(coords[0]) < 5 && Math.abs(coords[1]) - 35 < 5) {
            toGPlane();
        }
        g2d.drawOval((int) coords[0], (int) coords[1], 5, 5);
        if (!depart && !goAround) {
            if (mGate == GATE.NORTH) {
                g2d.drawLine((int) coords[0], (int) coords[1], 0, (int)target[1]);
            } else if (mGate == GATE.SOUTH) {
                g2d.drawLine((int) coords[0], (int) coords[1], 0, (int)target[1]);
            }
        }
    }
    
    @Override
    public String toString() {
        return "APlane {" + coords[0] + ", " + coords[1] +
                "}\n\theading " + orientation +
                "\n\ttowards {" + target[0] + ", " + target[1] + "}";
    }
    
    public void toGPlane() {
        if (!depart && !goAround) {
            airspace.planes.remove(this);
            if (mGate == GATE.NORTH)
                new GPlane(this, GPlane.Direction.SOUTH);
            else
                new GPlane(this, GPlane.Direction.NORTH);
        }
    }
    
    public enum GATE {
        NORTH,
        SOUTH,
    }
}

