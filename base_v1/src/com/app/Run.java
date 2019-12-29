package com.app;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        Airport airport = new Airport();
        ArrayList<APlane> planes = new ArrayList<APlane>();

/*
        JFrame f = new JFrame();
        Airspace a = new Airspace();
        a.setAPlanes(planes);
        f.setSize(500,500);
        f.add(a);

        f.setVisible(true);
        for(int i = 0; i< 10; i++) {
            a.addPlane(new APlane());
            Thread.sleep((1000));
        }
*/

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.add(airport);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Thread.sleep(10000);
        GPlane p = new GPlane(airport, 6);
        airport.addGPlane(p);
        Thread.sleep(1000);
        GPlane p2 = new GPlane(airport, 2);
        airport.addGPlane(p2);

    }


    /**
     * @param out the String to be printed.
     * @return <code>Scanner.nextLine();</code> the response to the question
     */
    public static String ask(String out) {
        Scanner s = new Scanner(System.in);
        System.out.print(out);
        return s.nextLine();
    }
}
