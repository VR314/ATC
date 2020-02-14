package Greedy;

import app.Airport;

import java.awt.*;

public class GreedyAirport extends Airport {
    
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
                planes.get(i).go = planes.get(i).coords[1] != planes.get(j).coords[1] || !(Math.abs(planes.get(i).coords[0] - planes.get(j).coords[0]) < 30);
            }
        }
    
    
    }
}