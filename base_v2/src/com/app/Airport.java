package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Airport extends JPanel {
    final int TICK = 100; //in ms to refresh
    public LinkedList<GPlane> planes = new LinkedList<GPlane>();
    public LinkedList<Drivable> parts = new LinkedList<Drivable>();

    public Airport() {
        //parts = {runway, un, deux, trois, quatre, cinq, six, a1, a2, a3, g1-6}

        Runway r = new Runway(new Rectangle2D.Double(600, 75, 160, 525), new int[]{680, 578});
        Taxiway un = new Taxiway(r, new Rectangle2D.Double(450, 562.5, 150, 37.5), new int[]{400, 578});
        Taxiway deux = new Taxiway(un, new Rectangle2D.Double(350, 431.25, 100, 168.75), new int[]{400, 506});
        Taxiway trois = new Taxiway(deux, new Rectangle2D.Double(350, 281.25, 100, 150), new int[]{400, 356});
        Taxiway quatre = new Taxiway(trois, new Rectangle2D.Double(350, 150, 100, 131.25), new int[]{400, 206});
        Taxiway cinq = new Taxiway(quatre, new Rectangle2D.Double(350, 75, 100, 75), new int[]{400, 90});
        Taxiway six = new Taxiway(cinq, new Rectangle2D.Double(450, 75, 150, 37.5), new int[]{580, 90});
        parts.add(r);
        parts.add(deux);
        parts.add(un);

        parts.add(trois);
        parts.add(quatre);
        parts.add(cinq);
        parts.add(six);

        Apron a1 = new Apron(new Rectangle2D.Double(275, 187.5, 75, 37.5), new int[]{238, 206});
        Apron a2 = new Apron(new Rectangle2D.Double(275, 337.5, 75, 37.5), new int[]{238, 306});
        Apron a3 = new Apron(new Rectangle2D.Double(275, 487.5, 75, 37.5), new int[]{238, 406});
        parts.add(a1);
        parts.add(a2);
        parts.add(a3);

        Gate g1 = new Gate(new Rectangle2D.Double(225, 131.25, 50, 75));
        Gate g2 = new Gate(new Rectangle2D.Double(225, 206.25, 50, 75));
        Gate g3 = new Gate(new Rectangle2D.Double(225, 280.5, 50, 75));
        Gate g4 = new Gate(new Rectangle2D.Double(225, 355.5, 50, 75));
        Gate g5 = new Gate(new Rectangle2D.Double(225, 430.5, 50, 75));
        Gate g6 = new Gate(new Rectangle2D.Double(225, 506.25, 50, 75));
        parts.add(g1);
        parts.add(g2);
        parts.add(g3);
        parts.add(g4);
        parts.add(g5);
        parts.add(g6);
    }

    @Override
    protected void paintComponent(Graphics g) {
        AffineTransform tform = AffineTransform.getTranslateInstance(-150, 0);

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(tform);

        for (Drivable d : parts)
            d.paint(g2d);

        g2d.setColor(Color.magenta);
        for (Drivable d : parts)
            g2d.draw(d.rect);

        for (GPlane p : planes)
            p.paint(g2d);

        try {
            Thread.sleep(TICK);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
