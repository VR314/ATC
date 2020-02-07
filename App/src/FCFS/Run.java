package FCFS;

import app.*;

import javax.swing.*;

public class Run {
    public static void main(String[] args) {
        //spawn at set time
        Airport jfk = new FCFSAirport();
        Airspace space = new FCFSAirspace(jfk);
        JFrame frame = Tester.setupAirportFrame(jfk);
        JFrame frame2 = Tester.setupAirspaceFrame(space);
        Thread t = new Thread(new Scenario(new Time(300), jfk, space));
        t.start();
    }
}
