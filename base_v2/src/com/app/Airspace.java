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
            if (Math.abs(planes.get(i - 1).x) < 3 && Math.abs(planes.get(i - 1).y) - 35 < 3)
                handoff(planes.get(i - 1));
            else
                planes.get(i - 1).paint(g2d);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    private void handoff(APlane aPlane) {
        APlane temp = planes.remove(planes.indexOf(aPlane));
        Plane p = temp;
        temp.airport.planes.add(new GPlane(p));
    }
}
