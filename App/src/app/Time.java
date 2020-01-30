package app;

public class Time {
    public final int multiplier; //used by thread.sleeps
    private final long offset;

    public Time(int m) {
        offset = System.currentTimeMillis();
        this.multiplier = m;
    }

    public long getMins() {
        return ((System.currentTimeMillis() - offset) / 1000 / 60) * multiplier;
    }
}