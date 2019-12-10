package com.app;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Airport extends JPanel {

    ArrayList<Drivable> parts;

    public Airport(){
        parts = new ArrayList<>();
    }

    //TODO:
    //  -implement Graphics, way for planes to move

    /**
     * setter for <code>parts</code>
     */
    public void setParts(ArrayList<Drivable> d){
        parts = d;
    }

    @Override
    //HARD CODED AIRPORT
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform tform = AffineTransform.getTranslateInstance( 250, 0);
        g2d.setTransform(tform);

        //RUNWAY
        g2d.fillRect(100,50,25,350);

        //TAXIWAYS // OFF/ON RAMPS OF RUNWAY
        g2d.setColor(Color.gray);
        g2d.fillRect(75,50,25,25);
        g2d.fillRect(75, 375, 25, 25);

        //TAXIWAY
        g2d.fillRect(50,50,25,350);

        //APRON
        g2d.fillRect(25, 125, 25,25);
        g2d.fillRect(25, 225, 25,25);
        g2d.fillRect(25, 325, 25,25);

        g2d.setColor(Color.black);
        g2d.draw(new Rectangle2D.Double(0, 87.5, 25, 50));
        g2d.draw(new Rectangle2D.Double(0, 137.5, 25, 50));
        g2d.draw(new Rectangle2D.Double(0, 187.5, 25, 50));
        g2d.draw(new Rectangle2D.Double(0, 237.5, 25, 50));
        g2d.draw(new Rectangle2D.Double(0, 287.5, 25, 50));
        g2d.draw(new Rectangle2D.Double(0, 337.5, 25, 50));
        repaint();
    }

}

class AirportThread extends Thread {


}
