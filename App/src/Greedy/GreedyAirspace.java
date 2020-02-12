package Greedy;

import app.APlane;
import app.Airport;
import app.Airspace;
import app.GPlane;

import java.awt.*;
import java.util.ConcurrentModificationException;

public class GreedyAirspace extends Airspace {
    int crashes = 0;
    int nearMisses = 0;

    public GreedyAirspace(Airport airport) {
        super(airport);
    }

    /**
     * <p>
     * Overridden method from the JPanel class that draws the Airspace and any planes in the scope of the Airspace, refreshing every 100 ms.
     * </p>
     *
     * @param g Default graphics object, part of the parent JPanel class
     * @throws ConcurrentModificationException Concurrent deletion in and iteration of the planes list
     */
    @Override
    protected void paintComponent(Graphics g) throws ConcurrentModificationException {
        super.paintComponent(g);

        //gives priority to GPlanes waiting to takeoff
        boolean allowLAND = true;
        if (airport.r.planes.size() > 0) {
            for (GPlane p : airport.planes) {
                if (p.index == 4 && p.pastGate) {
                    allowLAND = false;
                }
            }

            if (!allowLAND) {
                for (APlane p : planes) {
                    p.goAround = true;
                }
            }
        }

    }
}
