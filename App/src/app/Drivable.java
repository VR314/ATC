package app;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Drivable {
    public boolean full;
    public LinkedList<GPlane> planes;
    protected Rectangle2D rect;
    protected Color color;
    protected boolean fill;
    protected int[] target;
    
    public Drivable(Rectangle2D rect, int[] target) {
        this.rect = rect;
        this.target = target;
        this.planes = new LinkedList<>();
    }
    
    public Drivable(Rectangle2D rect) {
        this.rect = rect;
    }
    
    public final void paint(Graphics2D g2d) {
        g2d.setColor(color);
        if (fill)
            g2d.fill(rect);
        else
            g2d.draw(rect);
        
        g2d.setColor(Color.blue);
        g2d.fillOval(target[0], target[1], 4, 4);
    }
}
