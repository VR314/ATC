package com.app;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Runway extends Drivable {
    public Runway(Rectangle2D rect, int[] target) {
        super(rect, target);
        color = Color.black;
        fill = true;
    }
}
