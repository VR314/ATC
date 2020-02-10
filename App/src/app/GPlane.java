package app;

import java.awt.*;

public class GPlane extends Plane {
    public boolean wait = false;
    int index = 0;
    private int speed;
    private int[] gateCoords;
    private double[][] targets = new double[5][2];
    private Apron a;
    private boolean pastGate = false;
    private boolean takingoff = false;
    private Direction takeoff; //towards
    private Direction land; //from
    
    public GPlane(Airport airport, int gate, Direction d, Airspace airspace, int id, int[] times) { //used by Scenario
        takeoff = d;
        this.airport = airport;
        this.airspace = airspace;
        this.gate = gate;
        this.gateCoords = airport.gates[6 - gate].target;
        pastGate = true;
        this.pTimes = times;
        this.actualTimes = new int[pTimes.length];
        toTOParts();
        coords = new double[]{(double) gateCoords[0], (double) gateCoords[1]};
        this.id = id;
    }
    
    public GPlane(Plane p, Direction d) { //used by toGPlane()
        this.land = d;
        this.time = p.time;
        this.id = p.id;
        this.airport = p.airport;
        this.airspace = p.airspace;
        this.gate = p.gate;
        this.gateCoords = airport.gates[6 - gate].target;
        toGateParts();
        this.pTimes = p.pTimes;
        this.actualTimes = new int[pTimes.length];
        this.actualTimes[0] = (int) time.getMins();
        this.speed = p.speed;
        if (d == Direction.NORTH)
            coords = new double[]{680, -100};
        else
            coords = new double[]{680, 700};
        spawn();
        airport.r.planes.add(this);
        System.out.println(this.toString());
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
        targets[4] = new double[]{airport.r.rect.getCenterX() - 100, targets[3][1]};
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
        speed = 150;
        orientation = 90 + angleOf(coords[0], coords[1], target[0], target[1]);
        this.airport.planes.add(this);
    }
    
    @Override
    public void move() {
        if (takingoff) { //TODO: implement waiting/stopping if too close to another plane (ex. waiting for takeoff)
            if (Math.abs(coords[0] - airport.r.rect.getCenterX()) > 5) {
                target[0] = airport.r.rect.getCenterX();
                target[1] = coords[1];
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 10;
            } else {
                coords[0] = airport.r.rect.getCenterX();
                actualTimes[3] = (int) time.getMins();
                if (takeoff == Direction.NORTH) {
                    orientation = 360;
                    target[1] = 0;
                } else {
                    orientation = 180;
                    target[1] = -800;
                }
                if (speed < 250)
                    speed *= 1.1;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 10;
                if (coords[1] < 20 || coords[1] > 700) {
                    if (!airport.r.planes.contains(this))
                        this.airport.r.planes.remove(this);
                    toAPlane();
                }
            }
        } else if (!wait) {
            if (actualTimes[2] == 0) {
                actualTimes[2] = (int) time.getMins();
            }
            if (Math.hypot(coords[0] - target[0], coords[1] - target[1]) <= speed / 10 * 2) {
                coords[0] = target[0];
                coords[1] = target[1];
                changeTarget();
            } else {
                if (speed > 50)
                    speed *= 0.998;
                coords[0] += Math.cos(Math.toRadians(orientation - 90)) * speed / 10;
                coords[1] += Math.sin(Math.toRadians(orientation - 90)) * speed / 10;
                if (coords[0] != airport.r.rect.getCenterX() && !pastGate) {
                    if (airport.r.planes.contains(this))
                        this.airport.r.planes.remove(this);
                }
            }
        }
    }
    
    private void changeTarget() {
        if (index < 4) {
            if (!airport.r.planes.contains(this))
                this.airport.r.planes.remove(this);
            target = targets[++index];
            orientation = 90 + angleOf(coords[0], coords[1], target[0], target[1]);
        } else if (index == 4 && !pastGate) {
            actualTimes[1] = (int) time.getMins();
            toTOParts();
            index = 0;
            pastGate = true;
            orientation = 90 + angleOf(coords[0], coords[1], target[0], target[1]);
            wait = true; //only changed by algorithm
        } else {
            if (!airport.r.planes.contains(this) && airport.r.planes.isEmpty()) {
                this.airport.r.planes.add(this);
                takingoff = true;
            }
        }
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
        airport.r.planes.remove(this);
        if (this.takeoff == Direction.NORTH)
            new APlane(this, APlane.GATE.NORTH);
        else
            new APlane(this, APlane.GATE.SOUTH);
    }
    
    enum Direction {
        NORTH, //from north LAND, TOWARDS NORTH T/O
        SOUTH
    }
}
