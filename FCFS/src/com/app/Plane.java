package com.app;

public class Plane {
    public int id;
    public Airport airport;
    public Status status;
    public double orientation;
    public double speed; // 1 knot = 1 nm / h, 150 knots = 40 minutes
    private int[] target;

    public void changeStatus(Status s) {
        this.status = s;
        if (s == Status.LAND) {
            this.target = airport.parts.get(0).target;
        }
    }
}
