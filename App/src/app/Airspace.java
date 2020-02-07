package app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Airspace extends JPanel {
    /**
     * The list of planes in the Airspace's scope
     */
    public LinkedList<APlane> planes = new LinkedList<APlane>();
    
    /**
     * Constructor requires no arguments - empty constructor
     */
    public Airspace() {
    }
    
    /**
     * <p>
     * Overridden method from the JPanel class that draws the Airspace and any planes in the scope of the Airspace, refreshing every 100 ms.
     * </p>
     *
     * @param g Default graphics object, part of the parent JPanel class
     *
     * @throws ConcurrentModificationException Concurrent deletion in and iteration of the planes list
     */
    @Override
    protected void paintComponent(Graphics g) throws ConcurrentModificationException {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform tform = AffineTransform.getTranslateInstance(375, 250);
        g2d.setTransform(tform);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, 5, 0, -5);
        g2d.setStroke(new BasicStroke(1));
        for (APlane plane : planes) {
            if (Math.abs(plane.coords[0]) < 5 && Math.abs(plane.coords[1]) - 35 < 5) {
                plane.paint(g2d);
                plane.toGPlane();
            } else
                plane.paint(g2d);
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }
}
