package com.app;

public enum Status {
    /**in airspace */
    AIRSPACE,
    /**at metered gates */
    MG,
    /**approach */
    APPR,
    /**landing */
    LAND,
    /**taxiway */
    TX,
    /**main taxiway */
    MTX,
    /**gate */
    GATE,
    /**taking off */
    TKOFF,
    /**taken off, out of control, done */
    OUT
}
