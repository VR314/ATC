package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Scanner;

public class Airport extends JPanel {
    final int TICK = 100; //in ms to refresh
    public LinkedList<GPlane> planes = new LinkedList<>();
    public LinkedList<Drivable> parts = new LinkedList<>();
    public Gate[] gates;

    public Airport() {
        //parts = {runway, un, deux, trois, quatre, cinq, six, a1, a2, a3}
        Scanner s = new Scanner(System.in);
        Runway r = new Runway(new Rectangle2D.Double(600, 75, 160, 525), new int[]{680, 578});
        Taxiway un = new Taxiway(r, new Rectangle2D.Double(450, 562.5, 150, 37.5), new int[]{400, 578});
        Taxiway deux = new Taxiway(un, new Rectangle2D.Double(350, 431.25, 100, 168.75), new int[]{400, 506});
        Taxiway trois = new Taxiway(deux, new Rectangle2D.Double(350, 281.25, 100, 150), new int[]{400, 356});
        Taxiway quatre = new Taxiway(trois, new Rectangle2D.Double(350, 150, 100, 131.25), new int[]{400, 206});
        Taxiway cinq = new Taxiway(quatre, new Rectangle2D.Double(350, 75, 100, 75), new int[]{400, 90});
        Taxiway six = new Taxiway(cinq, new Rectangle2D.Double(450, 75, 150, 37.5), new int[]{580, 90});

        parts.add(r);
        parts.add(un);
        parts.add(deux);
        parts.add(trois);
        parts.add(quatre);
        parts.add(cinq);
        parts.add(six);

        Apron a3 = new Apron(new Rectangle2D.Double(275, 187.5, 75, 37.5), new int[]{275, 206});
        Apron a2 = new Apron(new Rectangle2D.Double(275, 337.5, 75, 37.5), new int[]{275, 356});
        Apron a1 = new Apron(new Rectangle2D.Double(275, 487.5, 75, 37.5), new int[]{275, 506});
        parts.add(a1);
        parts.add(a2);
        parts.add(a3);

        gates = new Gate[]{new Gate(new Rectangle2D.Double(225, 131.25, 50, 75)),
                new Gate(new Rectangle2D.Double(225, 206.25, 50, 75)),
                new Gate(new Rectangle2D.Double(225, 280.5, 50, 75)),
                new Gate(new Rectangle2D.Double(225, 355.5, 50, 75)),
                new Gate(new Rectangle2D.Double(225, 430.5, 50, 75)),
                new Gate(new Rectangle2D.Double(225, 506.25, 50, 75))};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(AffineTransform.getTranslateInstance(-150, 0));

        for (Drivable d : parts)
            d.paint(g2d);

        g2d.setColor(Color.magenta);
        for (Drivable d : parts)
            g2d.draw(d.rect);
        parts.get(1).paint(g2d);
        g2d.setColor(Color.magenta);
        g2d.draw(parts.get(1).rect);

        for (Gate ga : gates)
            ga.paint(g2d);

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
