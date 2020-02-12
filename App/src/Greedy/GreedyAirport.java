package Greedy;

import app.Airport;
import app.GPlane;

import java.awt.*;

public class GreedyAirport extends Airport {
    int crashes = 0;
    int nearMisses = 0;
    
    /**
     * <p>
     * Overridden method from the app.Airport class that draws the Airport and any planes in the scope of the Airport, now conducting the algorithm at the time of the refresh.
     * </p>
     *
     * @param g Default graphics object, part of the parent JPanel class
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        for (int i = 0; i < planes.size(); i++) {
            for (int j = i + 1; j < planes.size(); j++) {
                if (planes.get(i).coords[1] == planes.get(j).coords[1] && Math.abs(planes.get(i).coords[0] - planes.get(j).coords[0]) < 30) {
                    planes.get(i).go = false;
                    planes.get(j).go = false;
                }
            }
        }
    
        for (GPlane p : planes) {
            if (p.wait) {
                if (p.pTimes[2] <= p.time.getMins()) {
                    p.wait = false;
                }
            }
        }
    }
}
