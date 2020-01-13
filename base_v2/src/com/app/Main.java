package com.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Airport jfk = new Airport();
        JFrame frame = setupAirportFrame(jfk);
        GPlane p1 = new GPlane(jfk, (int) (Math.random() * 6) + 1);
        jfk.planes.add(p1);

        Airspace space = new Airspace();
        JFrame frame2 = setupAirspaceFrame(space);
        space.planes.add(new APlane(120, jfk));
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

}
