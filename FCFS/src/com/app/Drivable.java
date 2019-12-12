package com.app;

import java.util.LinkedList;

public class Drivable {
    public int maxPlanes; //inherits
    public LinkedList<Plane> planes; //inherits
    public boolean full; //inherits
    public int[] target; //inherits

    public void addPlane(Plane p) {
        planes.addLast(p);
    }

    public void handoff(Drivable next, Plane p) {
        next.addPlane(p);
        planes.remove(p);
    }
}
