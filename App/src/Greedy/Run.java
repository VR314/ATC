package Greedy;

import app.*;

import javax.swing.*;

public class Run {
    public static void main(String[] args) {
        //spawn at set time
        Airport jfk = new GreedyAirport();
        Airspace space = new GreedyAirspace(jfk);
        JFrame frame = Tester.setupAirportFrame(jfk);
        JFrame frame2 = Tester.setupAirspaceFrame(space);
        Thread t = new Thread(new Scenario(new Time(300), jfk, space));
        t.start();
    }
}
