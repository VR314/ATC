package app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Airspace extends JPanel {
    /**
     * The list of planes in the Airspace's scope
     */
    public ArrayList<APlane> planes = new ArrayList<>();
    
    public Airport airport;
    
    /**
     * Constructor requires no arguments - empty constructor
     */
    public Airspace(Airport airport) {
        this.airport = airport;
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
        for (int i = 0; i < planes.size(); i++) {
                planes.get(i).paint(g2d);
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }
}
