package app;

import javax.swing.*;
import java.awt.*;

public class Tester {
    public static void main(String[] args) throws InterruptedException {
        Airport jfk = new Airport();
        Airspace space = new Airspace();

        JFrame frame = setupAirportFrame(jfk);
        JLabel label = new JLabel("", SwingConstants.NORTH_EAST);
        JFrame frame2 = setupAirspaceFrame(space, label);
        Thread t = new Thread(new Scenario(new Time(200), jfk, space, label));
        APlane p = new APlane(Math.random() * 360, jfk, space, 2);
        t.start();
        /*

        Thread.sleep(1000);
        new APlane(Math.random() * 360, jfk, space, 3);
        Thread.sleep(1000);
        new GPlane(jfk, (int) (Math.random() * 6) + 1, GPlane.Direction.NORTH, space, 4);
        new GPlane(jfk, (int) (Math.random() * 6) + 1, GPlane.Direction.NORTH, space, 1);
        */
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

    static JFrame setupAirspaceFrame(Airspace space, JLabel label) {
        JFrame frame = new JFrame();
        frame.setSize(750, 500);
        frame.add(space);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        label.setBounds(650, 400, 100, 100);
        label.setForeground(Color.white);
        label.setBackground(Color.black);
        frame.add(label);
        return frame;
    }
}
