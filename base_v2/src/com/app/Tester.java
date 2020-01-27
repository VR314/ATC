package com.app;

import javax.swing.*;
import java.util.ArrayList;

public class Tester {
    public static void main(String[] args) {
        Airport jfk = new Airport();
        Airspace space = new Airspace();

        JFrame frame = setupAirportFrame(jfk);
        //new GPlane(jfk, (int)  3, GPlane.Direction.NORTH, space); //(Math.random() * 6) +

        JFrame frame2 = setupAirspaceFrame(space);
        new APlane(120, jfk, space);
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
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }

    static ArrayList<Plane> generateScenario() {

        return null;
    }

}
