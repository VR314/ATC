package com.app;

import java.util.LinkedList;

public class Ground {
    private LinkedList<Plane> planes;

    public Ground() {
        planes = new LinkedList<Plane>();
    }

    private void refresh() {


        for (Plane p : planes) {
            if (p.status == Status.LAND) {
                handoff(p);
            }
        }
    }

    /*
    /TODO:
    /   make handoff to next
    */
    public void handoff(Plane p) {


    }

    public void addPlane(Plane p) {
        planes.addLast(p);
    }
}
