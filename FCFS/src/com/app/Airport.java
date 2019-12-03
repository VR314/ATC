package com.app;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Airport extends JPanel {

    ArrayList<Drivable> parts;

    public Airport(){
        parts = new ArrayList<Drivable>();
    }

    //TODO:
    //  -implement Graphics, way for planes to move


    public void setParts(ArrayList<Drivable> d){
        parts = d;
    }

    @Override
    public void paintComponent(Graphics g) {


        repaint();
    }

}

class AirportThread extends Thread {


}
