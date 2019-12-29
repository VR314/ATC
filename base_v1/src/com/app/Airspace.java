package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Airspace extends JPanel {
    private ArrayList<APlane> APlanes;

    public Airspace() {
        APlanes = new ArrayList<APlane>();
    }

    /**
     * setter for planes List
     */
    public void setAPlanes(ArrayList<APlane> APlanes) {
        this.APlanes = APlanes;
    }

    /**
     * adds a plane to the planes List of the airspace
     *
     * @param p the plane to be added
     */
    public void addPlane(APlane p) {
        this.APlanes.add(p);
    }

    /**
     * overrided method from the JPanel class
     * draws each plane in the airspace
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform tform = AffineTransform.getTranslateInstance(250, 250);
        g2d.setTransform(tform);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(0, 0, 10, 10);
        g2d.setStroke(new BasicStroke(1));
        for (int i = APlanes.size(); i > 0; i--) {
            try {
                APlanes.get(i - 1).paint(g2d);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        repaint();
    }
}
