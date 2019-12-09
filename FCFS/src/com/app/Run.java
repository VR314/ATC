package com.app;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
//WORKS ON SCHOOL PC
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Airport airport = new Airport();
        ArrayList<Plane> planes = new ArrayList<Plane>();
        JFrame f = new JFrame();
        Airspace a = new Airspace();
        a.setPlanes(planes);
        f.setSize(500,500);
        f.add(a);
        f.setVisible(true);
        for(int i = 0; i< 10; i++) {
            a.addPlane(new Plane());
            Thread.sleep((1000));
        }

        //Thread.sleep(10000);
        //a.addPlane(new Plane());
    }


    /**
     *
     * @param out the String to be printed.
     * @return Scanner.nextLine(); the response to the question
     */
    public static String ask(String out){
        Scanner s = new Scanner(System.in);
        System.out.print(out);
        return s.nextLine();
    }
}
