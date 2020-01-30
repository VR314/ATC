package app;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Gate extends Drivable {
    public Gate(Rectangle2D rect) {
        super(rect);
        fill = false;
        color = Color.black;
        target = new int[]{(int) rect.getCenterX(), (int) rect.getCenterY()};
    }
}
