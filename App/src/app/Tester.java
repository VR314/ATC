package app;

import javax.swing.*;

public class Tester {
    public static void main(String[] args) throws InterruptedException {
        Airport jfk = new Airport();
        Airspace space = new Airspace();

        JFrame frame = setupAirportFrame(jfk);
        //new GPlane(jfk, (int) (Math.random() * 6) + 1, GPlane.Direction.NORTH, space, 1);
        Runnable target;
        Thread t = new Thread(new Scenario(new Time(100), jfk, space));
        t.start();
        JFrame frame2 = setupAirspaceFrame(space);
        /*
        APlane p = new APlane(Math.random() * 360, jfk, space, 2);
        Thread.sleep(1000);
        new APlane(Math.random() * 360, jfk, space, 3);
        Thread.sleep(1000);
        new GPlane(jfk, (int) (Math.random() * 6) + 1, GPlane.Direction.NORTH, space, 4);
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

    static JFrame setupAirspaceFrame(Airspace space) {
        JFrame frame = new JFrame();
        frame.setSize(750, 500);
        frame.add(space);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
