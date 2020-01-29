package com.app;

import javax.swing.*;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) throws InterruptedException {
        Airport jfk = new Airport();
        Airspace space = new Airspace();

        JFrame frame = setupAirportFrame(jfk);
        new GPlane(jfk, (int) (Math.random() * 6) + 1, GPlane.Direction.NORTH, space);

        JFrame frame2 = setupAirspaceFrame(space);
        new APlane(Math.random() * 360, jfk, space);
        Thread.sleep(1000);
        new APlane(Math.random() * 360, jfk, space);
        Thread.sleep(1000);
        new GPlane(jfk, (int) (Math.random() * 6) + 1, GPlane.Direction.NORTH, space);

    }

    static JFrame setupAirportFrame(Airport airport) {
        JFrame frame = new JFrame();
        frame.setSize(750, 700);
        frame.add(airport);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    static JFrame setupAirspaceFrame(Airspace space) {
        JFrame frame = new JFrame();
        frame.setSize(750, 500);
        frame.add(space);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    /* TODO: create JSON for each plane object, instantiate from JSON directly
    static ArrayList<Plane> generateScenario() {
        return null;
    }
    */
}
