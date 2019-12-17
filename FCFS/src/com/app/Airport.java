package com.app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Airport extends JPanel {
    ArrayList<Plane> planes;
    ArrayList<Drivable> parts;

    public Airport() {
        parts = new ArrayList<Drivable>();
        Runway r = new Runway();
        parts.add(r);
        Taxiway un = new Taxiway(new Rectangle2D.Double(225, 375, 75, 25), new int[]{290, 385}, new int[]{230,385});
        parts.add(un);
        Taxiway deux = new Taxiway(new Rectangle2D.Double(225, 50, 75, 25), new int[] {230, 60}, new int[]{290, 60});
        parts.add(deux);
        Taxiway trois = new Taxiway(new Rectangle2D.Double(175, 50, 50, 137.5), new int[] {200, 135}, new int[]{200, 60});
        parts.add(trois);
        Taxiway quatre = new Taxiway(new Rectangle2D.Double(175, 187.5, 50, 100), new int[] {200, 265}, new int[]{200, 180});
        parts.add(quatre);
        Taxiway cinq = new Taxiway(new Rectangle2D.Double(175, 287.5, 50, 100), new int[] {200, 395}, new int[]{200, 310});
        parts.add(cinq);
        planes = new ArrayList<Plane>();
    }

    //TODO:
    //  -implement Graphics, way for planes to move

    /**
     * setter for <code>parts</code>
     */
    public void setParts(ArrayList<Drivable> d) {
        parts = d;
    }

    public void addPlane(Plane p) {
        planes.add(p);
    }

    @Override
    //HARD CODED AIRPORT
    //TODO: MAKE COORDS FROM <DRIVABLE>S
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform tform = AffineTransform.getTranslateInstance(0, 0);
        g2d.setTransform(tform);

        //RUNWAY
        //g2d.fillRect(300,50,75,350);
        parts.get(0).paint(g2d);

        //TAXIWAYS // OFF/ON RAMPS OF RUNWAY
        //g2d.setColor(Color.gray);
        //g2d.fillRect(225, 50, 75, 25);
        parts.get(1).paint(g2d);
        parts.get(2).paint(g2d);
        parts.get(3).paint(g2d);
        parts.get(4).paint(g2d);
        parts.get(5).paint(g2d);
        g2d.setColor(Color.gray);

        //TAXIWAY
        //g2d.fillRect(175, 50, 50, 350);

        //APRON
        g2d.fillRect(100, 125, 75, 25);
        g2d.fillRect(100, 225, 75, 25);
        g2d.fillRect(100, 325, 75, 25);

        g2d.setColor(Color.black);
        g2d.draw(new Rectangle2D.Double(50, 87.5, 50, 50));
        g2d.draw(new Rectangle2D.Double(50, 137.5, 50, 50));
        g2d.draw(new Rectangle2D.Double(50, 187.5, 50, 50));
        g2d.draw(new Rectangle2D.Double(50, 237.5, 50, 50));
        g2d.draw(new Rectangle2D.Double(50, 287.5, 50, 50));
        g2d.draw(new Rectangle2D.Double(50, 337.5, 50, 50));

        repaint();
    }

}

class AirportThread extends Thread {


}
