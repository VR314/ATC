package com.app;

import java.util.LinkedList;

public class Arrival {
    private LinkedList<Plane> planes; //newest last = remove first when processing

    public Arrival() {
        planes = new LinkedList<Plane>();
    }
/*
    private void refresh(Ground g) {
        Plane temp = planes.pollFirst();
        if (temp != null) {
            for(Plane p : planes){
                p.move();
                p.fixOrientation();

            }

        }


        for (Plane p : planes) {
            if (p.status == Status.LAND) {
                handoff(p, g);
            }
        }
    }

 */
     /**
     * moves the planes from arrival queue to the ground queue
     * @param p the plane to be 'handed off'
     * @param g the Ground object to 'hand off' the plane to
     * @return modified Ground object with the added plane
    */
    public Airport handoff(Plane p, Airport a) {
        Plane temp = planes.get(planes.indexOf(p));
        planes.remove(p);
        Airport airport = a;
        a.addPlane(temp);
        return a;
    }

    /**
     * adds a plane to the end of the Arrival queue
     * @param p plane to be added
     */
    public void addPlane(Plane p) {
        planes.addLast(p);
    }
}