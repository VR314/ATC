package com.app;

public enum Status {
    AIRSPACE, //in airspace
    TOFH, //flying to freeze horizon
    FH, //at freeze horizon
    TOMG, //flying to metered gates
    MG, //at metered gates
    APPR, //approach
    LAND, //landing
    TX, //taxiway
    RMP, //ramp
    GATE, //gate
    TKOFF, //taking off
    OUT //taken off, out of control, done
}
