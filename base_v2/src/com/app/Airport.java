package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Airport extends JPanel {
    final int TICK = 100; //in ms to refresh
    public LinkedList<GPlane> planes = new LinkedList<>(); //TODO: IMPLEMENT QUEUE
    public Rectangle2D[] parts = new Rectangle2D[3];
    public Apron[] aprons;
    public Gate[] gates = new Gate[]{new Gate(new Rectangle2D.Double(225, 131.25, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 206.25, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 280.5, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 355.5, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 430.5, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 506.25, 50, 75))};

    {
        Rectangle2D deux = new Rectangle2D.Double(450, 562.5, 150, 37.5);
        Rectangle2D main = new Rectangle2D.Double(350, 75, 100, 525);
        Rectangle2D un = new Rectangle2D.Double(450, 75, 150, 37.5);
        parts = new Rectangle2D[]{main, un, deux};
    }

    public Runway r = new Runway(new Rectangle2D.Double(600, 75, 160, 525), new int[]{680, 578});

    {
        Apron a3 = new Apron(new Rectangle2D.Double(275, 187.5, 75, 37.5), new int[]{275, 206});
        Apron a2 = new Apron(new Rectangle2D.Double(275, 337.5, 75, 37.5), new int[]{275, 356});
        Apron a1 = new Apron(new Rectangle2D.Double(275, 487.5, 75, 37.5), new int[]{275, 506});
        aprons = new Apron[]{a1, a2, a3};
    }


    public Airport() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(AffineTransform.getTranslateInstance(-150, 0));

        g2d.setColor(Color.gray);
        for (Rectangle2D d : parts)
            g2d.fill(d);

        g2d.setColor(Color.magenta);
        for (Rectangle2D d : parts)
            g2d.draw(d);

        g2d.setColor(Color.gray);
        g2d.fill(parts[1]);
        g2d.setColor(Color.magenta);
        g2d.draw(parts[1]);

        for (Gate ga : gates)
            ga.paint(g2d);

        for (Apron a : aprons)
            a.paint(g2d);

        g2d.setColor(Color.black);
        g2d.fill(r.rect);

        for (GPlane p : planes) {
            //System.out.println(p.toString());
            p.paint(g2d);
        }
        try {
            Thread.sleep(TICK);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
