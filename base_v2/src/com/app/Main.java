package com.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Airport jfk = new Airport();
        JFrame frame = setupAirportFrame(jfk);

        GPlane p1 = new GPlane(jfk, (int)(Math.random() * 6) + 1);
        jfk.planes.add(p1);
    }

    static JFrame setupAirportFrame(Airport airport){
        JFrame frame = new JFrame();
        frame.setSize(750, 700);
        frame.add(airport);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
