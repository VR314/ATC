package com.app;

import java.awt.*;
import java.util.LinkedList;

public class GPlane extends Plane {
    private LinkedList<Drivable> beforeGate = new LinkedList<>();
    private LinkedList<Drivable> afterGate = new LinkedList<>();

    public GPlane(Airport airport) {
        parts = airport.parts;
    }


    public void setPartsOrder() {
        int lastTW = 0;
        switch (gate) {
            case 1:
            case 2:
                lastTW = 2;
                break;
            case 3:
            case 4:
                lastTW = 3;
                break;
            case 5:
            case 6:
                lastTW = 4;
                break;
            default:
                break;
        }


        for (int i = 0; i <= lastTW; i++) {
            beforeGate.addFirst(parts.peekFirst());
        }

        beforeGate.addLast(parts.get(5 + lastTW));
        afterGate.addFirst(parts.get(5 + lastTW));

        for (int i = lastTW; i >= 6; i++) {
            afterGate.addFirst(parts.peekFirst());
        }

        System.out.println(beforeGate.size());

    }

    @Override
    public void spawn(Airport airport) {

    }

    @Override
    public void move() {

    }

    @Override
    public void paint(Graphics2D g2d) {

    }
}
