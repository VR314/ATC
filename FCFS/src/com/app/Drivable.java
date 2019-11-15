package com.app;

import java.util.LinkedList;

public class Drivable {
    int maxPlanes;
    LinkedList<Plane> planes;
    boolean full;

    public void addPlane(Plane p) {
        planes.addLast(p);

    }

    public void handoff(Drivable next, Plane p) {
        next.addPlane(p);
        planes.remove(p);
    }
}
