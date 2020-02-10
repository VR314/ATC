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
    
        if (airport.r.planes.size() > 0) {
            for (APlane p : planes) {
                if (Math.abs(p.coords[1]) < 50) {
                    p.goAround = true;
                }
            }
        }
    
        for (int i = 0; i < airport.planes.size(); i++) {
            for (int j = i + 1; j < airport.planes.size(); j++) {
                if (i != j) {
                    GPlane p = airport.planes.get(i);
                    GPlane p2 = airport.planes.get(j);
                    if (Math.pow(p.coords[0] - p2.coords[0], 2) + Math.pow(p.coords[1] - p2.coords[1], 2) < 12) {
                        crashes++;
                        System.out.println("CRASH: " + p.id + " + " + p2.id);
                    } else if (Math.pow(p.coords[0] - p2.coords[0], 2) + Math.pow(p.coords[1] - p2.coords[1], 2) < 40) {
                        nearMisses++;
                        System.out.println("NEARMISS " + p.id + " + " + p2.id);
                    }
                }
            }
        }
    
        for (GPlane p : airport.planes) {
            if (p.wait) {
                if (p.pTimes[2] <= p.time.getMins()) {
                    p.wait = false;
                }
            }
        }
    
        if (this.planes.isEmpty() && this.airport.planes.isEmpty()) {
            System.out.println(crashes + " crashes");
            System.out.println(nearMisses + " nearMisses");
        }
    }
}
