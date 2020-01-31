package app;

import java.awt.*;

public class GPlane extends Plane {
    public boolean wait = false;
    private final int move = 20;
    int index = 0;
    private int[] gateCoords;
    private double[][] targets = new double[5][2];
    private Apron a;
    private boolean pastGate = false;
    private boolean takingoff = false;
    Direction takeoff; //towards
    Direction land; //from

    public GPlane(Plane p, Direction d, int id) {
        land = d;
        this.id = id;
        this.airport = p.airport;
        this.airspace = p.airspace;
        this.gate = p.gate;
        this.gateCoords = airport.gates[6 - gate].target;
        toGateParts();
        if (d == Direction.NORTH)
            coords = new double[]{680, -100};
        else
            coords = new double[]{680, 700};
        spawn();
        System.out.println(this.toString());
    }

    public GPlane(Airport airport, int gate, Direction d, Airspace airspace, int id) { //TODO: spawn plane @ gate
        takeoff = d;
        this.airport = airport;
        this.airspace = airspace;
        this.gate = gate;
        this.gateCoords = airport.gates[6 - gate].target;
        pastGate = true;
        toTOParts();
        coords = new double[]{(double) gateCoords[0], (double) gateCoords[1]};
        this.id = id;
    }

    enum Direction {
        NORTH, //from north LAND, TOWARDS NORTH T/O
        SOUTH
    }

    public void toGateParts() {
        setApron();
        if (land == Direction.NORTH) {
            targets[0] = new double[]{airport.r.rect.getCenterX(), airport.parts[2].getCenterY()};
        } else {
            targets[0] = new double[]{airport.r.rect.getCenterX(), airport.parts[1].getCenterY()};
        }
        targets[1] = new double[]{airport.parts[0].getCenterX(), targets[0][1]};
        targets[2] = new double[]{targets[1][0], a.rect.getCenterY()};
        targets[3] = new double[]{a.rect.getX(), targets[2][1]};
        targets[4] = new double[]{gateCoords[0], gateCoords[1]};
    }

    public void toTOParts() {
        if (takeoff == null) {
            takeoff = Direction.SOUTH;
        }
        if (a == null) {
            setApron();
        }
        targets[0] = new double[]{gateCoords[0], gateCoords[1]};
        targets[1] = new double[]{a.rect.getX(), a.rect.getCenterY()};
        targets[2] = new double[]{airport.parts[0].getCenterX(), targets[1][1]};
        if (takeoff == Direction.NORTH) {
            targets[3] = new double[]{targets[2][0], airport.parts[2].getCenterY()};
        } else {
            targets[3] = new double[]{targets[2][0], airport.parts[1].getCenterY()};
        }
        targets[4] = new double[]{airport.r.rect.getCenterX(), targets[3][1]};
    }

    private void setApron() {
        switch (gate) {
            case 1:
            case 2:
                a = airport.aprons[0];
                break;
            case 3:
            case 4:
                a = airport.aprons[1];
                break;
            case 5:
            case 6:
                a = airport.aprons[2];
                break;
            default:
                break;
        }
    }

    @Override
    public void spawn() { //call from algorithm
        target = targets[0];
        orientation = 90 + angleOf(coords[0], coords[1], target[0], target[1]);
        this.airport.planes.add(this);
    }

    @Override
    public void move() {
        if (takingoff) {
            coords[0] += Math.cos(Math.toRadians(orientation - 90)) * move;
            coords[1] += Math.sin(Math.toRadians(orientation - 90)) * move;
            if (coords[1] < 20 || coords[1] > 700) {
                toAPlane();
            }
        } else if (!wait) {
            if (Math.hypot(coords[0] - target[0], coords[1] - target[1]) <= move * 2) {
                coords[0] = target[0];
                coords[1] = target[1];
                changeTarget();

            } else {
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * move;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * move;
            }
        }
    }

    private void changeTarget() {
        if (index < 4) {
            target = targets[++index];
            orientation = 90 + angleOf(coords[0], coords[1], target[0], target[1]);
        } else if (index == 4 && !pastGate) {
            toTOParts();
            index = 0;
            pastGate = true;
            orientation = 90 + angleOf(coords[0], coords[1], target[0], target[1]);
            //AT GATE
        } else {
            takingoff = true;
            if (takeoff == Direction.NORTH) {
                target[1] = 0;
                orientation = 360;
            } else {
                target[1] = -800;
                orientation = 180;
            }
        }
        System.out.println(this.toString());
    }

    @Override
    public void paint(Graphics2D g2d) {
        move();
        g2d.setColor(Color.green);
        g2d.fillOval((int) coords[0], (int) coords[1], 5, 5);
    }

    @Override
    public String toString() {
        return "GPlane #" + id + ": {" + coords[0] + ", " + coords[1] +
                "}\n\theading " + orientation +
                "\n\ttowards {" + target[0] + ", " + target[1] +
                "}\n\tgate: " + gate + " (" + gateCoords[0] + ", " + gateCoords[1] + ")" +
                "\n\t index: " + index;
    }

    public void toAPlane() {
        airport.planes.remove(this);
        if (this.takeoff == Direction.NORTH)
            new APlane(this, APlane.GATE.NORTH, this.id);
        else
            new APlane(this, APlane.GATE.SOUTH, this.id);
    }
}
