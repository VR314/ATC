package com.app;
import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<Drivable> drive = setAirport();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        JFrame f = new JFrame();
        Panel p = new Panel();
        p.setPlanes(planes);
        f.setSize(500,500);
        f.add(p);
        f.setVisible(true);
        for(int i = 0; i< 10; i++) {
            p.addPlane(new Plane());
            Thread.sleep((1000));
        }

        //Thread.sleep(10000);
        //p.addPlane(new Plane());
    }

    //TODO
    public static LinkedList<Drivable> setAirport(){
        LinkedList<Drivable> drive = new LinkedList<Drivable>();

        return drive;
    }

    /**
     *
     * @param out
     * @return Scanner.nextLine();
     */
    public static String ask(String out){
        Scanner s = new Scanner(System.in);
        System.out.print(out);
        return s.nextLine();
    }
}
