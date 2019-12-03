package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Airspace extends JPanel {
    private ArrayList<Plane> planes;

    public Airspace(){
        planes = new ArrayList<Plane>();
    }

    public void setPlanes(ArrayList<Plane> planes){
        this.planes = planes;
    }

    public void addPlane(Plane p){
        this.planes.add(p);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform tform = AffineTransform.getTranslateInstance( 250, 250);
        g2d.setTransform(tform);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(0,0,10,10);
        g2d.setStroke(new BasicStroke(1));
        for(int i = planes.size(); i > 0; i--){
            try {
                planes.get(i - 1).paint(g2d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        repaint();
    }
}
