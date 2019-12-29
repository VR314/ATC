package com.app;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Taxiway extends Drivable {
    public Taxiway(Drivable before, Rectangle2D rect, int[] target) {
        super(before, rect, target);
        color = Color.darkGray;
        fill = true;
    }
}
