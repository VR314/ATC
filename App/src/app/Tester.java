package app;

import javax.swing.*;
import java.awt.*;

public class Tester {
    public static void main(String[] args){
        Airport jfk = new Airport();
        Airspace space = new Airspace(jfk);
        JFrame frame = setupAirportFrame(jfk);
        JFrame frame2 = setupAirspaceFrame(space);
        JLabel label = new JLabel("", SwingConstants.NORTH_EAST);
        label.setBounds(250, 150, 100, 100);
        label.setForeground(Color.white);
        label.setBackground(Color.black);
        frame2.add(label);
        Thread t = new Thread(new Scenario(new Time(300), jfk, space));
        t.start();
    }
    
    public static JFrame setupAirportFrame(Airport airport) {
        JFrame frame = new JFrame();
        frame.setSize(750, 700);
        frame.add(airport);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
    
    public static JFrame setupAirspaceFrame(Airspace space) {
        JFrame frame = new JFrame();
        frame.setSize(750, 500);
        frame.add(space);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}
