package com.app;

import java.awt.*;
import java.util.LinkedList;

public class GPlane extends Plane {
    private final int move = 10;
    public boolean done = false;
    int index = 0;
    int lastTW = 0;
    private LinkedList<int[]> beforeGate = new LinkedList<>();
    private LinkedList<int[]> afterGate = new LinkedList<>();
    private int[] gateCoords;
    boolean past = false;

    public GPlane(Airport airport, int gate) {
        parts = airport.parts;
        this.gate = gate;
        this.gateCoords = airport.gates[6 - gate].target;
        System.out.println(gateCoords[0] + ", " + gateCoords[1]);
        setPartsOrder();
        spawn();
    }


    public void setPartsOrder() {
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
            beforeGate.addLast(parts.get(i).target);
        }
        beforeGate.addLast(parts.get((gate - 1) / 2 + 7).target); //apron
        beforeGate.addLast(gateCoords);

        afterGate.addFirst(parts.get((gate - 1) / 2 + 7).target); //apron

        for (int i = lastTW; i <= 6; i++) {
            afterGate.addLast(parts.get(i).target);
        }


    }

    @Override
    public void spawn() {
        coords = new int[]{680, 0};
        target = parts.get(0).target;
        orientation = 90 + angleOf(new Point(coords[0], coords[1]), new Point(target[0], target[1]));
        System.out.println(orientation);
        System.out.println(target[0] + " " + target[1]);

    }

    @Override
    public void move() {
        if (Math.hypot(coords[0] - target[0], coords[1] - target[1]) <= move) {
            coords[0] = target[0];
            coords[1] = target[1];
            //if (new Scanner(System.in).nextInt() == (id)) //TODO: placeholder for algorithm
            changeTarget();
        } else {
            coords[0] += Math.cos(Math.toRadians(orientation - 90)) * move;
            coords[1] += Math.sin(Math.toRadians(orientation - 90)) * move;
        }


        //System.out.println(Math.hypot(coords[0] - target[0], coords[1] - target[1]));
        //System.out.println(coords[0] + " " + coords[1]);
    }

    private void changeTarget() {
        index++;
        if (!past && index < beforeGate.size()) {
            target = beforeGate.get(index);
            //System.out.println(beforeGate.get(index)[0] + " " + beforeGate.get(index)[0]);
            //System.out.println(index);
        } else if (!past && index == beforeGate.size()) {
            index = -1;
            past = true;
        } else if (index < afterGate.size()) {
            target = afterGate.get(index);
        }
        orientation = 90 + angleOf(new Point(coords[0], coords[1]), new Point(target[0], target[1]));

        System.out.println(this.toString());
    }

    @Override
    public void paint(Graphics2D g2d) {
        move();
        g2d.setColor(Color.green);
        g2d.fillOval(coords[0], coords[1], 5, 5);
    }

    @Override
    public String toString() {
        return "GPlane #" + id + ": {" + coords[0] + ", " + coords[1] +
                "}\n\theading " + orientation +
                "\n\ttowards {" + target[0] + ", " + target[1] +
                "}\n\tgate: " + gate + " (" + gateCoords[0] + ", " + gateCoords[1] + ")";
    }
}
