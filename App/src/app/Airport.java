package app;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Airport extends JPanel {
    protected int crashes, nearMisses;
    
    /**
     * The time in ms to refresh the Airport frame
     */
    final int TICK = 75; //in ms to refresh
    
    /**
     * The list of planes in the Airport's scope
     */
    public ArrayList<GPlane> planes = new ArrayList<>();
    
    /**
     * The Runway of the Airport
     */
    public Runway r = new Runway(new Rectangle2D.Double(600, 75, 160, 525), new int[]{680, 578});
    
    /**
     * The array of the Taxiway and the on/off ramps of the Runway to be printed
     */
    public Rectangle2D[] parts = {
            new Rectangle2D.Double(350, 75, 100, 525),
            new Rectangle2D.Double(450, 75, 150, 37.5),
            new Rectangle2D.Double(450, 562.5, 150, 37.5)};
    
    /**
     * The array of possible Aprons to be used in the GPlane class and to be drawn
     */
    public Apron[] aprons = {
            new Apron(new Rectangle2D.Double(275, 487.5, 75, 37.5), new int[]{275, 506}),
            new Apron(new Rectangle2D.Double(275, 337.5, 75, 37.5), new int[]{275, 356}),
            new Apron(new Rectangle2D.Double(275, 187.5, 75, 37.5), new int[]{275, 206})};
    
    /**
     * The array of the 6 gates to be used in the GPlane class and to be drawn
     */
    public Gate[] gates = new Gate[]{
            new Gate(new Rectangle2D.Double(225, 131.25, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 206.25, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 280.5, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 355.5, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 430.5, 50, 75)),
            new Gate(new Rectangle2D.Double(225, 506.25, 50, 75))};
    
    /**
     * Constructor requires no arguments - empty constructor
     */
    public Airport() {
    }
    
    /**
     * <p>
     * Overridden method from the JPanel class that draws the Airport and any planes in the scope of the Airport, refreshing every {@link Airport#TICK} ms.
     * </p>
     *
     * @param g Default graphics object, part of the parent JPanel class
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        //check time @ gate, wait for pushback time
        for (GPlane p : planes) {
            if (p.wait) {
                if (p.pTimes[2] <= p.time.getMins()) {
                    p.wait = false;
                }
            }
        }
    
        //check crashes
        for (int i = 0; i < planes.size(); i++) {
            for (int j = i + 1; j < planes.size(); j++) {
                GPlane p = planes.get(i);
                GPlane p2 = planes.get(j);
                if ((p.coords[0] == p2.coords[0] && Math.abs(p.coords[1] - p2.coords[1]) < 5) || (p.coords[1] == p2.coords[1] && Math.abs(p.coords[0] - p2.coords[0]) < 5)) { //TODO: fix crashes & near-misses
                    crashes++;
                    System.out.println("CRASH: " + p.id + " + " + p2.id);
                } else if (Math.pow(p.coords[0] - p2.coords[0], 2) + Math.pow(p.coords[1] - p2.coords[1], 2) < 40) {
                    nearMisses++;
                    System.out.println("NEARMISS " + p.id + " + " + p2.id);
                
                }
            }
        }
    
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
    
        for (int i = 0; i < planes.size(); i++) {
            //System.out.println(p.toString());
            planes.get(i).paint(g2d);
        }
        try {
            Thread.sleep(TICK);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
