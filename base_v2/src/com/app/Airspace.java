package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public class Airspace extends JPanel {

    public LinkedList<APlane> planes = new LinkedList<>();

    public Airspace() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform tform = AffineTransform.getTranslateInstance(375, 250);
        g2d.setTransform(tform);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(0, 0, 10, 10);
        g2d.setStroke(new BasicStroke(1));
        for (int i = planes.size(); i > 0; i--) {
            planes.get(i - 1).paint(g2d);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }
}
