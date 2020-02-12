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
    

    
        if (this.planes.isEmpty() && this.airport.planes.isEmpty()) {
            System.out.println(crashes + " crashes");
            System.out.println(nearMisses + " nearMisses");
        }
    }
}
