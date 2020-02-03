package app;

import java.awt.*;

public class APlane extends Plane {
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
    
    enum GATE {
        NORTH,
        SOUTH,
    }
    
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
        coords = new double[]{(100 * Math.cos(Math.toRadians(a))), (100 * Math.sin(Math.toRadians(a)))};
        this.airspace = airspace;
        this.airport = airport;
        gate = 1;
        this.id = id;
        this.pTimes = times;
        this.actualTimes = new int[pTimes.length];
        speed = 300;
        while (a < 0) {
            a += 360;
        }
        if (a < 180) {
            mGate = GATE.NORTH;
        } else {
            mGate = GATE.SOUTH;
        }
        orientation = 0 - a;
    }
    
    public APlane(Plane p, GATE g) { //gate determines direction of takeoff
        this.mGate = g;
        this.pTimes = p.pTimes;
        this.actualTimes = new int[pTimes.length];
        this.id = p.id;
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
        speed = 100; //TEMP
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
    }
    
    @Override
    public void spawn() {
        airspace.planes.add(this);
    }

    @Override
    protected void move() {
        if (!goAround) {
            if (!depart) {
                turn();
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 100;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 100;
                if (speed > 175) {
                    speed *= 0.95;
                }
    
            } else {
                if (speed < 300)
                    speed *= 1.005;
                if (mGate == GATE.NORTH)
                    coords[1] -= speed / 100.00;
                else
                    coords[1] += speed / 100.00;
    
                if (coords[1] > 200 || coords[1] < -250) {
                    leaveAirspace();
                }
            }
        } else {
            //TODO: implement go-around
        }
    }

    private void leaveAirspace() {
        this.airspace.planes.remove(this);
        System.out.println("APlane #" + id + " has left the airspace");
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
                "\n\ttowards {" + target[0] + ", " + target[1] + "}";
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

