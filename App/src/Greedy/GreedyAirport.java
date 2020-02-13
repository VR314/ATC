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
    
        //check stoppage
        for (int i = 0; i < planes.size(); i++) {
            for (int j = i + 1; j < planes.size(); j++) {
                if (planes.get(i).coords[1] == planes.get(j).coords[1] && Math.abs(planes.get(i).coords[0] - planes.get(j).coords[0]) < 30) {
                    planes.get(i).go = false;
                    //planes.get(j).go = false;
                } else {
                    planes.get(i).go = true;
                    //planes.get(j).go = true;
                }
            }
        }
    
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
    }
}