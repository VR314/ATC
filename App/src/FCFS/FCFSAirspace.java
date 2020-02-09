package FCFS;

import app.APlane;
import app.Airport;
import app.Airspace;
import app.GPlane;

import java.awt.*;
import java.util.ConcurrentModificationException;

public class FCFSAirspace extends Airspace {
    int crashes = 0;
    int nearMisses = 0;
    
    public FCFSAirspace(Airport a) {
        super(a);
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
    
        if (airport.r.full) {
            for (APlane p : planes) {
                if (Math.abs(p.coords[1]) < 50) {
                    p.goAround = true;
                }
            }
        }
    
        for (int i = 0; i < airport.planes.size(); i++) {
            for (int j = 0; j < airport.planes.size(); j++) {
                if (i != j) {
                    GPlane p = airport.planes.get(i);
                    GPlane p2 = airport.planes.get(j);
                    if (Math.hypot(Math.abs(p.coords[0] - p2.coords[0]), Math.abs(p.coords[1] - p2.coords[1])) < 5) {
                        crashes++;
                        System.out.println("CRASH: " + p.id + " + " + p2.id);
                    } else if (Math.hypot(Math.abs(p.coords[0] - p2.coords[0]), Math.abs(p.coords[1] - p2.coords[1])) < 10) {
                        nearMisses++;
                        System.out.println("NEARMISS " + p.id + " + " + p2.id);
                    }
                }
            }
        }
    
    
        if (this.planes.isEmpty() && this.airport.planes.isEmpty()) {
            System.out.println(crashes + " crashes");
            System.out.println(nearMisses + " nearMisses");
        }
    }
}
