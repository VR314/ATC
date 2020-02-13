package Greedy;

import app.APlane;
import app.Airport;
import app.Airspace;
import app.Plane;

import java.awt.*;

//FIND OUT WHATS GOING ON WITH THE RUNWAY EMPTYING
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
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        /*//gives priority to GPlanes waiting to takeoff
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
        }*/
        
        //give priority to the closest APlane to the mGate
        if (airport.r.planes.isEmpty() && !planes.isEmpty()) {
            int lowestDist = Integer.MAX_VALUE;
            APlane closest = null;
            for (APlane p : planes) {
                if (APlane.dist(p.coords, new double[]{0, 35}) < lowestDist || Plane.dist(p.coords, new double[]{0, -35}) < lowestDist) {
                    closest = p;
                }
            }
            
            closest.goAround = false;
            if (closest.coords[1] >= 0) {
                closest.mGate = APlane.GATE.NORTH;
                closest.target = new double[]{0, 35};
            } else {
                closest.mGate = APlane.GATE.NORTH;
                closest.target = new double[]{0, -35};
            }
        }
        
        
    }
}
