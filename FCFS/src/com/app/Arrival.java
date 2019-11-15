package com.app;

import java.util.LinkedList;

public class Arrival {
    private LinkedList<Plane> planes; //newest last = remove first when processing

    public Arrival() {
        planes = new LinkedList<Plane>();
    }

    private void refresh(Ground g) {
        Plane temp = planes.pollFirst();
        if (temp != null) {

        }


        for (Plane p : planes) {
            if (p.status == Status.LAND) {
                handoff(p, g);
            }
        }
    }

    public void handoff(Plane p, Ground g) {
        Plane temp = planes.get(planes.indexOf(p));
        planes.remove(p);
        g.addPlane(temp);
    }

    public void addPlane(Plane p) {
        planes.addLast(p);
    }
}