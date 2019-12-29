package com.app;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Apron extends Drivable {
    public Apron(Rectangle2D rect, int[] target) {
        super(rect, target);
        color = Color.lightGray;
        fill = true;
    }
}
