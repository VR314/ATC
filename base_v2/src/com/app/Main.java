package com.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setSize(750, 750);
        Airport jfk = new Airport();
        frame.add(jfk);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GPlane p1 = new GPlane(jfk, 1);
        jfk.planes.add(p1);
    }
}
