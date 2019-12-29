package com.app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame airport = new JFrame();
        airport.setSize(750, 750);
        Airport jfk = new Airport();
        airport.add(jfk);
        airport.setVisible(true);
        airport.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GPlane p1 = new GPlane(jfk);
        p1.gate = 6;
        p1.setPartsOrder();
    }
}
